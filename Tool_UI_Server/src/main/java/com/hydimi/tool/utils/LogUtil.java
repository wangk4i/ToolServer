package com.hydimi.tool.utils;

import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/27 11:23
 */
@Component
public class LogUtil {

    public static boolean error(String log, Exception e){
        String content = errorLogContents(log, e);
        return buildLogFile(content, "error");
    }

    public static boolean info(String log){
        String content = infoLogContents(log);
        return buildLogFile(content, "info");
    }

    public static boolean warn(String log){
        String content = warnLogContents(log);
        return buildLogFile(content, "warn");
    }

    public static boolean clientErr(String log, Exception e){
        String content = errorLogContents(log, e);
        return buildLogFile(content, "communication");
    }

    public static void bizErr(String log){
        String content = warnLogContents(log);
        buildLogFile(content, "bussiness");
    }

    /**
     * 创建日志文件
     * @param content 日志内容
     * @param folderNam 日志文件夹
     * @return
     */
    public static boolean buildLogFile(String content, String folderNam){
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHH");
        String rootPath = System.getProperty("user.dir")+"/log/";
        String folderPath = rootPath + folderNam;
        // 文件夹路径
        File rootfolder = new File(rootPath);
        if(!rootfolder.exists()){ // 如果文件夹不存在
            rootfolder.mkdir(); // 创建文件夹
        }
        File filefolder = new File(folderPath);
        if(!filefolder.exists()){ // 如果文件夹不存在
            filefolder.mkdir(); // 创建文件夹
        }
        // 日志路径  :   根/log/ /接口方法名_yyyyMMddHH.log
        String filePath = sd.format(date)+".log";
        File fileName = new File(folderPath+"/"+filePath);
        RandomAccessFile mm = null;
        boolean flag = false;
        FileOutputStream o = null;
        try {
            o = new FileOutputStream(fileName,true);
            o.write(content.getBytes("utf-8"));
            o.close();
            flag = true;
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
        return flag;
    }

    private static String errorLogContents(String logStr, Exception e){
        StringBuffer sb = new StringBuffer();
        String d = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now());
        sb
                .append(d).append("\tERROR\t").append("\t:\t ")
                .append(logStr).append(" \n").append(e.getMessage()).append("\n")
                .append(getStackTrace(e)).append("\n\n");
        return sb.toString();
    }

    private static String infoLogContents(String logStr){
        StringBuffer sb = new StringBuffer();
        String d = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now());
        sb
                .append(d).append("\tINFO\t").append("\t:\t")
                .append(logStr).append("\n");
        return sb.toString();
    }

    private static String warnLogContents(String logStr){
        StringBuffer sb = new StringBuffer();
        String d = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now());

        sb
                .append(d).append("\tWARN\t").append("\t:\t")
                .append(logStr).append("\n");
        return sb.toString();
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

}
