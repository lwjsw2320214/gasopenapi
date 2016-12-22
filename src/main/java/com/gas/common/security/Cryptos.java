package com.gas.common.security;

import org.apache.shiro.codec.Base64;
import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by GC on 2016/12/21.
 */
public class Cryptos {

    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";
    private static  final  String CIPHERKEY="11111111";

    public static String getSign(String timestamp, String userToken, String nonceStr,String deviceId) {
        try {
            String encryptText = String.format("%1$s%2$s%3$s%4$s",deviceId.toLowerCase(), userToken.toLowerCase(), timestamp, nonceStr.toLowerCase());
            return hmacSha1(encryptText, CIPHERKEY);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }


        return "";
    }

    /**
     * HmacSHA1 加密
     * */
    public static String hmacSha1(String base, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        if (StringUtils.isEmpty(base)||StringUtils.isEmpty(key)){
            return  "";
        }
        String type = MAC_NAME;
        SecretKeySpec secret = new SecretKeySpec(key.getBytes(), type);
        Mac mac = Mac.getInstance(type);
        mac.init(secret);
        byte[] digest = mac.doFinal(base.getBytes());
        return bytesToHexString(digest);

    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv + "");
        }
        return stringBuilder.toString();
    }

    /**
     * MD5加密
     * */
    public static String md5Encryption(String str){
        String newStr=null;
        try{
            MessageDigest messageDigest=MessageDigest.getInstance("MD5");
            newStr= Base64.encodeToString(messageDigest.digest(str.getBytes()));
        }catch (Exception e){

        }
        return newStr;
    }

}
