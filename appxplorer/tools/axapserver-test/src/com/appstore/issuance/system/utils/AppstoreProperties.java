package com.appstore.issuance.system.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppstoreProperties {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppstoreProperties.class);
	public static AppstoreProperties appstoreProperties = new AppstoreProperties();
	public Properties properties = null;

	public static AppstoreProperties getInstance() {
		return appstoreProperties;
	}

	/**
	 * To restrict creating multiple applications
	 */
	private AppstoreProperties() {
		init();
	}

	private void init() {
		try {
			properties = new Properties();
//			File file = new File("C:/nxp/workspaces/swt/AppStore-Sample-IssuanceSystem/conf/ndef_config.properties");
			File file = new File("./conf/appstore_monorail_config.properties");
			properties.load(new FileInputStream(file));
			System.out.println(properties);
			LOGGER.info("#Properties:"+properties.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getValue(String key) {
		System.out.println("#key:" + key);
		String value = "";
		try {
			value = properties.getProperty(key);
			System.out.println("#Got the value:" + value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public static void main(String[] args) {
		System.out.println("#app.version:" + getInstance().getValue("app.version"));
		System.out.println("#app.name:" + getInstance().getValue("app.name"));
		System.out.println("#appstore.demo.cardissuer1.ndef.msg:" + getInstance().getValue("appstore.demo.cardissuer1.ndef.msg"));
		System.out.println("#appstore.certificate.file.path:" + getInstance().getValue("appstore.certificate.file.path"));
	}

}
