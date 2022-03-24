package com.base64;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class EncodeAndDecode {
	public static int byteArrayToInt(byte[] bytes){
		int i0= bytes[3] & 0xFF  ;
		int i1 = (bytes[2] & 0xFF) << 8 ;
		int i2 = (bytes[1] & 0xFF) << 16 ;
		int i3 = (bytes[0] & 0xFF) << 24 ;
		return i0 | i1 | i2 | i3 ;
	}
	
	public static void decode(InputStream is) {
		try {
			byte[] ret_node = new byte[6];
			is.read(ret_node);
			System.out.println("ret_node:"+new String(ret_node,"UTF-8"));
			byte[] ret_jylx = new byte[1];
			is.read(ret_jylx);
			System.out.println("ret_jylx:"+ret_jylx[0]);
			byte[] ret_jym = new byte[7];
			is.read(ret_jym);
			System.out.println("ret_jym:"+new String(ret_jym,"UTF-8"));
			byte[] ret_zwf = new byte[2];
			is.read(ret_zwf);
			byte[] ret_size = new byte[4];
			is.read(ret_size);
			System.out.println("ret_size:"+byteArrayToInt(ret_size));
			byte[] ret_body = new byte[byteArrayToInt(ret_size)];
			is.read(ret_body);
			System.out.println("ret_body:"+new String(ret_body,"UTF-8"));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch ( IOException e ) {
				e.printStackTrace();
			}
		}
		
	}

    public static String decodeToStr(InputStream is) {
        try {
            byte[] ret_node = new byte[6];
            is.read(ret_node);
            System.out.println("ret_node:"+new String(ret_node,"UTF-8"));
            byte[] ret_jylx = new byte[1];
            is.read(ret_jylx);
            System.out.println("ret_jylx:"+ret_jylx[0]);
            byte[] ret_jym = new byte[7];
            is.read(ret_jym);
            System.out.println("ret_jym:"+new String(ret_jym,"UTF-8"));
            byte[] ret_zwf = new byte[2];
            is.read(ret_zwf);
            byte[] ret_size = new byte[4];
            is.read(ret_size);
            System.out.println("ret_size:"+byteArrayToInt(ret_size));
            byte[] ret_body = new byte[byteArrayToInt(ret_size)];
            is.read(ret_body);
            System.out.println("ret_body:"+new String(ret_body,"UTF-8"));
            return  new String(ret_body,"UTF-8");
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }

        return null;

    }
	
	/**
	 * 
	 * @param node  节点号
	 * @param transactionType  交易类型
	 * @param transactionCode   交易码
	 * @param messageBody  报文头
	 * @return byte[]
	 */
	public static byte[] encode(String node,int transactionType,String transactionCode,String messageBody) {
		try {
			byte[] b_node = node.getBytes(StandardCharsets.UTF_8);
			byte b_type = (byte) transactionType;
			byte[] b_jym = transactionCode.getBytes(StandardCharsets.UTF_8);
			byte b_bu1 = (byte) 0;
			byte b_bu2 = (byte) 0;
			byte[] b_body = messageBody.getBytes(StandardCharsets.UTF_8);
			byte[] b_size = intToByteArray(b_body.length);
			int allsize = b_node.length+1+b_jym.length+1+1+b_size.length+b_body.length;
			byte[] b_all = new byte[allsize];
			int n = 0;
			for(int i=0;i<b_node.length;i++){
				b_all[n]=b_node[i];
				n++;
			}
			b_all[n]=b_type;
			n++;
			for(int i=0;i<b_jym.length;i++){
				b_all[n]=b_jym[i];
				n++;
			}
			b_all[n]=b_bu1;
			n++;
			b_all[n]=b_bu2;
			n++;
			for(int i=0;i<b_size.length;i++){
				b_all[n]=b_size[i];
				n++;
			}
			for(int i=0;i<b_body.length;i++){
				b_all[n]=b_body[i];
				n++;
			}
			return b_all;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] intToByteArray(int a) {
		return new byte[] {
				(byte) ((a >> 24) & 0xFF),
				(byte) ((a >> 16) & 0xFF),
				(byte) ((a >> 8) & 0xFF),
				(byte) (a & 0xFF) };
	}
	
}
