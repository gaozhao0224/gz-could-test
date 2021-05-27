package com.demo;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class ReadTxt {
    /**传入txt路径读取txt文件
     * @param txtPath
     * @return 返回读取到的内容
     */
    public static String readTxt(String txtPath) {
        log.info("读取文件的地址:{}",txtPath);
        File file = new File(txtPath);
        if(file.isFile() && file.exists()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuffer sb = new StringBuffer();
                String text = null;
                while((text = bufferedReader.readLine()) != null){
                    text+='\n';
                    sb.append(text);
                }
                log.info("读取文件的内容:{}",sb.toString());
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**使用FileOutputStream来写入txt文件
     * @param txtPath txt文件路径
     * @param content 需要写入的文本
     */
    public static void writeTxt(String txtPath,String content){
        log.info("写入文件的地址:{},写入文件的内容:{}",txtPath,content);
        FileOutputStream fileOutputStream = null;
        File file = new File(txtPath);
        try {
            if(file.exists()){
                //判断文件是否存在，如果不存在就新建一个txt
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //验证方法：先写入文件后读取打印如下：
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = readTxt("F:/error.log");
        int num = 3;
        writeTxt("F:/error1.log",  Base64.encryption(str,num));
        writeTxt("F:/error2.log", Base64.decode(Base64.encryption(str,num),num));
    }
}