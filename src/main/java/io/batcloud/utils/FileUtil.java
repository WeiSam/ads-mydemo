package io.batcloud.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;

import com.zz.common.http.HttpClientUtil;
import com.zz.common.http.model.HttpResult;
import com.zz.common.log.LogService;

public class FileUtil {
	 // 缓存文件头信息-文件头信息
    public static final HashMap<String, String> mFileTypes = new HashMap<String, String>();

    static {
        // images
        mFileTypes.put("FFD8FF", "jpg");
        mFileTypes.put("89504E47", "png");
        mFileTypes.put("47494638", "gif");
        mFileTypes.put("49492A00", "tif");
        mFileTypes.put("424D", "bmp");
        //
        mFileTypes.put("41433130", "dwg"); // CAD
        mFileTypes.put("38425053", "psd");
        mFileTypes.put("7B5C727466", "rtf"); // 日记本
        mFileTypes.put("3C3F786D6C", "xml");
        mFileTypes.put("68746D6C3E", "html");
        mFileTypes.put("44656C69766572792D646174653A", "eml"); // 邮件
        mFileTypes.put("D0CF11E0", "doc");
        mFileTypes.put("5374616E64617264204A", "mdb");
        mFileTypes.put("252150532D41646F6265", "ps");
        mFileTypes.put("255044462D312E", "pdf");
        mFileTypes.put("504B03040A00000000008", "docx");
        mFileTypes.put("504B0304", "zip");// zip 压缩文件
        mFileTypes.put("52617221", "rar");
        mFileTypes.put("57415645", "wav");
        mFileTypes.put("41564920", "avi");
        mFileTypes.put("2E524D46", "rm");
        mFileTypes.put("000001BA", "mpg");
        mFileTypes.put("000001B3", "mpg");
        mFileTypes.put("6D6F6F76", "mov");
        mFileTypes.put("3026B2758E66CF11", "asf");
        mFileTypes.put("4D546864", "mid");
        mFileTypes.put("1F8B08", "gz");
    }

    private static String matchFileType(String fileTypeMatchString) {
    	  String result = "";
          for (Entry<String, String> entry : mFileTypes.entrySet()) {
              if (fileTypeMatchString.startsWith(entry.getKey())) {
                  result = entry.getValue();
              }
          }
          return result;
    }
    
    /**
     * 根据文件路径获取文件类型
     *
     * @param filePath
     *            文件路径
     * @return 文件类型
     */
    public static String getFileType(String filePath) {
        String value = getFileHeader(filePath);
        return matchFileType(value);
    }

    public static String getFileType(byte[] b) {
        String value = getFileHeader(b);
        return matchFileType(value);
    }

    
    
    public static String getFileType(File file) {
    	 String value = getFileHeader(file);
         return matchFileType(value);
    }
    
    /**
     * 根据文件获取文件头信息
     *
     * @param filePath
     *            文件路径
     * @return 文件头信息
     */
    private static String getFileHeader(byte[] b) {
       
        String value = "";
        try {
        	byte[] slice = Arrays.copyOfRange(b, 0, 20);
           
            value = bytesToHexString(slice);
        } catch (Exception e) {
        	
        } 
        return value;
    }
    
    
    
    /**
     * 根据文件获取文件头信息
     *
     * @param filePath
     *            文件路径
     * @return 文件头信息
     */
    private static String getFileHeader(File file) {
        FileInputStream is = null;
        String value = "";
        try {
        	
            is = new FileInputStream(file);
            byte[] b = new byte[20];
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
        	LogService.error("FileUtil.getFileHeader error:"+ e.getMessage(),e);
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return value;
    }
    
    
    /**
     * 根据文件路径获取文件头信息
     *
     * @param filePath  文件路径
     * @return 文件头信息
     */
    private static String getFileHeader(String filePath) {
       return getFileHeader(new File(filePath));
    }

    /**
     * 将要读取文件头信息的文件的byte数组转换成string类型表示
     *
     * @param src
     *            要读取文件头信息的文件的byte数组
     * @return 文件头十六进制信息
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }

	/**
	 * 复制文件到指定的目录
	 * 
	 * @param srcfile 源文件
	 * @param destPath 目的文件
	 * @return {@link File} 返回目的文件
	 */
	public static File copyFile(File srcfile, String destPath) {
		File resultFile = null;
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			if (srcfile == null || !srcfile.exists()) {
				return resultFile;
			}
			File destFile = new File(destPath);
			if (!destFile.getParentFile().exists()) {
				destFile.getParentFile().mkdirs();
			}
			in = new FileInputStream(srcfile);
			out = new FileOutputStream(destFile);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			if (destFile.exists()) {
				resultFile = destFile;
			}
			in.close();
			out.close();
		} catch (Exception e) {
			LogService.error("FileUtil.copyFile,srcFile:"+ srcfile.getAbsolutePath()+",destPath:"+ destPath+ ",error:"+ e.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}

			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}
		return resultFile;
	}

	/**
	 * 
	 * @param zipFile
	 * @param descDir
	 * @param addSurfixIfAbsent 解压的文件加后缀
	 * @throws IOException
	 */
	public static List<File> unZipFiles(File zipFile,String descDir,boolean addSurfixIfAbsent)throws IOException{
		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile);
		List<File> unZipFiles = new ArrayList<>();
		
		for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = descDir + File.separator + zipEntryName;
			
			// 判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf(File.separator)));
			if (!file.exists()) {
				file.mkdirs();
			}
			// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if (new File(outPath).isDirectory()) {
				continue;
			}
			// 输出文件路径信息
			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
			
			File outFile = new File(outPath);
			String fileType = getFileType(outPath);
			if(addSurfixIfAbsent && !outPath.endsWith("." +  fileType)) {
				//如果文件没有后缀，自动加后缀
				File newFile = new File(outPath + "."+ fileType);
				outFile.renameTo(newFile);
				outFile = newFile; 
			} 
			unZipFiles.add(outFile);
		}
		zip.close();
		
		return unZipFiles;
	}
	
	
	
	/**http下载文件 **/
	public static byte[] httpDownload(String httpUrl) {

        try {
            HttpResult  result = HttpClientUtil.get(httpUrl);
            return result.getData();
            
       } catch (Exception e) {
    	   LogService.error("FileUtil.httpDownload:" + e.getMessage(),e);
    	}
       return null;
    }
	
	//移动文件夹
	public static File moveFile(String srcFileName, String destDirName) {  
	      
	    File srcFile = new File(srcFileName);  
	    if(!srcFile.exists() || !srcFile.isFile()) {
	        LogService.error("FileUtil.moveFile:"+ srcFileName + " is not exist or not a file");
	    	return null;  
	    }
	    File destDir = new File(destDirName);  
	    if (!destDir.exists())  {
	        destDir.mkdirs();  
	    }
	    File newFile = new File(destDirName + File.separator + srcFile.getName());
	    srcFile.renameTo(newFile); 
	    return newFile;
	}  
	
	
	public static void main(String[] args) throws Exception {
//		FileUtil.httpDownload("http://cdn.batmobi.net/appstore/richmedia/20161208/rr0w2wal082hj14e83s5d5tj_com.trivago_480x800.jpg", "./../pic.jpg");
		
//	    FileUtil.moveFile("C:\\Users\\zhengjianhao\\Desktop\\debug.log","C:\\Users\\zhengjianhao\\Desktop\\debug");
		File f = new File("C:\\Users\\zhengjianhao\\Desktop\\AD-icon.png");
		FileInputStream is = new FileInputStream(f);
		
		byte[] b = IOUtils.toByteArray(is);
		
		System.out.println(FileUtil.getFileType(b));
		
	}
}
