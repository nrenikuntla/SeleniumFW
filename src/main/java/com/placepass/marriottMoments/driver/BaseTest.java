package com.placepass.marriottMoments.driver;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

 
public class BaseTest {
	public static WebDriver driver;
	public static String user_dir = System.getProperty("user.dir");
	private Properties prop = new Properties();

	public String getProperty(String configItemName) {
		try {
			FileInputStream f = new FileInputStream(user_dir + "\\selenium.properties");
			prop.load(f);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return prop.getProperty(configItemName);
	}

	@BeforeMethod
	public void startSession() {
		if (getProperty("remoteWebdriver").equalsIgnoreCase("false")) {
			if (getProperty("browser").equalsIgnoreCase("FF")) {
				System.setProperty("webdriver.gecko.driver", user_dir + "\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (getProperty("browser").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", user_dir + "\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (getProperty("browser").equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", user_dir + "\\drivers\\IEDriverServer.exe");
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				driver = new InternetExplorerDriver(ieCapabilities);
			}
		} else {
			URL gridUrl = null;
			try {
				gridUrl = new URL(getProperty("gridHubURL"));
			} catch (MalformedURLException e) {
			}
			if (getProperty("browser").equalsIgnoreCase("FF")) {
				System.setProperty("webdriver.gecko.driver", user_dir + "\\drivers\\geckodriver.exe");
				FirefoxProfile fp = new FirefoxProfile();
				// set something on the profile...
				DesiredCapabilities dc = DesiredCapabilities.firefox();
				dc.setCapability(FirefoxDriver.PROFILE, fp);
				driver = new RemoteWebDriver(gridUrl, dc);
			} else if (getProperty("browser").equalsIgnoreCase("CHROME")) {
				System.setProperty("webdriver.chrome.driver", user_dir + "\\drivers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				// set some options
				options.addArguments("auth-server-whitelist='https://marriott-stage.placepass.com/'");
				DesiredCapabilities dc = DesiredCapabilities.chrome();
				dc.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new RemoteWebDriver(gridUrl, dc);
			} else if (getProperty("browser").equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", user_dir + "\\drivers\\IEDriverServer.exe");
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				driver = new InternetExplorerDriver(ieCapabilities);
			}
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(getUrl());
	}

	@AfterMethod
	public void stopSession() {
		driver.quit();
	}

	public String getUrl() {
		String env = getProperty("env");
		String baseUrl = "";
		if (env.contentEquals("stg")) {
			baseUrl = getProperty("baseurl_stg");
		} else if (env.contentEquals("dev")) {
			baseUrl = getProperty("baseurl_dev");
		} else if (env.contentEquals("qa")) {
			baseUrl = getProperty("baseurl_qa");
		}
		return baseUrl;
	}
}
