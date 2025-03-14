package galen.pages.tenant.dx.InitialAssessment;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;

public class BirthControl extends BasePage {

    public By title = By.xpath("//form[contains(@class,'BirthControl')]");
    public String questionText = "Are you currently using any hormonal birth control (such as birth control pill, IUD, " +
            "injection, implant, patch, or vaginal ring)?";
    public String moreInfoText = "You must stop use of your current form of hormonal birth control when you start to use Zena.\n" +
            "Using both together can cause serious health issues such as heart attack, stroke and even death.\n" +
            "Other forms of hormonal birth control include pills, vaginal ring, patch, injection or an inter-uterine device (IUD).\n" +
            "If you are unsure you must talk to a health care professional before using Zena.";
    public static By modalPopup = By.className("bc-confirm-modal");
    public static By acknowledgedCheckBox = By.xpath("//input[@id='confirm']/following-sibling::span[1]");
    public static String modalText="You must stop using your current birth control product when you start using Zena." +
            " Two forms of birth control should not be used together as this can increase the chance of serious health" +
            " issues such as heart attack, stroke and even death. (Condoms are ok to continue)";

    public BirthControl(WebDriver driver) {
        super(driver);
        headingTitle=title;
        titleText="Are you using a hormonal injection, implant or IUD for birth control?";
        reportText="Hormonal BC (A) Screen";
        modal=modalPopup;
    }
    public Boolean verifyConfirmModalOpen(@Nullable GalenReport report) {
        return verifyModalDisplayed(report);
    }
}
