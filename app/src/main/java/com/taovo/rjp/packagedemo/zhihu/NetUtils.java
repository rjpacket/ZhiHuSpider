package com.taovo.rjp.packagedemo.zhihu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author Gimpo create on 2017/10/9 16:51
 * @email : jimbo922@163.com
 */

public class NetUtils {

    public static String get(String urlStr) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            //通过URL的openStrean方法获取URL对象所表示的自愿字节输入流
            InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            //为字符输入流添加缓冲
            BufferedReader br = new BufferedReader(isr);
            String data = br.readLine();//读取数据
            sb.append(data);
            while (data != null) {//循环读取数据
//                    System.out.println(data);//输出数据
                data = br.readLine();
                sb.append(data);
            }
            br.close();
            isr.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
