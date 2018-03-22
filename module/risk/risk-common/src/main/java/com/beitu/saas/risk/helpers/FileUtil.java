package com.beitu.saas.risk.helpers;

import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;

import java.io.*;

/**
 * @Author 柳朋朋
 * @Create 2016-12-23 18:11
 */
public class FileUtil {
    private static Log LOGGER = LogFactory.getLog(FileUtil.class);


    public static boolean string2File(String res, String filePath) {
        boolean flag = true;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            File distFile = new File(filePath);
            if (!distFile.getParentFile().exists()) distFile.getParentFile().mkdirs();
            bufferedReader = new BufferedReader(new StringReader(res));
            bufferedWriter = new BufferedWriter(new FileWriter(distFile));
            char buf[] = new char[1024];         //字符缓冲区
            int len;
            while ((len = bufferedReader.read(buf)) != -1) {
                bufferedWriter.write(buf, 0, len);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            LOGGER.info(".......文件上传异常，filePath: {}, e:", filePath, e);
            flag = false;
            return flag;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

}
