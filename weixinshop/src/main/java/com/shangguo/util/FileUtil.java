package com.shangguo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author JabnMai
 * @date 2015年7月14日
 * 
 */
public class FileUtil {
    
    public static String getStringFile(String filePath) {
        try {
            InputStream in = getLocalInputStream(filePath);
            byte[] tempByte = new byte[1024 * 10];
            byte[] b = new byte[0];
            int tempInt = 0;
            while ((tempInt = in.read(tempByte)) != -1) {
                b = ArrayUtils.addAll(b, Arrays.copyOf(tempByte, tempInt));
            }
            in.close();
            return new String(b);
        } catch (Exception ex) {
            throw new RuntimeException("文件错误:", ex);
        }
    }
    /**
     * 过滤所有环境变量参数
     * @param str
     * @return
     */
    public static String filterSysEnv(String str){
    	Pattern pattern = Pattern.compile("\\$\\{[^\\}]+\\}");
		Matcher matcher = pattern.matcher(str);
		return matcher.replaceAll("\"\"");
    }
    
    public static InputStream getLocalInputStream(String filePath) {
        File file = getLocalFile(filePath);
        if (file!=null && file.exists() && file.isFile()) {
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
            }
        }else{
            //在jar包里
            return Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        }
        return null;
    }
    
    public static File getLocalFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file;
        }else if(filePath.indexOf("/")==0){
            return file;
        }else if(filePath.indexOf(":")!=-1){
            return file;
        }else{
            return new File(Thread.currentThread().getContextClassLoader().getResource("").getPath()+filePath);
        }
    }
    
}

