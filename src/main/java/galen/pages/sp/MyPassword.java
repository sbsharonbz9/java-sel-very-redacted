package galen.pages.sp;

import galen.helpers.common.GalenReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;

public class MyPassword extends SPBasePage {
    public By header = By.xpath("//h1[text()='Change My Password']");
    public By oldPassword = By.id("old");
    public By newPassword = By.id("new");
    public By confirmPassword = By.id("con");


    public MyPassword(WebDriver driver) {
        super(driver);
        headingTitle=header;
        reportText="My Password Screen";
    }

    public boolean verifyAllElementsDisplayed(@Nullable GalenReport report) {
        LinkedHashMap<String, By> results = new LinkedHashMap<>();
        results.put("Old Password field", oldPassword);
        results.put("New Password field", newPassword);
        results.put("Confirm New Password field", confirmPassword);
        results.put("Save Button", saveButton);
        return basicHelpers.verifyElementsDisplayed(results, report);
    }

}