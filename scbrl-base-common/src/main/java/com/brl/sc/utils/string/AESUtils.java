package com.brl.sc.utils.string;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


public class AESUtils {

    private static final String CHARSET = "UTF-8";
    private static final String AES = "AES";
    private static final String SHA_1 = "SHA-1";
    private static final int KEY_SIZE = 128;

    /**
     * 生成随机密钥（默认128）
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generateSecret() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance(AES);
        generator.init(KEY_SIZE, new SecureRandom());
        SecretKey key = generator.generateKey();
        return byteToHexString(key.getEncoded()).toUpperCase();
    }

    /**
     * 生成随机密钥
     * @param keySize 密钥大小推荐128 256
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generateSecret(int keySize) throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance(AES);
        generator.init(keySize, new SecureRandom());
        SecretKey key = generator.generateKey();
        return byteToHexString(key.getEncoded()).toUpperCase();
    }

    /**
     * 加密AES 成功返回数据 失败为null
     * @param strToEncrypt
     * @param secret
     * @return
     */
    public static String encryptTry(String strToEncrypt, String secret){
        try {
            return encrypt(strToEncrypt, secret);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String strToEncrypt, String secret) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec secretKey = getKey(secret);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(CHARSET)));
    }

    /**
     * 解析-AES 成功返回数据 失败为null
     * @param strToEncrypt
     * @param secret
     * @return
     */
    public static String decryptTry(String strToEncrypt, String secret){
        try {
            return decrypt(strToEncrypt, secret);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String secret) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec secretKey = getKey(secret);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }

    private static SecretKeySpec getKey(String myKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] key = myKey.getBytes(CHARSET);
        MessageDigest sha = MessageDigest.getInstance(SHA_1);
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        return new SecretKeySpec(key, AES);
    }
    /**
     * byte数组转化为16进制字符串
     * @param bytes
     * @return
     */
    private static String byteToHexString(byte[] bytes){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String strHex=Integer.toHexString(bytes[i]);
            if(strHex.length() > 3){
                sb.append(strHex.substring(6));
            } else {
                if(strHex.length() < 2){
                    sb.append("0" + strHex);
                } else {
                    sb.append(strHex);
                }
            }
        }
        return  sb.toString();
    }

    public static void main(String[] args) {
        testExample();
    }

    public static void testExample(){
        // 模拟-AES秘钥（登陆成功返回的salt值为秘钥）
        String key = "D5FE7B3B77F0E727DA21A79157CF2A1F";
        // 模拟-敏感接口请求参数
        Map<String,Object> bodyParam = new HashMap<>();
        bodyParam.put("payPwd","123456");// 加密前明文参数-密码 (明文请勿在网络传递)
        bodyParam.put("title","恭喜发财,大吉大利!");//加密前明文参数-标题  (明文请勿在网络传递)
        bodyParam.put("emChatId","zk9yvf57g96c3v3n45ist5");// 加密前明文参数-环信ID  (明文请勿在网络传递)
        bodyParam.put("money",new BigDecimal(10.00)); // 加密前明文参数-金额数字  (明文请勿在网络传递)
        bodyParam.put("timestamp", System.currentTimeMillis()+"");//加密前明文参数-服务器时间戳【字符串】,验证重放攻击使用(单位:毫秒 , 例:1600071320059 (字符串)))  (明文请勿在网络传递)
        // 将明文参数对象转换成json字符串
        String enJson = GsonUtil.GsonString(bodyParam);
        // 对明文Json字符串进行AES加密成密文,加密后参数放置
        String en = encryptTry(enJson,key);
        System.err.println("加密:"+en);
        System.err.println("解密:"+decryptTry(en,key));
    }



}
