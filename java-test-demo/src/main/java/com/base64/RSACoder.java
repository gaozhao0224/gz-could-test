package com.base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSACoder {
	/** 非对称加密秘钥算法 */
	private static final  String KEY_ALGORITHM = "RSA";

	/**
	 * 私钥解密
	 * @param data 带解密数据
	 * @param key 私钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data,byte[] key) throws Exception{
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}
	/**
	 * 公钥解密
	 * @param data 待解密数据
	 * @param key 公钥
	 * @return byte [] 解密数据
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data,byte[] key)throws Exception{
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
		
	}
	/**
	 * 公钥加密
	 * @param data 待加密数据
	 * @param key 公钥
	 * @return byte [] 加密数据
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data,byte[] key)throws Exception{
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}
	/**
	 * 私钥加密
	 * @param data 待加密数据
	 * @param key 私钥
	 * @return byte [] 加密数据
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data,byte[] key)throws Exception{
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}
} 