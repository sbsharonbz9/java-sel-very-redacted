package galen.pages.tenant.petros.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;

public class NitrateUse extends BasePage {

    public By nitrateHeader = By.className("NitrateQuestionScreen");
    public By moreInfoP1 = By.xpath("//div[@class='assessment-base__more-info-content']/p[0]");
    public By moreInfoP2 = By.xpath("//div[@class='assessment-base__more-info-content']/p[1]");
    public By moreInfoP3 = By.xpath("//div[@class='assessment-base__more-info-content']/p[2]");


    public String moreInfoP1Text="Nitrates may be tablets, sprays, creams, patches, suppositories, or injectable products. " +
            "Below are some examples of nitrates.";
    public String moreInfoP2Text="Never use Avanafi OTC with a nitrate medicine. Using nitrates with this product could " +
            "cause a life-threatening drop in blood pressure and could lead to a heart attack or stroke.";
    public String moreInfoP3Text="This is not a complete list. If you are unsure whether you have been prescribed or " +
            "taken these or any other nitrate, consult a doctor or pharmacist before taking Avanafil OTC.";




    public NitrateUse(WebDriver driver) {
        super(driver);
        titleText="NITRATE MEDICINE";
    }

    public WebElement getHeader() {
        return basicHelpers.getWebElement(nitrateHeader);
    }

    public boolean verifyAtPage(@Nullable GalenReport report) {
        return basicHelpers.verifyAtPage(getHeader()!=null, "Nitrate Use", report);
    }


}
