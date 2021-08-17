package com.pc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoDemo {
    // 获取外层地址正则href
    private static final String IMGURL_HREF = "/Html/\\d*.html";

    public static void main(String[] args) throws Exception {
        List<String> listimgurl=new ArrayList<String>();
        for (int i = 2; i <= 28; i++) {
            //获得html文本内容
            String HTML = getHtml("https://www.pexels.com/zh-tw/search");
            Matcher matcher= Pattern.compile(IMGURL_HREF).matcher(HTML);
            while (matcher.find()){
                listimgurl.add("'https://www."+matcher.group()+"'");
            }
        }
        //爬取迅雷下载使用
        System.out.println(listimgurl);
        System.out.println(listimgurl.size());
    }

    //获取HTML内容
    public static String getHtml(String url)throws Exception{
        URL url1=new URL(url);
        URLConnection connection=url1.openConnection();
        InputStream in=connection.getInputStream();
        InputStreamReader isr=new InputStreamReader(in);
        BufferedReader br=new BufferedReader(isr);

        String line;
        StringBuffer sb=new StringBuffer();
        while((line=br.readLine())!=null){
            sb.append(line,0,line.length());
            sb.append('\n');
        }
        br.close();
        isr.close();
        in.close();
        return sb.toString();
    }
}
