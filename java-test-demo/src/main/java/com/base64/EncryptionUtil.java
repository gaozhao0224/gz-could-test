package com.base64;

import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {
	public static final String KEY_ALGORITHM = "AES";
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	
	/**
	 * 转换秘钥
	 * @param key  二进制秘钥
	 * @return Key  秘钥
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception{
		SecretKey secretKey = new SecretKeySpec(key,KEY_ALGORITHM);
		return secretKey;
	}
	
	/**
	 * 解密
	 * @param data 待解密数据
	 * @param key 秘钥
	 * @return byte [] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte [] data,String key) throws Exception{
		Key k = toKey(initKey(key));
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	
	/**
	 * 加密
	 * @param data 待加密数据
	 * @param key 秘钥
	 * @return byte [] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data,String key)throws Exception{
		Key k = toKey(initKey(key));
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	
	public static byte[] initKey(String key) throws Exception{
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(128,new SecureRandom(key.getBytes()));
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();
	}
	public static void main(String[] args) throws Exception{
	    // 签到key
		String key = "4Z4DptxAkGPeZuHvKhVw2A==";
		// 内容
		String s = "你好!";
		long start = System.currentTimeMillis();
		byte[] eb = encrypt(s.getBytes("UTF-8"), key);
        System.out.println("加密后:" + new String(eb));
		byte[] db = decrypt(eb, key);
        System.out.println("解密后:" + new String(db));
		long end = System.currentTimeMillis();
		System.out.println(end-start+"ms");
	}
}
