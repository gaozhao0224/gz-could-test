package com.example.controller;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileDownController {
    // 获取img标签正则
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
    // 获取src路径的正则
    private static final String IMGSRC_REG = "[a-zA-z]+://[^\\s]*";
    @Value("${pathUrl}")
    private String pathUrl;
    @Value("${EpathUrl}")
    private String EpathUrl;

    @RequestMapping("/down")
    public boolean downFile(String url ,String name) throws Exception {
        try {
            url = url.trim();
            String suffixName = url.substring(url.lastIndexOf("."));
            String uuid = IdUtil.simpleUUID();
            URL imgUrl = new URL(url);
            FileUtils.copyURLToFile(imgUrl,new File(EpathUrl+name+uuid+suffixName));
            log.info(uuid);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /*
    * 防爬接口
    * */
    @RequestMapping("/downFp")
    public boolean downFileFp(String url ,String name) throws Exception {
        try {
            String uuid = IdUtil.simpleUUID();
            url = url.trim();
            String suffixName = url.substring(url.lastIndexOf("."));
            URL imgUrl = new URL(url);
            URLConnection con = imgUrl.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/4.0");
            InputStream inputStream = con.getInputStream();
            int index;
            byte[] bytes = new byte[1024];
            FileOutputStream downloadFile = new FileOutputStream("F:\\xl\\img\\"+name+"_"+uuid+suffixName);
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

    @RequestMapping("/unbindBase")
    public boolean unbindBase(String imgStr ,String name,String type) throws Exception {
        if(imgStr == null){
            return false;
        }
        imgStr = imgStr.replace("data:image/jpg;base64,", "");
        BASE64Decoder decoder = new BASE64Decoder();
        try{
            //解密
            byte[] b = decoder.decodeBuffer(imgStr);
            //处理数据
            for (int i = 0;i<b.length;++i){
                if(b[i]<0){
                    b[i]+=256;
                }
            }
            OutputStream out = new FileOutputStream(pathUrl+name+IdUtil.simpleUUID()+type);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * java 爬虫
     * */
    @RequestMapping("/javaReptile")
    public boolean javaReptile(String imgStr ,String name,String type) throws Exception {
        try {
            //获得html文本内容
            String HTML = getHtml("URL");

            //获取图片标签
            List<String> imgUrl = getImageUrl(HTML);
            //获取图片src地址
            List<String> urlStrings = getImageSrc(imgUrl);
            List<String> imgSrc = new ArrayList<>();
            for (String s : urlStrings) {
                s = s+"g";
                imgSrc.add(s);
            }
            String uuid = IdUtil.simpleUUID();
            //System.out.println(imgSrc);
            imgSrc.parallelStream().forEach(i->{
            //下载图片
                try {
                    downFileJavaPc(i,uuid);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return true;
        }catch (Exception e){
            System.out.println("发生错误");
            return false;
        }
    }

    //获取HTML内容
    private String getHtml(String url)throws Exception{
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

    //获取ImageUrl地址
    private List<String> getImageUrl(String html){
        Matcher matcher= Pattern.compile(IMGURL_REG).matcher(html);
        List<String>listimgurl=new ArrayList<String>();
        while (matcher.find()){
            listimgurl.add(matcher.group());
        }
        return listimgurl;
    }

    //获取ImageSrc地址
    private List<String> getImageSrc(List<String> listimageurl){
        List<String> listImageSrc=new ArrayList<String>();
        for (String image:listimageurl){
            Matcher matcher=Pattern.compile(IMGSRC_REG).matcher(image);
            while (matcher.find()){
                listImageSrc.add(matcher.group().substring(0, matcher.group().length()-1));
            }
        }
        return listImageSrc;
    }
    public boolean downFileJavaPc(String url,String uuid) throws Exception {
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
            FileOutputStream downloadFile = new FileOutputStream("F:\\xl\\img\\"+uuid+id+suffixName);
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




//
//    public static void main(String[] args) {
//        try {
//
//            String imgUrl = "https://pic.loadpicz.com/images/mtsw/Wa3tiphxz17/xeuhqgcxtox_71_64.jpg";
////            String url = "https://img9.doubanio.com/view/photo/m/public/p2640611704.jpg";
//            String suffixName = imgUrl.substring(imgUrl.lastIndexOf("."));
//            String uuid = IdUtil.simpleUUID();
//            URL url = new URL(imgUrl);
//            URLConnection con = url.openConnection();
//            con.setRequestProperty("User-Agent", "Mozilla/4.0");
//            InputStream inputStream = con.getInputStream();
//
//            int index;
//            byte[] bytes = new byte[1024];
//            FileOutputStream downloadFile = new FileOutputStream("F:\\xl\\img\\"+"123.jpg");
//            while ((index = inputStream.read(bytes)) != -1) {
//                downloadFile.write(bytes, 0, index);
//                downloadFile.flush();
//            }
//            downloadFile.close();
//
//
////            FileUtils.copyURLToFile(url,new File("F:\\xl\\img\\"+uuid+suffixName));
////            log.info(uuid);
////            System.out.println(uuid);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}