package galen.tenant.petros;

import com.itextpdf.io.IOException;
import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosNavigations;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.tenant.petros.InitialAssessment.PetrosPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_FRD_087_088_089_Heart_Not_Safe_For_Sex_Screen extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    PetrosUser user;
    String reportName = "VTP_FRD_087_088_089_Heart_Not_Safe_For_Sex_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_FRD_087_088_089_Heart_Not_Safe_For_Sex_Screen()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_FRD_087_088_089_Heart_Not_Safe_For_Sex_Screen_Test() throws IOException, InterruptedException, java.io.IOException {
        
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_087_088_089_Heart_Not_Safe_For_Sex_Screen";
        PetrosPageObj pageObj = new PetrosPageObj(driver);
        user = new PetrosUser();

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.PETROS);
        PetrosNavigations navs = new PetrosNavigations(driver);

        navs.petrosHappyFlowTo(user, pageObj.heartNotSafeForSex,report);
        pageObj.heartNotSafeForSex.verifyYesNoPresent(report);
        report.addScreenshotStep("Step2_Heart Not Safe for Sex screen", driver);

        pageObj.heartNotSafeForSex.clickYesNoNextToPage("No", pageObj.cardiacConditions1, report);
        report.addScreenshotStep("Step3_Cardiac Conditions 1", driver);

        user.heartNotSafeForSex="Yes";
        navs.petrosHappyFlowTo(user, pageObj.cardiacConditions1,report);
        report.addScreenshotStep("Step5_Cardiac Conditions 1", driver);

        navs.petrosHappyFlowTo(user, pageObj.review, report);
        pageObj.review.addressConfirmationsAndProgress(pageObj.kickoutPage, report);
        report.addScreenshotStep("Step7_DNU Page", driver);

        user.heartNotSafeForSex="No";
        navs.petrosHappyFlowTo(user, pageObj.review, report);
        pageObj.review.clickNSFSEdit(report);
        pageObj.heartNotSafeForSex.verifyAtPage(report);

        pageObj.heartNotSafeForSex.clickYesNoNextToPage("Yes", pageObj.review,report);
        report.addScreenshotStep("Step10_Review", driver);

        pageObj.review.addressConfirmationsAndProgress(pageObj.kickoutPage, report);
        report.addScreenshotStep("Step12_DNU Page", driver);
    }
}
