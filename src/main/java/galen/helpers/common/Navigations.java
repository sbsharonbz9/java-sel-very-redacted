package galen.helpers.common;

import org.openqa.selenium.WebDriver;


public class Navigations {

    protected WebDriver driver;
    public CommonPageFeatures commonPageFeatures;
    public BasicHelpers basicHelpers;

    public Navigations(WebDriver driver) {
        this.driver = driver;
        this.commonPageFeatures = new CommonPageFeatures(driver);
        this.basicHelpers = new BasicHelpers(driver);
    }
}

