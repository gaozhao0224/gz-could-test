package com.base64;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * RSA加解密例子
 *
 * @author luofuwei
 * @date wrote on 2020/1/16
 */
public class RSACoderDemo {
    
    /** base64公钥 */
    private static String publicKey =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCtONYY07CTG+/14a6hwKlotKrLnm+cAV1e+fpNf2yGFX360Eso6Kk95cEPLFjdVcNAapJm6oJh0V8apN1dX1qR0SIwqysBSDiItEod9WQyQICz6FS+Xk/D8x9etuXEz62Tak6KIQI3qnQBfhKbPMhunqfIvjx64IETP0+FaY6H8wIDAQAB";
    /** base64私钥 */
    private static String privateKey =
            "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAK041hjTsJMb7/XhrqHAqWi0qsueb5wBXV75+k1/bIYVffrQSyjoqT3lwQ8sWN1Vw0BqkmbqgmHRXxqk3V1fWpHRIjCrKwFIOIi0Sh31ZDJAgLPoVL5eT8PzH1625cTPrZNqToohAjeqdAF+Eps8yG6ep8i+PHrggRM/T4VpjofzAgMBAAECgYBpYOM2NUn9JIjFf2badjdZQFeLCbaCJH5l8hMopDUkGN0MAT1IBMo4opXhIixpcewXOv4XZ+Crm1CNB/C3BfQ1Lo/6CjoikSoTrVUllINfr07eGn7ktRHHdbmhShWoCOncIzcNa+CLvblawo8r3zZvetU6ULuZPm5A7ZG66X6swQJBAPzPmAnRuZ+168jql2w8z8881zPiuA5IMnefPTB9mpBkYh3i975MktL2cW505Y050boI6T64mu+uQLgMsxTEAuECQQCvaDmL3olu72SDtO6kX3VHATczVNVW9Gk03LjbkBwHAz97ahwzWSbFAPQBPwkI4MdAN+x5qoeOGNXAuz6yjblTAkEA0xCSE6iX++ILL1vD/earbI6T8MqY9buK/tngYSqML37Pd7o1MhiGAEEmJJzNxWF/Wnz0FpxTJkXp3H8lx/F5oQJBAKwHiSDfcWSGjssJYV9+a5Pm4k+KrO1qKeB5dSeJb6OFEPamO+A0WGywIZ8zRlYZtfEKp/cvQ2EehE1Qlw4hJxcCQFvIYtvyHxnTVJ+7G0zmSuKGAKbPzXxlmmpBNmIw6biCgEcXakxztDgyke6T/V0m1jepUlzMx8bfwr0zi5TZ9Eo=";

    public static void main(String[] arg)throws Exception{
        RSACoderDemo demo = new RSACoderDemo();
        demo.easyDemo2();
    }

    public void easyDemo1() throws Exception {
        System.out.println("\n-----私钥加密-----公钥解密--------");
        // 原始报文
        String inputStr1 = "这是原始报文数据！";
        System.out.println("原文："+inputStr1);
        // 转化成字节
        byte [] data1 = inputStr1.getBytes();
        // 使用私钥加密报文
        byte [] encodedData1 = RSACoder.encryptByPrivateKey(data1, Base64.decodeBase64(privateKey));
        System.out.println("私钥加密后："+ Base64.encodeBase64String(encodedData1));
        // 使用公钥解密报文
        byte [] decodedData1 = RSACoder.decryptByPublicKey(encodedData1, Base64.decodeBase64(publicKey));
        String outputStr1 = new String(decodedData1);
        System.out.println("公钥解密后："+outputStr1);

        System.out.println("\n----公钥加密-----私钥解密----");
        String inputStr2 = "这是原始报文数据！";
        byte [] data2 = inputStr2.getBytes();
        System.out.println("原文："+inputStr2);
        byte [] encodedData2 = RSACoder.encryptByPublicKey(data2, Base64.decodeBase64(publicKey));
        System.out.println("公钥加密后："+Base64.encodeBase64String(encodedData2));
        byte[] decodedData2 = RSACoder.decryptByPrivateKey(encodedData2, Base64.decodeBase64(privateKey));
        String outputStr2 = new String(decodedData2);
        System.out.println("私钥解密后："+outputStr2);
    }

    public void easyDemo2() throws Exception {
        // 原始签到key
        String sKey =
                "djvFI9Kb/7hmETfEzSmIvfuPNdKoiaN6DjIZ6rtbRrPkNsPkRySx0F9oq8627DFPNStF6Q9Y9Br207qNqpwTzgeRUVqdI/Ce4ov1K8uMFUnidMsa9paCVVV8hIUZHFOGLNRuQCW7HoHh+iTd/PVzDygpS4mzAzqfyo9Z86LWB5M=";
        System.out.println("原文："+sKey);
        // 1、先使用base64解码
        byte[] decodeByte = Base64.decodeBase64(sKey);
        // 2.使用公钥解密签到key，得到签到key原始内容
        byte [] decodedData1 = RSACoder.decryptByPublicKey(decodeByte, Base64.decodeBase64(publicKey));
        String outputStr1 = new String(decodedData1);
        System.out.println("公钥解密后："+outputStr1);
    }
}
