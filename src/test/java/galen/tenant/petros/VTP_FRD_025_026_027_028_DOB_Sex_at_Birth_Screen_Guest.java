package galen.tenant.petros;

import galen.base.BaseTest;
import galen.driver.DriverManager;
import galen.enums.common.Gender;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosNavigations;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.tenant.petros.InitialAssessment.PetrosPageObj;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_FRD_025_026_027_028_DOB_Sex_at_Birth_Screen_Guest extends BaseTest {
    static String OBJECTIVE = "FRD_025: To verify the Date of Birth / Sex at Birth Screen shall tag the user as DNU and navigate an unauthenticated user to the OAuth Screen when the user inputs and submits a female response.\n" +
            "FRD_026: To verify the Date of Birth / Sex at Birth Screen shall tag the user as DNU and navigate an unauthenticated user to the OAuth Screen when the user inputs and submits a male response and an age input <18.\n" +
            "FRD_027: To verify the Date of Birth / Sex at Birth Screen shall navigate an unauthenticated user to the OAuth Screen when the user inputs and submits a male response and an age input 18-74.\n" +
            "FRD_028: To verify the Date of Birth / Sex at Birth Screen shall tag this account as ADBU and navigate an unauthenticated user to the OAuth Screen when the user inputs and submits a male response and an age input greater than 75.\n";
    static String NOTES = "This protocol contains the following scenario(s):\n" +
            "●\tUser selects and submits the Female radio button, they are navigated to the OAuth Screen\n" +
            "○\tNavigates to the DNU Screen after Review Answers Attestation Modal\n" +
            "●\tMale user 17 years old is navigated to the OAuth Screen\n" +
            "○\tNavigates to the DNU Screen after Review Answers Attestation Modal\n" +
            "●\tMale user 18 years old is navigated to the OAuth Screen\n" +
            "●\tMale user greater than 75 years of age triggers ADBU and is navigated to the OAuth Screen\n" +
            "●\tMale user equal to 75 years of age by navigation from the Review Answers Screen triggers ADBU and navigates to ADBU Screen\n";
    static String REQUIREMENTS = "FRD_025, FRD_026, FRD_027, FRD_028";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_w_Guest_User.docx";
    PetrosPageObj pageObj;
    PetrosUser user;
    String reportName = "VTP_FRD_025_026_027_028_DOB_Sex_at_Birth_Screen_Guest";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;

    VTP_FRD_025_026_027_028_DOB_Sex_at_Birth_Screen_Guest() throws IOException {
        this.user = new PetrosUser();
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_FRD_025_026_027_028_DOB_Sex_at_Birth_Screen_Guest() throws IOException, InterruptedException {
        WebDriver driver = DriverManager.getDriver();
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_025_026_027_028_DOB_Sex_at_Birth_Screen_Guest";
        bh = new BasicHelpers(driver);
        pageObj = new PetrosPageObj(driver);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.PETROS);;
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.sexAndBirthYear, report);
        user.gender = Gender.Female;
        pageObj.sexAndBirthYear.fillOutForm(user, report);
        report.addScreenshotStep("OAuth Screen Step 2", driver);

        pageObj.pritUnl.getWelcomePage(report);
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.kickout.verifyAtPage(report);
        report.addScreenshotStep("Kickout Page Step 4", driver);

        pageObj.pritUnl.getWelcomePage(report);
        user.gender = Gender.Male;
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.sexAndBirthYear, report);
        LocalDate dobFor17YearOld = LocalDate.now().minusYears(17).withMonth(1).withDayOfMonth(1);
        user.dobYear = String.valueOf(dobFor17YearOld.getYear());
        pageObj.sexAndBirthYear.fillOutForm(user, report);
        pageObj.oAuth.verifyAtPage(report);
        report.addScreenshotStep("Step 6 oath is seen ");

        pageObj.pritUnl.getWelcomePage(report);
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.kickout.verifyAtPage(report);
        report.addScreenshotStep("Kickout Page Step 8", driver);

        pageObj.pritUnl.getWelcomePage(report);
        user.gender = Gender.Male;
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.sexAndBirthYear, report);
        LocalDate dobFor18YearOld = LocalDate.now().minusYears(18).withMonth(1).withDayOfMonth(1);
        user.dobYear = String.valueOf(dobFor18YearOld.getYear());
        pageObj.sexAndBirthYear.fillOutForm(user, report);
        pageObj.oAuth.verifyAtPage(report);
        report.addScreenshotStep("Step 10 oath is seen ");

        pageObj.pritUnl.getWelcomePage(report);
        user.gender = Gender.Male;
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.sexAndBirthYear, report);
        LocalDate dobFor76YearOld = LocalDate.now().minusYears(76).withMonth(1).withDayOfMonth(1);
        user.dobYear = String.valueOf(dobFor76YearOld.getYear());
        pageObj.sexAndBirthYear.fillOutForm(user, report);
        pageObj.oAuth.verifyAtPage(report);
        report.addScreenshotStep("Step 12 oath is seen ");

        pageObj.pritUnl.getWelcomePage(report);
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.review, report);
        pageObj.review.addressConfirmations(report);
        pageObj.adbu.verifyAtPage(report);
        pageObj.adbu.verifyAgeCondition(report);
        report.addScreenshotStep("Step 15 oath ADBU 75");

        pageObj.pritUnl.getWelcomePage(report);
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.review, report);
        pageObj.review.clickDateOfBirthEditButton(report);
        LocalDate dobFor76YearOldLast = LocalDate.now().minusYears(76).withMonth(1).withDayOfMonth(1);
        user.dobYear = String.valueOf(dobFor76YearOldLast.getYear());
        pageObj.sexAndBirthYear.fillOutForm(user, report);
        pageObj.sexAndBirthYear.clickNextToPage(pageObj.sexAndBirthYear, report);
        pageObj.review.verifyAtPage(report);
        pageObj.review.addressConfirmations(report);
        pageObj.adbu.verifyAtPage(report);
        report.addScreenshotStep("Step 22 oath ADBU 75 Last Step");
    }
}