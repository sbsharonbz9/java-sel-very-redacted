/**
/***************************************************/

package galen.factories;

import galen.driver.DriverManagerChrome;
import galen.driver.DriverManagerEdge;
import galen.driver.DriverManagerFirefox;
import galen.driver.DriverManager_OC;
import galen.enums.framework.DriverType;

public class DriverManagerFactory {

	public static DriverManager_OC getManager(DriverType driverType) {

		switch (driverType) {

		case CHROME: {
			return new DriverManagerChrome();
		}
		case FIREFOX: {
			return new DriverManagerFirefox();
		}
		case EDGE: {
			return new DriverManagerEdge();
		}
//		case SAFARI: {
//			return new DriverManagerSafari();
//		}
//		case OPERA: {
//			return new DriverManagerOpera();
//		}
		default:
			throw new IllegalArgumentException("Invalid Driver: " + driverType);
		}

	}

}
