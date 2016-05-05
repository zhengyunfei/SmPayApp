package com.zero2ipo.qrCode.webc;

import java.security.MessageDigest;

public class SHA1Util {

	public  static String getSha1(String data) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA1");
		md.update(data.getBytes());
		StringBuffer sb = new StringBuffer();
		byte[] bits = md.digest();
		for (int i = 0; i < bits.length; i++) {
			int a = bits[i];
			if (a < 0)
				a += 256;
			if (a < 16)
				sb.append("0");
			sb.append(Integer.toHexString(a));
		}
		return sb.toString();

	}

}
