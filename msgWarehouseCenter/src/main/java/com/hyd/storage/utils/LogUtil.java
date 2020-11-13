package com.hyd.storage.utils;

import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class LogUtil {

    /**
     * 创建日志文件
     * @param logStr 日志内容
     * @return*/

    public static void info(String logStr){
        String content = infoLogContent(logStr);
        buildLogFile(content, "info");
    }

    public static void error(String logStr, Exception e){
         String content = errorLogContent(logStr, e);
         buildLogFile(content, "error");
    }

    public static void warn(String logStr){
         String content = warnLogContent(logStr);
         buildLogFile(content, "warn");
    }

    public static void clientErr(String log, Exception e){
        String content = errorLogContent(log, e);
        buildLogFile(content, "communication");
    }

    private static void buildLogFile(String content, String folderNam){
        String rootPath = System.getProperty("user.dir")+"/log/";
        String folderPath = rootPath + folderNam;
        // 文件夹路径
        File filefolder = new File(folderPath);
        if(!filefolder.exists()){ // 如果文件夹不存在
            filefolder.mkdirs(); // 创建文件夹
        }

        //日志路径 根/log/info(error、warn) /yyyyMMdd/yyyyMMddHH.log
        LocalDateTime date = LocalDateTime.now();
        String time = DateTimeFormatter.ofPattern("yyyyMMddHH").format(date);
        String day = DateTimeFormatter.ofPattern("yyyyMMdd").format(date);
        File dayFolder = new File(filefolder + "\\" + day);
        if (!dayFolder.exists()){
            dayFolder.mkdirs();
        }
        File file = new File(dayFolder + "\\"+time+ ".log");
        RandomAccessFile mm = null;
        FileOutputStream o = null;
        try {
            o = new FileOutputStream(file,true);
            o.write(content.getBytes("utf-8"));
            o.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (mm != null) {
                try {
                    mm.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 完整的堆栈信息
     * @param e Exception
     * @return Full StackTrace
     */
    public static String getStackTrace(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }


    private static String infoLogContent(String str){
        StringBuffer sb = new StringBuffer();
        String d = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now());
        sb.append(d).append("\t\tINFO\t: ").append(str).append("\n");
        return sb.toString();
    }

    private static String warnLogContent(String str){
        StringBuffer sb = new StringBuffer();
        String d = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now());
        sb.append(d).append("\t\tWARN\t: ").append(str).append("\n");
        return sb.toString();
    }

    private static String errorLogContent(String str, Exception e){
        StringBuffer sb = new StringBuffer();
        String d = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now());
        sb.
                append(d).append("\t\tERROR\t: ").append(str).append("\t").append(e.getMessage()).append("\n")
                .append(getStackTrace(e)).append("\n\n");
        return sb.toString();
    }

}
