package com.taovo.rjp.packagedemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author：RJP on 2017/3/16 11:40
 */

public class PackageUtil {
    public static final String SCHEMA = "http://download.dajiang365.com/";

    public static void main(String[] args) {
        gen("D:\\Android\\company\\package\\1.7.5\\pp\\0926\\pp");
//        writeTo("D:\\Android\\company\\package\\170\\qiniu\\common1");
//        try {
//            count("D:\\Android\\workspace\\ZYWL_android");
//            System.out.println("--------->" + countNum);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    private static void writeTo(String path) {
        File file = new File(path);
        File txt = new File(path + File.separator + "链接.txt");
        if (!txt.exists()) {
            try {
                txt.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream ios = null;
        try {
            ios = new FileOutputStream(txt);
            ios.write(("渠道号" + "          " + "下载链接").getBytes());
            ios.write("\r\n".getBytes());
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File file1 : files) {
                    String name = file1.getName();
                    if(name.contains("-")) {
                        String[] split = name.split("-");
                        ios.write((split[2].split("\\.")[0] + "          " + SCHEMA + name).getBytes());
                        ios.write("\r\n".getBytes());
                    }
                }
                ios.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(ios != null){
                try {
                    ios.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void gen(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                try {
                    String name = file1.getName();
                    String[] names = name.split("_");
                    String laterName = names[0] + "-" + deal(names[2]) + "-" + names[1] + "-" + names[4] + "_" + names[5] + "_" + names[6] + ".apk";
                    file1.renameTo(new File(path + File.separator + laterName));
                }catch (Exception e){

                }
            }
        }
    }

    private static void gen1(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                try {
                    String name = file1.getName();
                    String[] names = name.split("-");
                    String laterName = names[0] + "-" + names[2] + "-" + names[3];
                    file1.renameTo(new File(path + File.separator + laterName));
                }catch (Exception e){

                }
            }
        }
    }

    private static String deal(String name) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            sb.append(name.charAt(i) + ".");
        }
        return sb.substring(0,sb.length() - 1);
    }

    //app-1.7.0-zywl-agent_huawei.apk
    private static int countNum = 0;

    private static void count(String path) throws Exception {
        File file = new File(path);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File file1 : files) {
                if(file1.isDirectory()){
                    count(file1.getAbsolutePath());
                }else{
                    boolean b = file1.getPath().endsWith(".java");
                    if(b) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
                        while (true){
                            String str = in.readLine();
                            if(str != null){
                                countNum ++;
                            }else{
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
