package com.yaqel.checksum;

import java.io.File;

import org.apache.log4j.Logger;

import com.yaqel.checksum.utils.ChecksumUtil;

public class Main {

	private static final String[] SUPPORTED_ALGORITHM = { "SHA1", "MD5" };
	private static final Logger LOGGER = Logger.getLogger(Main.class);

	private String fileName;
	private String algorithm;

	public Main(String fileName, String algorithm) {
		super();
		this.fileName = fileName;
		this.algorithm = algorithm;

		if (!validateAlgorithm() || !validateFileLocation()) {
			LOGGER.info("Algorithm Not Supported or File Not found");
		} else {
			compute();
		}

	}

	public void compute() {

		String checksum;
		switch (this.algorithm) {
		case "SHA1":
			LOGGER.info("Processing SHA1 checksum");
			checksum = ChecksumUtil.computeMd5Hash(this.fileName);
			LOGGER.info("SHA1 Checksum: " + checksum);
			LOGGER.info("End of Processing");
			break;

		case "MD5":
			LOGGER.info("Processing MD5 checksum");
			checksum = ChecksumUtil.computeMd5Hash(this.fileName);
			LOGGER.info("MD5 Checksum: " + checksum);
			LOGGER.info("End of Processing");
			break;

		default:
			LOGGER.error("No Algorithm was specified");
			break;
		}

	}

	private boolean validateAlgorithm() {
		boolean found = false;
		for (String alg : SUPPORTED_ALGORITHM) {
			if (alg.equals(this.algorithm)) {
				LOGGER.info("Algorithm supported");
				found = true;
				break;
			}
		}
		return found;
	}

	private boolean validateFileLocation() {
		boolean isFileExist = false;
		File f = new File(this.fileName);
		if (f.exists() && !f.isDirectory()) {
			LOGGER.info("File exist");
			isFileExist = true;
		}
		return isFileExist;
	}

	public static void main(String[] args) {
		LOGGER.info("Start Execution");
		if (args.length > 2 || args.length <= 0)
			LOGGER.error("Invalid Arguments");
		else
			new Main(args[0], args[1]);
		LOGGER.info("End Execution");
	}

}
