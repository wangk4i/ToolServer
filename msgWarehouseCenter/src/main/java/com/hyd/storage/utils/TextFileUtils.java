package com.hyd.storage.utils;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/18 18:33
 */
@Component
public class TextFileUtils {

    /**
     * UTF-8编码写文件
     */
    public static void writeFile(String filePath, String fileStream){
        File folder = new File(filePath.substring(0, filePath.lastIndexOf(File.separator)));
        if (!folder.exists()){
            folder.mkdirs(); // 创建文件夹
        }

        File file = new File(filePath);
        FileOutputStream o = null;
        try {
            o = new FileOutputStream(file,true);
            o.write(fileStream.getBytes("utf-8"));
            o.close();
        } catch (IOException e) {
            LogUtil.error("写入文件异常,文件路径: "+filePath, e);
        }
    }

    public static void move(String sourcePath, String toPath){
        try {
            Files.move(Paths.get(sourcePath), Paths.get(toPath));
        }catch (IOException e){
            LogUtil.error("移动文件失败,文件来源:"+sourcePath+",文件去向:"+toPath, e);
        }
    }

    public static void delete(String filePath){
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            LogUtil.error("删除文件失败,文件来源:"+filePath, e);
        }
    }

    /**
     * 读文件，内容转为String
     * @param
     * @return
     */
    public static String readFile(String path) {
        File file = null;
        try {
            file = new File(path);
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            return sb.toString();
        } catch (Exception e) {
            LogUtil.error(file.getPath() + "文件解析异常", e);
            return null;
        }
    }

    public static String readFile(File file){
        try {
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1){
                sb.append((char)ch);
            }
            fileReader.close();
            reader.close();
            return sb.toString();
        }catch (Exception e){
            LogUtil.error(file.getPath() + "文件解析异常", e);
            return null;
        }
    }

    public static String readFile(String path, String charset) {
        File file = null;
        try {
            file = new File(path);
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file), charset);
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            return sb.toString();
        } catch (Exception e) {
            LogUtil.error(file.getPath() + "文件解析异常", e);
            return null;
        }
    }

    public static String readFile(File file, String charset){
        try {
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file), charset);
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1){
                sb.append((char)ch);
            }
            fileReader.close();
            reader.close();
            return sb.toString();
        }catch (Exception e){
            LogUtil.error(file.getPath() + "文件解析异常", e);
            return null;
        }
    }

    /**
     * 移动文件到新路径下
     */
    public static void move(String sourcePath,String fileName, String toPath){
        try {
            File tmpFile = new File(toPath);
            if(!tmpFile.exists()){
                tmpFile.mkdirs();
            }
            Files.move(Paths.get(sourcePath + File.separator + fileName)
                    , Paths.get(toPath + File.separator + fileName));
        }catch (IOException e){
            LogUtil.error("移动文件失败,文件来源 "+ sourcePath +",文件去向 "+ toPath, e);
        }
    }

    /**
     * 正则匹配方法
     */
    public static String matchValue(String stream, String regEx){
        String resultInfoStr = null;
        try {
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(stream);
            if(!matcher.find()){
                LogUtil.warn("未匹配到，返回值格式有误.原始值:"+ stream +"，正则: "+ regEx);
            }
            resultInfoStr = matcher.group(1);
        } catch (Exception e) {
            LogUtil.error("",e);
        }
        return resultInfoStr;
    }

    public static boolean hasValue(String stream, String regEx){
        try {
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(stream);
            return matcher.find();
        } catch (Exception e) {
            LogUtil.error("",e);
        }
        return false;
    }

    /**
     * 返回目标文件夹下所有子文件夹
     */
    public static List<File> traverseFolder(String path) {
        List<File> fileList = new ArrayList<>();
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                return null;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        fileList.add(file2);
                    }
                }
            }
        } else {
            return null;
        }
        return fileList;
    }

    public static List<File> traverseFolder(File file) {
        List<File> fileList = new ArrayList<>();
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                return null;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        fileList.add(file2);
                    }
                }
            }
        } else{
            return null;
        }
        return fileList;
    }

    /**
     * 返回目标文件夹下所有文件
     */
    public static List<File> traverseFile(String path) {
        List<File> fileList = new ArrayList<>();
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                return null;
            } else {
                for (File file2 : files) {
                    if (!file2.isDirectory()) {
                        fileList.add(file2);
                    }
                }
            }
        } else {
            return null;
        }
        return fileList;
    }

    public static List<File> traverseFile(File file) {
        List<File> fileList = new ArrayList<>();
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                return null;
            } else {
                for (File file2 : files) {
                    if (!file2.isDirectory()) {
                        fileList.add(file2);
                    }
                }
            }
        } else{
            return null;
        }
        return fileList;
    }



    /**
     * 更改目标文件对应内容
     * @param path 文件全路径名
     * @param oldString 需修改的内容
     * @param newString 修改后内容
     */
    public static void alterStringToCreateNewFile(String path, String oldString, String newString){
        File file = new File(path);
        try {
//            long start = System.currentTimeMillis(); //开始时间
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file))); //创建对目标文件读取流
            File newFile = new File("D:\\newFile"); //创建临时文件
            if (!newFile.exists()){
                newFile.createNewFile(); //不存在则创建
            }
            //创建对临时文件输出流，并追加
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(newFile,true)));
            String string = null; //存储对目标文件读取的内容
//            int sum = 0; //替换次数
            while ((string = br.readLine()) != null){
                //判断读取的内容是否包含原字符串
                if (string.contains(oldString)){
                    //替换读取内容中的原字符串为新字符串
                    string = new String(
                            string.replace(oldString,newString));
//                    sum++;
                }
                bw.write(string);
                bw.newLine(); //添加换行
            }
            br.close(); //关闭流，对文件进行删除等操作需先关闭文件流操作
            bw.close();
            String filePath = file.getPath();
            file.delete(); //删除源文件
            newFile.renameTo(new File(filePath)); //将新文件更名为源文件
//            long time = System.currentTimeMillis() - start; //整个操作所用时间;
//            System.out.println(sum+"个"+oldString+"替换成"+newString+"耗费时间:"+time);
        } catch(Exception e){
            e.printStackTrace();
        }
    }




}
