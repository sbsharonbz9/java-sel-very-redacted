/**

/***************************************************/

package galen.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverManagerEdge implements DriverManager_OC {

	public WebDriver createDriver() {
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
        //options.addArguments("headless");
		WebDriver driver = new EdgeDriver(options);
		driver.manage().window().maximize();
		return driver;
	}



}
