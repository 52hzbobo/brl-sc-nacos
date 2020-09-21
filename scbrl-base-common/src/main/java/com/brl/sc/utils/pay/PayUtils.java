package com.brl.sc.utils.pay;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.util.Map;

/**
 * @author chenxueqi
 * @description
 * @create 2020-05-19 14:10
 */
public class PayUtils {

    /**
     * 生成签名
     * @param params 待加密参数
     * @param secretKey 密钥
     * @return sign 加密后的签名字符串
     * */
    public static String generateSign(Map<String,String> params,String secretKey){
        StringBuffer signTemp = new StringBuffer();
        params.entrySet().stream()
                .filter(entry -> StringUtils.isNotBlank(entry.getKey()) && StringUtils.isNotBlank(entry.getValue()))
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> signTemp.append(x.getKey()).append("=").append(x.getValue()).append("&"));
        signTemp.append("key=").append(secretKey);
        String sign = MD5(signTemp.toString());
        return sign;
    }

    //orderId = 750027719948894208
    public static String generateOrderId(){
        return String.valueOf(IdWorkerUtil.getId());
    }

    /**
     * 32位MD5加密的大写字符串
     *
     * @param s
     * @return
     */
    public final static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static void main(String[] args) {
//        Map <String,String>map = new HashMap();
//        map.put("pay_amount", "1");
//        map.put("pay_applydate","2020-05-19 15:09:31");
//        map.put("pay_bankcode", "1");
//        map.put("pay_callbackurl", "http://122.com");
//        map.put("pay_memberid","200580613");
//        map.put("pay_notifyurl", "http://122.com");
//        map.put("pay_orderid", "456456");
//        map.put("pay_attach", "");
////        map.put("pay_productname","222");//pay_productname不参与签名
//        String sign = generateSign(map,"sjs6xibuzlwsh4veydxwyukscacs7y7h");
//        System.out.println(sign);
//    }

}
