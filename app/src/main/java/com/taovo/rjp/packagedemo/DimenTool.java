package com.taovo.rjp.packagedemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DimenTool {
    static String filePath = "D:\\Android\\company\\values-dimens";
  
    public static void gen() {  
        //以此文件夹下的dimens.xml文件内容为初始值参照  
        File file = new File(filePath + "/values/dimens.xml");
  
        BufferedReader reader = null;
        StringBuilder sw240 = new StringBuilder();  
        StringBuilder sw320 = new StringBuilder();
        StringBuilder sw360 = new StringBuilder();
        StringBuilder sw400 = new StringBuilder();
        StringBuilder sw440 = new StringBuilder();
        StringBuilder sw480 = new StringBuilder();
        StringBuilder sw600 = new StringBuilder();  
  
        StringBuilder sw720 = new StringBuilder();  
  
        StringBuilder sw800 = new StringBuilder();  
  
        StringBuilder w820 = new StringBuilder();  
  
        try {  
  
            System.out.println("生成不同分辨率：");  
  
            reader = new BufferedReader(new FileReader(file));
  
            String tempString;  
  
            int line = 1;  
  
            // 一次读入一行，直到读入null为文件结束  
  
            while ((tempString = reader.readLine()) != null) {  
  
  
                if (tempString.contains("</dimen>")) {  
  
                    //tempString = tempString.replaceAll(" ", "");  
  
                    String start = tempString.substring(0, tempString.indexOf(">") + 1);  
  
                    String end = tempString.substring(tempString.lastIndexOf("<") - 2);  
                    //截取<dimen></dimen>标签内的内容，从>右括号开始，到左括号减2，取得配置的数字  
                    Double num = Double.parseDouble  
                            (tempString.substring(tempString.indexOf(">") + 1,   
                                    tempString.indexOf("</dimen>") - 2));  
  
                    //根据不同的尺寸，计算新的值，拼接新的字符串，并且结尾处换行。  
                    sw240.append(start).append( num * 0.75).append(end).append("\r\n");  
                    sw320.append(start).append( num * 1).append(end).append("\r\n");
                    sw360.append(start).append( num * 1.125).append(end).append("\r\n");
                    sw400.append(start).append( num * 1.25).append(end).append("\r\n");
                    sw440.append(start).append( num * 1.375).append(end).append("\r\n");

                    sw480.append(start).append(num * 1.5).append(end).append("\r\n");  
  
                    sw600.append(start).append(num * 1.87).append(end).append("\r\n");  
  
                    sw720.append(start).append(num * 2.25).append(end).append("\r\n");  
  
                    sw800.append(start).append(num * 2.5).append(end).append("\r\n");  
  
                    w820.append(start).append(num * 2.56).append(end).append("\r\n");  
  
  
  
                } else {  
                    sw240.append(tempString).append("");  
                    sw320.append(tempString).append("");
                    sw360.append(tempString).append("");
                    sw400.append(tempString).append("");
                    sw440.append(tempString).append("");

                    sw480.append(tempString).append("");  
  
                    sw600.append(tempString).append("");  
  
                    sw720.append(tempString).append("");  
  
                    sw800.append(tempString).append("");  
  
                    w820.append(tempString).append("");  
  
                }  
  
                line++;  
  
            }  
  
            reader.close();  
            System.out.println("<!--  sw240 -->");  
  
            System.out.println(sw240);

            System.out.println("<!--  sw320 -->");

            System.out.println(sw320);
            System.out.println("<!--  sw360 -->");

            System.out.println(sw360);
            System.out.println("<!--  sw400 -->");

            System.out.println(sw400);
            System.out.println("<!--  sw440 -->");

            System.out.println(sw440);

            System.out.println("<!--  sw480 -->");  
  
            System.out.println(sw480);  
  
            System.out.println("<!--  sw600 -->");  
  
            System.out.println(sw600);  
  
            System.out.println("<!--  sw720 -->");  
  
            System.out.println(sw720);  
  
            System.out.println("<!--  sw800 -->");  
  
            System.out.println(sw800);  
  
            String sw240file = filePath + "/values-sw240dp";
            String sw320file = filePath + "/values-sw320dp";
            String sw360file = filePath + "/values-sw360dp";
            String sw400file = filePath + "/values-sw400dp";
            String sw440file = filePath + "/values-sw440dp";
            String sw480file = filePath + "/values-sw480dp";
            String sw600file = filePath + "/values-sw600dp";
            String sw720file = filePath + "/values-sw720dp";
            String sw800file = filePath + "/values-sw800dp";
            String w820file = filePath + "/values-w820dp";

            createDir(sw240file);
            createDir(sw320file);
            createDir(sw360file);
            createDir(sw400file);
            createDir(sw440file);
            createDir(sw480file);
            createDir(sw600file);
            createDir(sw720file);
            createDir(sw800file);
            createDir(w820file);

            sw240file = sw240file + "/dimens.xml";
            sw320file = sw320file + "/dimens.xml";
            sw360file = sw360file + "/dimens.xml";
            sw400file = sw400file + "/dimens.xml";
            sw440file = sw440file + "/dimens.xml";
            sw480file = sw480file + "/dimens.xml";
            sw600file = sw600file + "/dimens.xml";
            sw720file = sw720file + "/dimens.xml";
            sw800file = sw800file + "/dimens.xml";
            w820file = w820file + "/dimens.xml";

            //将新的内容，写入到指定的文件中去  
            writeFile(sw240file, sw240.toString());  
            writeFile(sw320file, sw320.toString());
            writeFile(sw360file, sw360.toString());
            writeFile(sw400file, sw400.toString());
            writeFile(sw440file, sw440.toString());

            writeFile(sw480file, sw480.toString());  
  
            writeFile(sw600file, sw600.toString());  
  
            writeFile(sw720file, sw720.toString());  
  
            writeFile(sw800file, sw800.toString());  
  
            writeFile(w820file, w820.toString());  
  
        } catch (IOException e) {
  
            e.printStackTrace();  
  
        } finally {  
  
            if (reader != null) {  
  
                try {  
  
                    reader.close();  
  
                } catch (IOException e1) {  
  
                    e1.printStackTrace();  
  
                }  
  
            }  
  
        }  
  
    }

    private static void createDir(String file) {
        try {
            File f = new File(file);
            if (!f.exists()) {
                f.mkdirs();
            }
        }catch (Exception e){

        }
    }


    /** 
     * 写入方法 
     * 
     */  
  
    public static void writeFile(String file, String text) {  
  
        PrintWriter out = null;
  
        try {
            File f = new File(file);
            if(!f.exists()){
                f.createNewFile();
            }
  
            out = new PrintWriter(new BufferedWriter(new FileWriter(f)));
  
            out.println(text);  
  
        } catch (IOException e) {  
  
            e.printStackTrace();  
  
        }  
  
  
  
        out.close();  
  
    }  
    public static void main(String[] args) {  
  
        gen();  
  
    }  
  
} 