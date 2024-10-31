package galen.base;

import galen.driver.DriverManager;
import galen.driver.DriverManagerFactory;
import galen.helpers.common.GalenReport;
import galen.utils.ConfigLoader;
import org.openqa.selenium.WebDriver;
import galen.enums.framework.DriverType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;

public class BaseTest {
	public GalenReport report;
	public WebDriver driver;

	protected WebDriver getDriver() {
		return driver;
	}

	private void setDriver(WebDriver driver) {
		DriverManager.setDriver(driver);
	}

	/*
	 * @Optional -> You can run the test case individually directly from Java class
	 */
	@Parameters("browser")
	@BeforeMethod
	public synchronized void startDriver(@Optional String browser) {

		System.out.println("@BeforeMethod: @BeforeMethod" + browser);

		if (browser==null)
		{
			browser = ConfigLoader.getBaseConfig("browser");
		}
		driver = DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver();
		setDriver(driver);
		System.out.println("Current Thread info = " + Thread.currentThread().getId() + ", Driver = " + getDriver());
	}

	@Parameters("browser")
	@AfterMethod
	public synchronized void quitDriver(@Optional String browser, ITestResult result) throws IOException {

		if (report!=null) {
			report.printFinalReport();
			report.closeDocument();
		}
		if (driver!=null) {
			driver.quit();
		}
	}

}
