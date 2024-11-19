package galen.tenant.petros;

import galen.base.BaseTest;
import galen.enums.common.Gender;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosHFWrappers;
import galen.helpers.tenant.petros.PetrosNavigations;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.tenant.petros.InitialAssessment.PetrosPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_FRD_025_026_027_028_DOB_Sex_at_Birth_Screen_Guest extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    PetrosPageObj pageObj;
    PetrosUser user;
    String reportName = "Report Name";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;

    VTP_FRD_025_026_027_028_DOB_Sex_at_Birth_Screen_Guest()  {
        this.user = new PetrosUser();
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_FRD_025_026_027_028_DOB_Sex_at_Birth_Screen_Guest_Test() throws IOException, InterruptedException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "Report Title";
        bh = new BasicHelpers(driver);
        pageObj = new PetrosPageObj(driver);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.PETROS);
        PetrosHFWrappers hf = new PetrosHFWrappers(driver);

        hf.executeHappyFlow(user, pageObj.sexAndBirthYear, REFERENCES, report);
        user.gender = Gender.Female;
        pageObj.sexAndBirthYear.fillOutForm(user, report);
        report.addScreenshotStep("OAuth Screen Step 2", driver);

        hf.executeHappyFlow(user, pageObj.review, REFERENCES, report);
        pageObj.review.addressConfirmationsAndProgress(pageObj.kickoutPage, report);
        report.addScreenshotStep("Kickout Page Step 4", driver);

        user.gender = Gender.Male;
        hf.executeHappyFlow(user, pageObj.sexAndBirthYear, REFERENCES, report);
        LocalDate dobFor17YearOld = LocalDate.now().minusYears(17).withMonth(1).withDayOfMonth(1);
        user.dobYear = String.valueOf(dobFor17YearOld.getYear());
        pageObj.sexAndBirthYear.fillOutForm(user, report);
        pageObj.oAuth.verifyAtPage(report);
        report.addScreenshotStep("Step 6 oath is seen ");

        hf.executeHappyFlow(user, pageObj.review, REFERENCES, report);
        pageObj.review.addressConfirmationsAndProgress(pageObj.kickoutPage, report);
        report.addScreenshotStep("Kickout Page Step 8", driver);

        user.gender = Gender.Male;
        hf.executeHappyFlow(user, pageObj.sexAndBirthYear, REFERENCES, report);
        LocalDate dobFor18YearOld = LocalDate.now().minusYears(18).withMonth(1).withDayOfMonth(1);
        user.dobYear = String.valueOf(dobFor18YearOld.getYear());
        pageObj.sexAndBirthYear.fillOutForm(user, report);
        pageObj.oAuth.verifyAtPage(report);
        report.addScreenshotStep("Step 10 oath is seen ");

        user.gender = Gender.Male;
        hf.executeHappyFlow(user, pageObj.sexAndBirthYear, REFERENCES, report);
        LocalDate dobFor76YearOld = LocalDate.now().minusYears(76).withMonth(1).withDayOfMonth(1);
        user.dobYear = String.valueOf(dobFor76YearOld.getYear());
        pageObj.sexAndBirthYear.fillOutForm(user, report);
        pageObj.oAuth.verifyAtPage(report);
        report.addScreenshotStep("Step 12 oath is seen ");

        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.review, report);
        pageObj.review.addressConfirmationsAndProgress(pageObj.adbu, report);
        pageObj.adbu.verifyAtPage(report);
        pageObj.adbu.verifyAgeCondition(report);
        report.addScreenshotStep("Step 15 oath ADBU 75");

        hf.executeHappyFlow(user, pageObj.review, REFERENCES, report);
        pageObj.review.clickDateOfBirthEditButton(report);
        LocalDate dobFor76YearOldLast = LocalDate.now().minusYears(76).withMonth(1).withDayOfMonth(1);
        user.dobYear = String.valueOf(dobFor76YearOldLast.getYear());
        pageObj.sexAndBirthYear.fillOutForm(user, report);
        new CommonPageFeatures(driver).clickNextToPage(pageObj.sexAndBirthYear, report);
        pageObj.review.addressConfirmationsAndProgress(pageObj.adbu, report);
        report.addScreenshotStep("Step 22 oath ADBU 75 Last Step");
    }
}