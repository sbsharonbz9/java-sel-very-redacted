/**

/***************************************************/

package galen.utils;

import galen.enums.framework.EnvType;

import java.util.Properties;

public class ConfigLoader {

	private static final String RETRY_FAILED_TESTS = "retry_failed_tests";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String STUDY_URL = "studyUrl";
	private static final String METRIC_URL = "metricsUser";
	private static final String SP_DEX_SUFFIX = "spDexSuffix";
	private static final String SP_PASSWORD= "spPassword";
	private static final String DEXTER_URL = "dexterUrl";
	private static final String PETROS_URL = "petrosUrl";
	private static final String METRICS_EMAIL="metricsEmail";
	private static final String METRICS_PARTID="metricsPartID";

	private static final String CONFIG_PROPERTIES = "_config.properties";

	/* Default config file is stg_config.properties */
	private static final String STG_CONFIG_PROPERTIES = "stg" + CONFIG_PROPERTIES;
	private static final String PROD_CONFIG_PROPERTIES = "prod" + CONFIG_PROPERTIES;
	private static final String PREPROD_CONFIG_PROPERTIES = "preprod" + CONFIG_PROPERTIES;
	private static final String DEV_CONFIG_PROPERTIES = "dev" + CONFIG_PROPERTIES;
	private static final String QA_CONFIG_PROPERTIES = "qa" + CONFIG_PROPERTIES;
	private static final String INT_CONFIG_PROPERTIES = "int" + CONFIG_PROPERTIES;

	private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/test/resources/";
	private final Properties properties;
	private static ConfigLoader configLoader;

	public ConfigLoader() {

		String env = getBaseConfig("env");
		switch (EnvType.valueOf(env)) {
			case STAGE: {
				properties = getConfigPropertyFile(STG_CONFIG_PROPERTIES);
				break;
			}
			case INT: {
				properties = getConfigPropertyFile(INT_CONFIG_PROPERTIES);
				break;
			}
			case QA: {
				properties = getConfigPropertyFile(QA_CONFIG_PROPERTIES);
				break;
			}
			case PRODUCTION: {
				properties = getConfigPropertyFile(PROD_CONFIG_PROPERTIES);
				break;
			}
			case PREPROD: {
				properties = getConfigPropertyFile(PREPROD_CONFIG_PROPERTIES);
				break;
			}
			case DEV: {
				properties = getConfigPropertyFile(DEV_CONFIG_PROPERTIES);
				break;
			}
			default: {
				throw new IllegalStateException("Invalid EnvType: " + env);
			}
		}
	}

	private static Properties getConfigPropertyFile(String configFile) {
		return PropertyUtils.propertyLoader(RESOURCES_PATH + configFile);
	}

	private String getPropertyValue(String propertyKey) {
		String prop = properties.getProperty(propertyKey);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException("Property " + propertyKey + " is not specified in the config.properties file");
		}
	}

	public static ConfigLoader getInstance() {
		if (configLoader == null) {
			configLoader = new ConfigLoader();
		}
		return configLoader;
	}

	public static String getBaseConfig(String propertyKey) {
		Properties envProperties = getConfigPropertyFile("config.properties");
		return envProperties.getProperty(propertyKey);
	}

	public String getStudyUrl() {
		return getPropertyValue(STUDY_URL);
	}

	public String getMetricUrl() {
		return getPropertyValue(METRIC_URL);
	}

	public String getSPDexSuffix() {
		return getPropertyValue(SP_DEX_SUFFIX);
	}

	public String getSPPassword() {
		return getPropertyValue(SP_PASSWORD);
	}

	public String getDexterUrl() {
		return getPropertyValue(DEXTER_URL);
	}

	public String getPetrosUrl() {
		return getPropertyValue(PETROS_URL);
	}

	public String getUsername() {
		return getPropertyValue(USERNAME);
	}

	public String getPassword() {
		return getPropertyValue(PASSWORD);
	}

	public String getRetryFailedTests() {
		return getPropertyValue(RETRY_FAILED_TESTS);
	}

	public String getMetricsEmail() {
		return getPropertyValue(METRICS_EMAIL);
	}

	public String getMetricsPartID() {
		return getPropertyValue(METRICS_PARTID);
	}

}
