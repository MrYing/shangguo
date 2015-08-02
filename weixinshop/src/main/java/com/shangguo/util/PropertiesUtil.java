package com.shangguo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 操作资源文件类
 * @author JabnMai
 * @date 2015年7月14日
 * 
 */
public class PropertiesUtil {

    private final static Logger log = LoggerFactory.getLogger(PropertiesUtil.class);
    public static Properties prop;
    public static Properties readProperties(String fileName) {
        Properties props = new Properties();
        synchronized (fileName) {
            InputStreamReader reader = null;
            try {
                InputStream in = FileUtil.getLocalInputStream(fileName);
                reader = new InputStreamReader(in, "UTF-8");
                props.load(reader);
            } catch (Exception e) {
                return null;
            } finally {
                try {
                    if (reader != null) reader.close();
                } catch (IOException e) {
                    log.error("关闭properties流文件错误", e);
                }
            }
        }
        return props;
    }

    /**
     * 修改Properties,注释不会被清除
     * 
     * @param file
     * @param key
     * @param value
     * @return
     */
    public static void setProfileString(String fileName, String key, String value) {
        String path = null;
        if (new File(fileName).exists()) {
            path = new File(fileName).getPath();
        } else {
            path = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
        }
        synchronized (fileName) {
            // 得到入理信息字符串
            String str = getFile(path, key, value);
            // 写入文件
            if (str != null) {
                writeFile(path, str);
            }
        }
    }

    /**
     * 得到处理过的信息字符串
     * 
     * @return
     */
    private static String getFile(String path, String key, String value) {
        BufferedReader br = null;
        try {
            FileInputStream f = new FileInputStream(path);
            br = new BufferedReader(new InputStreamReader(f, "UTF-8"));
            StringBuffer outstr = new StringBuffer();
            String line = "";
            boolean res = false;
            while ((line = br.readLine()) != null) {
                if (line == "") {
                    // 如果为空
                    outstr.append("\n");
                } else if (line.startsWith("#")) {
                    // 如果为注释
                    outstr.append(line + "\n");
                } else if (line.trim().startsWith(key)) {
                    String[] keyandvalue = line.split("=");
                    outstr.append(keyandvalue[0].toString() + "=" + value.toString() + "\n");
                    res = true;
                } else {
                    outstr.append(line + "\n");
                }
            }
            if (res) {
                return outstr.toString();
            }
        } catch (Exception e) {
            log.error("读取properties错误", e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                log.error("关闭properties读取流错误", e);
            }
        }
        return null;
    }

    /**
     * 把字符串写入文件
     * 
     * @param str
     */
    private static void writeFile(String path, String str) {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fos = new FileOutputStream(path, false);
            osw = new OutputStreamWriter(fos, "UTF-8");
            osw.write(str);
            osw.flush();

        } catch (Exception e) {
            log.error("写入properties文件错误,错误码为102", e);
        } finally {
            try {
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                log.error("关闭properties写入流错误", e);
            }
        }
    }

}

