/**

/***************************************************/

package galen.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManagerFirefox implements DriverManager_OC {

	@Override
	public WebDriver createDriver() {
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("headless");
		WebDriver driver = new FirefoxDriver(options);
		driver.manage().window().maximize();
		return driver;
	}

}
