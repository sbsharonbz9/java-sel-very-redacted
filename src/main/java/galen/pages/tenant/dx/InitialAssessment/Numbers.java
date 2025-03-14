package galen.pages.tenant.dx.InitialAssessment;

import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Numbers extends BasePage {

    public By title = By.className("KnowNumbers");
    public By content = By.className("assessment-base__content");
    public String content_text="Before you can purchase Zena, you need to know\n" +
            "Your height\n" +
            "Your weight\n" +
            "Your actual blood pressure numbers taken within the last 3 months\n" +
            "If you don't have your details now you can continue to complete the questions but will need to enter the information later.\n" +
            "If you do not have your blood pressure numbers now you can go away and come back to the health survey later.\n" +
            "When you have your blood pressure numbers please restart the survey and enter the information to see if Zena is right for you.\n" +
            "How to check your blood pressure";

    public Numbers(WebDriver driver) {
        super(driver);
        titleText="Before you can purchase Zena, you need to know";
        reportText="Numbers Page";
        headingTitle=title;
    }

}
