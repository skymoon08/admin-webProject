package com.oppo.os.ads.admin.system.tools;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    public static String byteToString(byte[ ]byte1){
        return new String(byte1);
    }
    //加密
    public static byte[] AES_CBC_Encrypt(byte[]content, byte[] keyBytes, byte[] iv){

        try{
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,key, new IvParameterSpec(iv));
            byte[]result=cipher.doFinal(content);
            return result;
        }catch (Exception e) {
            System.out.println("exception:"+e.toString());
        }
        return null;
    }
    //解密
    public static byte[] AES_CBC_Decrypt(byte[]content, byte[] keyBytes, byte[] iv){

        try{
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,key, new IvParameterSpec(iv));
            byte[]result=cipher.doFinal(content);
            return result;
        }catch (Exception e) {
            // TODO Auto-generated catchblock
            System.out.println("exception:"+e.toString());
        }
        return null;
    }
    /**
     * 字符串装换成base64
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return Base64.decodeBase64(key.getBytes());
    }

    /**
     *二进制装换成base64
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[]key) throws Exception {
        return new String(Base64.encodeBase64(key));
    }

}
