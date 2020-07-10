package org.yuekeju.common.util;

import org.yuekeju.common.constants.CommonConstants;

/**
 * 2020-7-10
 * @author szk
 *MD5加密
 */
public class Md5Util {
	/**
	 * 将字符串转换为MD5
	 * @param password
	 * @return
	 */
	public static String getMd5(String password) {
		byte[] bytePassword = password.getBytes();
		String s = null;
		// 用来将字节转换成 16 进制表示的字符
		char hexDigits[] = { 
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(bytePassword);
			// MD5 的计算结果是一个 128 位的长整数，
			byte tmp[] = md.digest(); 
			// 每个字节用 16 进制表示的话，使用两个字符，
			char str[] = new char[16 * 2]; 
			// 所以表示成 16 进制需要 32 个字符       表示转换结果中对应的字符位置
			int k = 0; 
			// 从第一个字节开始，对 MD5 的每一个字节
			for (int i = 0; i < CommonConstants.MD5_LENGTH; i++) { 
				// 转换成 16 进制字符的转换
				byte byte0 = tmp[i]; 
				// 取字节中高 4 位的数字转换,
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; 
				// >>> 为逻辑右移，将符号位一起右移   取字节中低 4 位的数字转换
				str[k++] = hexDigits[byte0 & 0xf]; 
			}
			//换后的结果转换为字符串
			s = new String(str); 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
}
