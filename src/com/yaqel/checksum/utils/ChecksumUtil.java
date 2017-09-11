package com.yaqel.checksum.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

public class ChecksumUtil {

	private static final Logger LOGGER = Logger.getLogger(ChecksumUtil.class);

	private ChecksumUtil() {

	}

	public static String computeSha1Hash(String fileName) {
		try (FileInputStream fis = new FileInputStream(new File(fileName))) {
			return DigestUtils.sha1Hex(fis);
		} catch (IOException e) {
			LOGGER.error("Error during computing SHA1 hash", e);
		}
		return "";
	}

	public static String computeMd5Hash(String fileName) {
		try (FileInputStream fis = new FileInputStream(new File(fileName))) {
			return DigestUtils.md5Hex(fis);
		} catch (IOException e) {
			LOGGER.error("Error during computing MD5 hash", e);
		}
		return "";
	}
	
	

}
