package com.beitu.saas.risk.helpers;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 基本文件函数
 * 
 */
public class BaseFile {
	public static long fileCode = 0;

	public BaseFile() {

	}

	/**
	 * 获取新的文件名称,文件名系统生成
	 * 
	 */
	public static synchronized String getFileCode() {

		fileCode += 1;
		String code = String.valueOf(fileCode);
		return DateUtil.getDate(new Date(), "yyyyMMddHHmmss") + code;

	}

	/**
	 * 文件分隔符
	 * 
	 * @return 字符串
	 */
	public static String separator() {
		return File.separator;
	}

	/**
	 * 复制文件
	 * 
	 * @param src
	 *            String 源文件路径
	 * @param dst
	 *            String 目标文件路径
	 */
	public static void copy(File src, File dst) {
		try {
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dst);
			// Transfer bytes from in to out
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建文件夹
	 * 
	 * @param fileName
	 *            String 文件路径
	 * 
	 */
	public static boolean mkDir(String fileName) {
		boolean make = false;
		try {
			make = (new File(fileName)).mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return make;
	}

	/**
	 * 获取文件后缀
	 */

	public static String getFileExt(String fileName) {
		if (fileName == null || fileName.equals("")) {
			return "";
		}
		String[] ext = fileName.trim().split("\\.");
		return ext[ext.length - 1];
	}

	/**
	 * 创建写入记事本
	 * 
	 * @param filePath
	 *            String 文件路径
	 * 
	 * @param txt
	 *            String 待写入行字串
	 */
	public static void writeTxt(String filePath, String txt) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(filePath, true),
					true);
			pw.println(txt);
			pw.close();
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}

	/**
	 * 读取文本文件内容
	 * 
	 * @param filePath
	 *            String 文件路径
	 * 
	 * 
	 */
	public static String readTxt(String filePath) {
		String rtn = null;
		File txtFile = new File(filePath);
		if (!txtFile.exists()) {
			return rtn;
		}

		try {
			BufferedReader br = new BufferedReader(new FileReader(txtFile));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				String[] xx = s.split("	");
				System.out.println(xx[0]+":"+xx[1]);
				sb.append(s + "\r\n");
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			e.getStackTrace();
		}
		return rtn;
	}
	
	public static List readTxts(String filePath) {
		String rtn = null;
		File txtFile = new File(filePath);
		List list = new ArrayList();
		try {
			BufferedReader br = new BufferedReader(new FileReader(txtFile));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				list.add(s);
			}
			br.close();
			return list;
		} catch (IOException e) {
			e.getStackTrace();
		}
		return list;
	}


	/**
	 * 读取文本文件内容
	 * 
	 * @param filePath
	 *            String 文件路径
	 * 
	 * 
	 */
	public static ArrayList<String> readTxtToArray(String filePath) {
		ArrayList<String> al = new ArrayList<String>();

		File txtFile = new File(filePath);
		if (!txtFile.exists()) {
			return al;
		}

		try {
			BufferedReader br = new BufferedReader(new FileReader(txtFile));
			String s = "";
			// StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				System.out.println(s);
				al.add(s);
				// sb.append(s + "\r\n");
			}
			br.close();
			// return sb.toString();
		} catch (IOException e) {
			e.getStackTrace();
		}
		return al;
	}

	/**
	 * 读取文件例表
	 * 
	 * @param arg0
	 *            文件夹路径
	 * @return String
	 */
	public static String fileList(String arg0) {
		String rtn = "";
		LinkedList<File> list = new LinkedList<File>();
		File dir = new File(arg0);
		File file[] = dir.listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isDirectory())
				list.add(file[i]);
			else
				rtn += "\r\n" + file[i].getAbsolutePath();
			// System.out.println(file[i].getAbsolutePath());
		}
		File tmp;
		while (!list.isEmpty()) {
			tmp = list.removeFirst();
			if (tmp.isDirectory()) {
				file = tmp.listFiles();
				if (file == null)
					continue;
				for (int i = 0; i < file.length; i++) {
					if (file[i].isDirectory())
						list.add(file[i]);
					else
						rtn += "\r\n" + file[i].getAbsolutePath();
					// System.out.println(file[i].getAbsolutePath());
				}
			} else {
				rtn += "\r\n" + tmp.getAbsolutePath();
				// System.out.println(tmp.getAbsolutePath());
			}
		}
		return rtn;
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myDelFile = new File(filePath);
			myDelFile.delete();

		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 删除文件夹
	 *
	 * @param filePathAndName
	 *            String 文件夹路径及名称 如c:/fqf
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹

		} catch (Exception e) {
			System.out.println("删除文件夹操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param path
	 *            String 文件夹路径 如 c:/fqf
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
			}
		}
	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath) {

		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	public static void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		delFile(oldPath);

	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	public void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);

	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	@SuppressWarnings({ "unused", "resource" })
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
//				int length = 0;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小

					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}
	
	public static int getTotalLines(String filePath) throws IOException {  
		File sourceFile = new File(filePath);
		FileReader in = new FileReader(sourceFile);  
        LineNumberReader reader = new LineNumberReader(in);  
        String s = reader.readLine();  
        int lines = 0;  
       while (s != null) {  
            lines++;  
           s = reader.readLine();  
        }  
        reader.close();  
        in.close();  
        return lines;  
    } 
	
	/**
     * 编辑参数值
     * @param param
     * @param value
     */
    @SuppressWarnings("unused")
	public static void editParamValue(String path,String param,String value) {
    	param = param + "=";
        String temp = "";
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // 保存该行前面的内容
            for (int j = 1; (temp = br.readLine()) != null
                    && !temp.contains(param); j++) {
                buf = buf.append(temp);
                buf = buf.append(System.getProperty("line.separator"));
            }
            
            // 将内容插入
            buf = buf.append(param+value);

            // 保存该行后面的内容
            while ((temp = br.readLine()) != null) {
                buf = buf.append(System.getProperty("line.separator"));
                buf = buf.append(temp);
            }

            br.close();
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取参数值
     * @param path
     * @param param
     * @return
     * @throws IOException
     */
 
	@SuppressWarnings("resource")
	public static String getParamValue(String path,String param) throws IOException{
    	param = param+"=";
    	File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String temp = "";
        String value = "";
        while ((temp = br.readLine()) != null) {
        	if(temp.contains(param))break;
        }
        value = temp.substring(param.length());
    	return value;
    }
public static void main(String[] args) {
	readTxt("d:\\xx.txt");
}
}
