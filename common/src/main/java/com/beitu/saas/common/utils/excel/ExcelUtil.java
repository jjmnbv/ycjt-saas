package com.beitu.saas.common.utils.excel;

import com.beitu.saas.common.utils.StringUtil;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;

public class ExcelUtil {

    private static final Log log = LogFactory.getLog(ExcelUtil.class);

    /**
     * 生成 excel 文件
     *
     * @param resulsts excel 内容
     * @param header   excel表头
     * @param fileName excel文件名
     * @return
     */
    public static File createExcelFile(List resulsts, LinkedHashMap<String, String> header, String filePath, String fileName) {
        if (StringUtils.isNotEmpty(fileName)) {
            String fileSuffix = fileName.substring(fileName.length() - 5).toLowerCase();
            if (".xlsx".equals(fileSuffix)) {
                return createXLSXFile(resulsts, header, filePath, fileName);
            } else if (".csv".equals(fileSuffix.substring(0))) {
                return createCSVFile(resulsts, header, filePath, fileName);
            } else {
                log.info("需要生成的文件名（{}）系统暂不支持！", fileName);
                return null;
            }
        }
        log.info("文件名为空！");
        return null;
    }

    /**
     * 在输出流中 生成 excel 文件
     *
     * @param resulsts excel 内容
     * @param header   excel表头
     * @param outputStream 输出流
     * @return
     */
    public static void createExcelFile(List resulsts, LinkedHashMap<String, String> header, OutputStream outputStream) {
        if (outputStream == null) {
            log.warn("文件输出流为空！");
        }
        createCSVFile(resulsts, header, outputStream);
    }

    private static File createXLSXFile(List<Object> resulsts, LinkedHashMap<String, String> header, String filePath, String fileName) {
        FileOutputStream out = null;
        try {
            Workbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
            Sheet sh = wb.createSheet();
            // 设置 excel 的默认宽度为20个字符的宽度
            sh.setDefaultColumnWidth(20);
            int rowNumber = 0;
            // 写入文件头部
            Row row = sh.createRow(rowNumber++);
            writeXLSXHeader(header, row);
            // 写入文件内容
            if (resulsts != null && resulsts.size() > 0) {
                for (Object object : resulsts) {
                    row = sh.createRow(rowNumber++);
                    writeXLSXContents(object, header, row);
                }
            }
            File excelFile = createFileByFileName(filePath, fileName);
            out = new FileOutputStream(excelFile);
            wb.write(out);

            return excelFile;
        } catch (Exception e) {
            log.warn("生成{}文件发出异常！异常：{}", fileName, e.getMessage());
            return null;
        } finally {
            try {
                if (out != null) {
                    out.close();
                    out.flush();
                }
            } catch (Exception e) {

            }
        }
    }

    private static void writeXLSXHeader(LinkedHashMap<String, String> header, Row row) throws IOException {
        int number = 0;
        for (String headerContents : header.values()) {
            row.createCell(number++).setCellValue(headerContents);
        }
    }

    private static void writeXLSXContents(Object contents, LinkedHashMap<String, String> header, Row row) throws IOException {
        Object[] objectArray = getContents(contents, header);
        for (int i = 0; i < objectArray.length; i++) {
            row.createCell(i).setCellValue(StringUtil.getString4Object(objectArray[i]));
        }
    }

    private static File createCSVFile(List<Object> resulsts, LinkedHashMap<String, String> header, String filePath, String fileName) {
        BufferedWriter csvWriter = null;
        try {
            File csvFile = createFileByFileName(filePath, fileName);
            // GB2312使正确读取分隔符","
            csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GB2312"), 1024);

            // 写入文件头部
            writeCSVHeader(header, csvWriter);

            // 写入文件内容
            if (resulsts != null && resulsts.size() > 0) {
                for (int i = 0; i < resulsts.size(); i++) {
                    writeCSVContents(resulsts.get(i), header, csvWriter);
                }
            }
            csvWriter.flush();

            return csvFile;
        } catch (Exception e) {
            log.warn("生成{}文件发出异常！异常：{}", fileName, e.getMessage());
            return null;
        } finally {
            try {
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createCSVFile(List<Object> resulsts, LinkedHashMap<String, String> header, OutputStream outputStream) {
        BufferedWriter csvWriter = null;
        try {
            // GB2312使正确读取分隔符","
            csvWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "GB2312"), 1024);

            // 写入文件头部
            writeCSVHeader(header, csvWriter);

            // 写入文件内容
            if (resulsts != null && resulsts.size() > 0) {
                for (int i = 0; i < resulsts.size(); i++) {
                    writeCSVContents(resulsts.get(i), header, csvWriter);
                }
            }
            csvWriter.flush();
        } catch (Exception e) {
            log.warn("在输出流生成文件发出异常！异常：{}", e.getMessage());
        } finally {
            try {
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeCSVHeader(LinkedHashMap<String, String> header, BufferedWriter csvWriter) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (String headerContents : header.values()) {
            sb.append("\"").append(headerContents).append("\",");
        }
        csvWriter.write(sb.toString());
        csvWriter.newLine();
    }

    private static void writeCSVContents(Object contents, LinkedHashMap<String, String> header, BufferedWriter csvWriter) throws IOException {
        Object[] objectArray = getContents(contents, header);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objectArray.length; i++) {
            sb.append("\"").append(objectArray[i]).append("\",");
        }
        csvWriter.write(sb.toString());
        csvWriter.newLine();
    }

    /**
     * 得到 对象 对应 表头的值
     *
     * @param object 对象
     * @param header 表头
     * @return 所对应的值
     */
    private static Object[] getContents(Object object, LinkedHashMap<String, String> header) {
        Object[] contents = new Object[header.size()];
        int number = 0;
        for (String key : header.keySet()) {
            if (StringUtils.isEmpty(key)) {
                contents[number++] = "";
            } else {
                contents[number++] = getAllValue(object, key);
            }
        }
        return contents;
    }

    /**
     * 得到 对象 某属性名的 值
     *
     * @param object    对象
     * @param fieldName 属性名(可多个，用","隔开)
     * @return 对象的属性值(可返回多个，用"／"隔开)
     */
    private static Object getAllValue(Object object, String fieldName) {
        if (fieldName.indexOf(",") < 0) {
            return getValue(object, fieldName);
        } else {
            String[] fieldNameArray = fieldName.split(",");
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < fieldNameArray.length; i++) {
                fieldName = fieldNameArray[i];
                if (StringUtils.isNotEmpty(fieldName)) {
                    result.append(getValue(object, fieldName)).append("/");
                }
            }
            return result.deleteCharAt(result.length() - 1).toString();
        }
    }

    private static Object getValue(Object object, String fieldName) {
        String getterMethodName = getGetterMethodNameByFieldName(fieldName);
        try {
            Method getterMethod = object.getClass().getDeclaredMethod(getterMethodName);
            return getterMethod.invoke(object);
        } catch (Exception e) {
            log.info("{}类中反射{}方法发生异常！异常：{}", object.getClass(), getterMethodName, e.getMessage());
            return "";
        }
    }

    /**
     * 根据 属性名 得到 getter方法名
     *
     * @param fieldName 属性名
     * @return
     */
    private static String getGetterMethodNameByFieldName(String fieldName) {
        StringBuilder methodName = new StringBuilder("get");
        methodName.append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1));
        return methodName.toString();
    }

    /**
     * 根据 文件名 生成文件，若存在，
     *
     * @param fileName
     * @return
     */
    private static File createFileByFileName(String filePath, String fileName) {
        if (filePath.endsWith(File.separator)) {
            filePath = filePath.substring(0, filePath.length() - File.separator.length());
        }
        File catalog = new File(filePath);
        if (!catalog.exists()) {
            // 目录不存在，则创建目录
            catalog.mkdir();
        }
        fileName = filePath + File.separator + fileName;
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            log.info("创建文件失败！文件：{};异常：{}", fileName, e.getMessage());
        }
        return null;
    }

}
