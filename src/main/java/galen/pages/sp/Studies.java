package galen.pages.sp;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


public class Studies extends SPBasePage {

    public By studyHeader = By.xpath("//th[text()='Study ID']");
    public By activeStudyString =By.xpath("//td[.//div[contains(@class,'checked')]]/preceding-sibling::*");
    public By inactiveStudyString = By.xpath("//div[contains(@class,'react-toggle') and not( " +
            "contains(@class, 'react-toggle-checked'))]/preceding-sibling::*");

    public Studies(WebDriver driver) {
        super(driver);
        headingTitle=studyHeader;
        reportText="Studies Screen";
    }

    //Param 1 - "On" or "Off"
    public String setActivationRandomStudy( String onOff,@Nullable GalenReport report) {
        String study;
        By listedStudysBy = (onOff.equals("Off"))?activeStudyString:inactiveStudyString;
        List<WebElement> listedStudys = basicHelpers.getAllWebElements(listedStudysBy);
        int index=basicHelpers.getRandomValue(listedStudys.size());
        study = (listedStudys.size()!=0) ? listedStudys.get(index).getText():"01";
        clickToggleFromStudy(study, report);
        return study;
    }

    public void clickToggleFromStudy(String study, @Nullable GalenReport report) {
        WebElement toggle =basicHelpers.getWebElement(By.xpath("//tbody/tr/td[text()='"+study+"']/following-sibling::td/div"));
        basicHelpers.clickFlex(toggle, study, report);
    }

    public Boolean verifyStudyIDFormat(@Nullable GalenReport report) {
        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        List<WebElement> options =
                basicHelpers.getAllWebElements(By.xpath("//tbody/tr/td[1]"));
        for (WebElement option : options) {
            result.put("Study ID option " + option.getText(), option.getText().length()==2);
        }
        if (report!=null) {
            report.addMultipleVerificationStep("All Study IDs have two character format", result,
                    false);
        }
        return result.containsValue(false);
    }

}