package org.yuekeju.common.util;

import org.yuekeju.common.constants.CommonConstants;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * 2020-7-10
 * @author szk
 *AES 加密
 */
public class AesUtil {
    private AesUtil() {

    }

    /**
     * 将二进制转换成16进制
     * 
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }


    /**
     * 将16进制转换为二进制
     * 
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1){
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / CommonConstants.AES_EXCEPT; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 加密
     * @param content
     * @param password
     * @return
     */
    public static byte[] encryptAes(String content, String password) {
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            // 创建AES的Key生产者
            KeyGenerator kgen = KeyGenerator.getInstance(CommonConstants.AES);
           // 利用用户密码作为随机数初始化出    128位的key生产者
            kgen.init(128, random);
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
            // 根据用户密码，生成一个密钥
            SecretKey secretKey = kgen.generateKey();
            // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, CommonConstants.AES);
            // 创建密码器
            Cipher cipher = Cipher.getInstance(CommonConstants.AES);
            byte[] byteContent = content.getBytes(CommonConstants.UTF8);
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * 
     * @param content
     *            待解密内容
     * @param password
     *            解密密钥
     * @return
     */
    public static byte[] decryptAes(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(CommonConstants.AES);
            //①
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            kgen.init(128, random);
            //生成一个密钥
            SecretKey secretKey = kgen.generateKey();
            // 转换为DES专用密钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), password);
             // 创建密码器
            Cipher cipher = Cipher.getInstance(CommonConstants.AES);
             // 初始化
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] result = cipher.doFinal(content);
            return result; 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
