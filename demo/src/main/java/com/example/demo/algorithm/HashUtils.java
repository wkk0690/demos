package com.example.demo.algorithm;

import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 单向散列函数
 * sha1 sha2 sha3 md5
 */
public class HashUtils {

    public static void main(String args[]) throws Exception {
        String str = "你好";
        System.out.println("SHA1后：" + sha1Encode(str));
        System.out.println("SHA2后：" + sha2Encode(str));
        System.out.println("sha256Encode后：" + sha2Encode(str));
        System.out.println("md5后：" + md5(str));
    }

    public static String md5(String str) throws NoSuchAlgorithmException {
        byte[] secretBytes = MessageDigest.getInstance("md5").digest(str.getBytes());
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    /**
     * SHA1实现
     * HA-1是一种数据加密算法，该算法的思想是接收一段明文，然后以一种不可逆的方式将它转换成一段（通常更小）密文
     */
    public static String sha1Encode(String inStr) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA");
        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * SHA2实现
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String sha2Encode(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(input.getBytes());
        byte[] hashValue = messageDigest.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashValue) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public static String sha256Encode(String input) {
        byte[] bytes = input.getBytes();
        SHAKEDigest digest = new SHAKEDigest(256);
        digest.update(bytes, 0, bytes.length);
        byte[] output = new byte[64];
        digest.doFinal(output, 0, output.length);

        return Hex.toHexString(output);
    }
}
 