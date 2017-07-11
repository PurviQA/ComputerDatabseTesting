package com.training.computerDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;

public class Computers {
	Properties prop = new Properties();
	Properties prop1 = new Properties();
/**
 * 
 * @param Element
 * @return locator
 * @throws IOException
 */
	public By Locators(String Element) throws IOException {

		InputStream input1 = null;
		input1 = getClass().getClassLoader().getResourceAsStream("elements.properties");
		prop1.load(input1);
		String element = prop1.getProperty(Element);
		String type = element.split(":")[0];
		String value = element.split(":")[1];
		if (type.compareTo("id") == 0) {
			return By.id(value);
		} else if (type.compareTo("cssSelector") == 0) {
			return By.cssSelector(value);
		} else if (type.compareTo("xpath") == 0) {
			return By.xpath(value);
		}
		return null;
	}
	/**
	 * 
	 * @param Element
	 * @return keyvalue
	 * @throws IOException
	 */

	public String Attributes(String Element) throws IOException {
		InputStream input = null;

		input = getClass().getClassLoader().getResourceAsStream("data.properties");
		prop.load(input);
		return prop.getProperty(Element);

	}
}