package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	/**
	 * This method is used to initialize the driver on the basis of the given browser name
	 */

	public WebDriver driver;
	public Properties prop;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public OptionsManager optionsManager;

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser").toLowerCase();
		System.out.println("browser name is : " + browserName);

	
		optionsManager = new OptionsManager(prop);

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			// driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
		} else {
			System.out.println("Please pass the right browser name: " + browserName);
			throw new FrameworkException(AppError.BROWSER_NOT_FOUND);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * this method is used to init the config properties
	 * 
	 * @return
	 */
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;

		// mvn clean install -Denv="dev"
		// mvn clean install

		//String envName = System.getenv("env");// stage/uat/qa/dev
		String envName = System.getProperty("env");
		System.out.println("-----> Running test cases on environment: ----->" + envName);

		if (envName == null) {
			System.out.println("No env is given..hence running it on QA env.....");
			try {
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		else {
			try {
				switch (envName) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("please pass the right env name...." + envName);
					throw new FrameworkException(AppError.ENV_NOT_FOUND);
					//break;
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	public static String getScreenshot() {
		
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		
		
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
		
	}

}


