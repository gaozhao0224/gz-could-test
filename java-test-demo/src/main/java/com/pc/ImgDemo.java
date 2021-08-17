package com.pc;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Slf4j
public class ImgDemo {

    // 获取img标签正则
    private static final String IMGURL_REG = "href=\"/search.*=undefined";

    // 获取外层地址正则href
    private static final String IMGURL_HREF = "<a href=\"/arts/.*>";
    // 获取src路径的正则
    private static final String IMGSRC_REG = "[a-zA-z]+://[^\\s]*";


    public static void getImgUrls(){

    }

    public static void getMain(String url) {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
        try {
            //获得html文本内容
            String HTML = getHtml(url);

            //获取图片标签
            List<String> imgUrl = getImageUrl(HTML);
            //获取图片src地址
            List<String> urlStrings = getImageSrc(imgUrl);
            List<String> imgSrc = new ArrayList<>();
            for (String s : urlStrings) {
                imgSrc.add(s.substring(0,s.lastIndexOf("."))+".jpg");
            }
            String uuid = IdUtil.simpleUUID().toUpperCase();
            //System.out.println(imgSrc);
            imgSrc.parallelStream().forEach(i->{
                //下载图片
                try {
                    boolean b = downFileFp(i, uuid);
                    //System.out.println(b);
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            });
//            stopWatch.stop();
//            log.error("查询缓存耗时{}",stopWatch.getLastTaskTimeMillis());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("发生错误");
        }

    }
//    public static void main(String[] args) {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        try {
//            Main cm=new Main();
//            //获得html文本内容
//            String HTML = cm.getHtml(URL);
//
//            //获取图片标签
//            List<String> imgUrl = cm.getImageUrl(HTML);
//            //获取图片src地址
//            List<String> urlStrings = cm.getImageSrc(imgUrl);
//            List<String> imgSrc = new ArrayList<>();
//            for (String s : urlStrings) {
//                imgSrc.add(s.substring(0,s.lastIndexOf("."))+".jpg");
//            }
//            String uuid = IdUtil.simpleUUID().toUpperCase();
//            //System.out.println(imgSrc);
//            imgSrc.parallelStream().forEach(i->{
//                //下载图片
//                try {
//                    boolean b = downFileFp(i, uuid);
//                    System.out.println(b);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//            stopWatch.stop();
//            log.error("查询缓存耗时{}",stopWatch.getLastTaskTimeMillis());
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println("发生错误");
//        }
//
//    }

    //获取HTML内容
    public static String getHtml(String url)throws Exception{
        URL url1=new URL(url);
        URLConnection connection=url1.openConnection();
        //connection.setRequestProperty("User-Agent", "Mozilla/4.0");
        connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
        connection.setDoInput(true);
        InputStream in = connection.getInputStream();
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

    //获取ImageUrl地址
    public static List<String> getImageUrl(String html){
        Matcher matcher=Pattern.compile(IMGURL_REG).matcher(html);
        List<String>listimgurl=new ArrayList<String>();
        while (matcher.find()){
            listimgurl.add(matcher.group());
        }
        System.out.println("图片地址:"+listimgurl);
        return listimgurl;
    }

    //获取ImageSrc地址
    public static List<String> getImageSrc(List<String> listimageurl){
        List<String> listImageSrc=new ArrayList<String>();
        for (String image:listimageurl){
            Matcher matcher=Pattern.compile(IMGSRC_REG).matcher(image);
            while (matcher.find()){
                listImageSrc.add(matcher.group().substring(0, matcher.group().length()-1));
            }
        }
        return listImageSrc;
    }

    public static boolean downFileFp(String url, String uuid) throws Exception {
        try {
            String id = IdUtil.simpleUUID();
            url = url.trim();
            String suffixName = url.substring(url.lastIndexOf("."));
            URL imgUrl = new URL(url);
            URLConnection con = imgUrl.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/4.0");
            InputStream inputStream = con.getInputStream();
            int index;
            byte[] bytes = new byte[1024];
            FileOutputStream downloadFile = new FileOutputStream("D:\\桌面壁纸\\"+uuid+id+suffixName);
            while ((index = inputStream.read(bytes)) != -1) {
                downloadFile.write(bytes, 0, index);
                downloadFile.flush();
            }
            downloadFile.close();
            log.info(uuid);
            return true;
        }catch (Exception e){
            //e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<String> list = getList();
        list.parallelStream().forEach(i->{
            log.info("当前网址>>>>>>>>>>>{}",i);
            getMain(i);
        });
        stopWatch.stop();
        log.error("查询缓存耗时{}",stopWatch.getLastTaskTimeMillis());
    }



    public static List<String> getList(){
        String [] strArray = {"https://image.baidu.com/search/index?ct=201326592&cl=2&st=-1&lm=-1&nc=1&ie=utf-8&tn=baiduimage&ipn=r&rps=1&pv=&fm=rs10&word=1920x1080%E5%A3%81%E7%BA%B8%E9%BB%91%E8%89%B2&oriquery=%E5%A3%81%E7%BA%B8&ofr=%E5%A3%81%E7%BA%B8&sensitive=0"};
        List list = Arrays.asList(strArray);
        return list;
    }

//    public static List<String> getList(){
//        String [] strArray =
//        List list = Arrays.asList(strArray);
//        return list;
//    }

}