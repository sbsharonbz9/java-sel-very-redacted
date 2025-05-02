/**

/***************************************************/

package galen.listeners;

import galen.utils.ConfigLoader;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTests implements IRetryAnalyzer {

	private int count = 0;

	@Override
	public boolean retry(ITestResult result) {

		boolean value = false;
		if (ConfigLoader.getInstance().getRetryFailedTests().equalsIgnoreCase("yes")) {
			int retries = 1;
			if (count < retries) {
				count++;
				return true;
			}
		}
		return value;
	}
}
