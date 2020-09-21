package com.brl.sc.utils.string;


import java.security.MessageDigest;

/**
 * 说明：MD5处理
 
 
 */
public class PwdUtil {
	/**
	 * 密码 MD5
	 * @param pwd 原密码
	 * @param salt 盐(每个用户有自己独立的盐值)
	 * @return
	 */
	public static String pwd(String pwd , String salt){
		return md5(pwd+"_"+salt);
	}

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}




	public static void main(String[] args) {
		System.out.println(md5("admin"+"admin"));
	}
}
