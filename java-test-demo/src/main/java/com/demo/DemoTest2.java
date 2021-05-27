package com.demo;

import com.lambda.Person;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class DemoTest2 {
    @Test
    public void test1(){
        ArrayList<Double> one = new ArrayList<>();
        ArrayList<Double> two = new ArrayList<>();
        one.add(1d);
        one.add(2d);
        one.add(3d);
        one.add(4d);
        two.add(5d);
        two.add(6d);
        two.add(7d);
        two.add(8d);
        one.stream().map(o->{

            return o;
        }).collect(Collectors.toList());
    }
    @Test
    public void test2(){
        int a[]={1,2,3,4};
        int b[]={5,6,7,8};
        int temp[]=new int[a.length+b.length];
        //连接两个数组
        for(int i=0;i<a.length;i++){
            temp[i]=a[i];
        }
        for(int i=0;i<b.length;i++){
            temp[a.length+i]=b[i];
        }
        //连接数组完成,开始清除重复元素
        int size=temp.length;
        for(int i=0;i<temp.length;i++){
            if(temp[i]!=-1){
                for(int j=i+1;j<temp.length;j++){
                    if(temp[i]==temp[j]){
                        temp[j]=-1;//将发生重复的元素赋值为-1
                        size--;
                    }
                }
            }
        }
        int[] result=new int[size];
        for(int i=0,j=0;j<size && i<temp.length;i++,j++){
            if(temp[i]==-1){
                j--;
            }
            else{
                result[j]=temp[i];
            }
        }
            //打印结果
        System.err.println(Arrays.toString(result));
    }
    @Test
    public void test3(){
        String str = "cXFxcXFxcXFxd3d3d3d3d2VlZWVlZWVlZQ==";
        byte[] b = str.getBytes();
        String stmp="";
        StringBuilder sb = new StringBuilder("");

        try {
            for (int n=0;n<b.length;n++)
            {
                stmp = Integer.toHexString(b[n] & 0xFF);
                sb.append((stmp.length()==1)? "0"+stmp : stmp);
                //sb.append(" ");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String trim = sb.toString().toUpperCase().trim();
        System.out.println(trim);
    }
    @Test
    public void test4() throws ParseException {
        Date date=new Date(System.currentTimeMillis());
        java.sql.Date sqldate=new java.sql.Date(java.sql.Date.parse(String.valueOf(date)));
        System.out.println(sqldate);

    }
    @Test
    public void test5(){
        java.sql.Date now= new java.sql.Date(System.currentTimeMillis());
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=sim.format(now);
        System.out.println(time);
    }
    @Test
    public void test6(){
        List names=new ArrayList<String>();
        List namea=new ArrayList<String>();

        names.add("1");

        names.add("2");

        names.add("3");

        boolean empty = namea.isEmpty();
        System.out.println(empty);
        System.out.println(String.join("-", names));



        String[] arrStr=new String[]{"a","b","c"};

        System.out.println(String.join("-", arrStr));


    }
    @Test
    public void test7(){

        //final String base = "@#&$%*o!;.";// 字符串由复杂到简单
        final String base = "KSPksp;.";
        try {
            final BufferedImage image = ImageIO.read(new File("D:\\Users\\aaa.jpg"));
            System.out.println("W:"+image.getWidth()+" H:"+image.getHeight());
            for (int y = 0; y < image.getHeight(); y += 2) {
                for (int x = 0; x < image.getWidth(); x++) {
                    final int pixel = image.getRGB(x, y);
                    final int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;
                    final float gray = 0.299f * r + 0.578f * g + 0.114f * b;
                    final int index = Math.round(gray * (base.length() + 1) / 255);
                    System.out.print(index >= base.length() ? " " : String.valueOf(base.charAt(index)));
                }
                System.out.println();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test8(){
        StringBuffer a = new StringBuffer("123456789");
        a.delete(0, 4);
        System.out.println(a);
    }
    @Test
    public void test9(){
        List<Dog> dogs = getDog();
        Map<String, List<Dog>> collect = dogs.stream().collect(Collectors.groupingBy(Dog::getSex));
        //多字段分组
        Map<String, List<Dog>> collect1 = dogs.stream().collect(Collectors.groupingBy(d->d.getSex()+"_"+d.getId()));
        System.out.println(collect);

    }
    public List<Dog> getDog(){
        List<Dog> dogs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Dog dog = new Dog();
            dog.setAge("20");
            dog.setId("1");
            dog.setName("No_1_001");
            dog.setSex("男");
            dogs.add(dog);
        }
        for (int i = 0; i < 5; i++) {
            Dog dog = new Dog();
            dog.setAge("20");
            dog.setId("2");
            dog.setName("No_1_001");
            dog.setSex("男");
            dogs.add(dog);
        }
        for (int i = 0; i < 5; i++) {
            Dog dog = new Dog();
            dog.setAge("20");
            dog.setId("3");
            dog.setName("No_1_001");
            dog.setSex("女");
            dogs.add(dog);
        }


        return dogs;
    }
    @Test
    public void test10(){
        String a = "1.11";
        String b = "2.22";
        BigDecimal bigDecimal = new BigDecimal(a);
        Float aFloat = Float.valueOf(a);
        Float aFloat1 = Float.valueOf(b);

        BigDecimal add = getBigDecimal(a).add(getBigDecimal(b));
        System.out.println(add);
    }
    @Test
    public void test11(){
        BigDecimal a = new BigDecimal(0);
        BigDecimal b = new BigDecimal(1111);

        BigDecimal add = a.add(b);
        System.out.println(add);
    }
    public BigDecimal getBigDecimal(Object obj){

        float number = 0L;
        if(obj!=null){
            Number num = Float.parseFloat(obj.toString());
            int oamount = num.intValue();
            number = Long.valueOf(oamount);
        }
        BigDecimal bigDecimal = BigDecimal.valueOf(Float.valueOf(number));
        return bigDecimal;
    }
}