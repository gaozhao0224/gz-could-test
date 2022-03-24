package com.base64;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


/**
 * 结算业务相关demo
 *
 * @author luofuwei
 * @date wrote on 2020/2/12
 */
public class TransactionDemo {

	public static void main(String[] args) {

        new TransactionDemo().signInDemo();

	}

	/**
	 *  释放资源
	 *
	 */
	private void close(OutputStream os,InputStream is,Socket socket){
		try {
			if (null != os) {
				os.close();
			}
			if (null != is) {
				is.close();
			}
			if (null != socket) {
				socket.close();
			}
		} catch ( Exception e ){
			System.out.println("释放资源异常！");
			e.printStackTrace();
		}
	}

	/**
	 *  系统签到 BDC001
     *
     *  功能说明：银行系统向结算应用系统发起签到交易，获得会话秘钥
	 *
	 */
	public void signInDemo(){

		OutputStream os = null;
		InputStream is = null;
		Socket socket = null;

		try {

			socket = new Socket("10.105.99.5", 9999);
			os = socket.getOutputStream();

			// 发送节点号
			String node = "403000";
			// 交易码（已补'0'）
			String transactionCode = "BDC0010";
			// 交易类型
			int transactionType = 1;
			// 报文体
			String messageBody= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
					"<message>" +
					"<head>" +
					"<field name=\"operNo\">130181198510217138</field>" +
					"<field name=\"sendDate\">20210525</field>" +
					"<field name=\"sendNode\">403000</field>" +
					"<field name=\"sendSeqNo\">20210525111111111</field>" +
					"<field name=\"sendTime\">123250</field>" +
					"<field name=\"txCode\">BDC001</field>" +
					"<field name=\"receiveNode\">JSPT</field>" +
					"</head>" +
					"</message>";
			// 报文组装
			byte[] message = EncodeAndDecode.encode(node,transactionType,transactionCode,messageBody);
			// 发出
			os.write(message);
			// 接收
			is = socket.getInputStream();
			String respStr = EncodeAndDecode.decodeToStr(is);
            System.out.println("响应：" + respStr);
		}catch (Exception e) {
			System.out.println("签到异常！");
			e.printStackTrace();
		}finally {
			close(os,is,socket);
		}
	}
}
