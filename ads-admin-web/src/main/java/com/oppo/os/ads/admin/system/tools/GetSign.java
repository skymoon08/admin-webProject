package com.oppo.os.ads.admin.system.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class GetSign {

	private String MD5(String sourceStr) throws Exception {
			String result = "";
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(sourceStr.getBytes());
				byte b[] = md.digest();
				int i;
				StringBuffer buf = new StringBuffer();
				for (int offset = 0; offset < b.length; offset++) {
					i = b[offset];
					if (i < 0)
						i += 256;
					if (i < 16)
						buf.append("0");
					buf.append(Integer.toHexString(i));
				}
				result = buf.toString();
			} catch (NoSuchAlgorithmException e) {
			}
			return result;
		}
	
}
