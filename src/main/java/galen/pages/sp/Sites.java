package galen.pages.sp;

import galen.helpers.common.GalenReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Sites extends SPBasePage {

    public By sitesHeader = By.xpath("//th[text()='Site ID']");
    public By activeSiteString = By.xpath("//td[.//div[contains(@class,'checked')]]/preceding-sibling::*");
    public By inactiveSiteString = By.xpath("//div[contains(@class,'react-toggle') and not (" +
            "contains(@class, 'checked'))]/preceding-sibling::*");

    public Sites(WebDriver driver) {
        super(driver);
        headingTitle=sitesHeader;
        reportText="Sites Screen";
    }

    public Boolean verifySiteIDFormat(@Nullable GalenReport report) {
        HashMap<String, Object> result = new LinkedHashMap<>();
        List<WebElement> options =
                basicHelpers.getAllWebElements(By.xpath("//tbody/tr/td[1]"));
        for (WebElement option : options) {
            result.put("Site ID option " + option.getText(), option.getText().length()==3);
        }
        if (report!=null) {
            report.addMultipleVerificationStep("All Site IDs have three character format", result,
                    false);
        }
        return result.containsValue(false);
    }

    //Param 1 - "On" or "Off"
    public String setActivationRandomSite( String onOff,@Nullable GalenReport report) {
        By listedSitesBy = (onOff.equals("Off"))?activeSiteString:inactiveSiteString;
        List<WebElement> listedSites = basicHelpers.getAllWebElements(listedSitesBy);
        int index=basicHelpers.getRandomValue(listedSites.size());
        String siteName;
        siteName = (listedSites.size()!=0) ? listedSites.get(index).getText():"01";
        clickToggleFromSite(siteName, report);
        return siteName;
    }

    public void clickToggleFromSite(String siteName, @Nullable GalenReport report) {
        WebElement toggle =basicHelpers.getWebElement(By.xpath("//tbody/tr/td[text()='"+siteName+"']/following-sibling::td/div"));
        basicHelpers.clickFlex(toggle, siteName, report);
    }

}