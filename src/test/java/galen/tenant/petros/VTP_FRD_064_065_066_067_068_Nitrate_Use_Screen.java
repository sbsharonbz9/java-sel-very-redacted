package galen.tenant.petros;

import com.itextpdf.io.IOException;
import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.petros.PetrosNavigations;
import galen.helpers.tenant.petros.PetrosUser;
import galen.pages.tenant.petros.InitialAssessment.PetrosPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_FRD_064_065_066_067_068_Nitrate_Use_Screen extends BaseTest {
    static String OBJECTIVE = "Objective";
    static String REQUIREMENTS = "Req";
    static String REFERENCES = "Ref";
    static String NOTES = "Notes";
    PetrosUser user;
    String reportName = "VTP_FRD_064_065_066_067_068_Nitrate_Use_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_FRD_064_065_066_067_068_Nitrate_Use_Screen() throws IOException {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }


    @Test
    public void VTP_FRD_064_065_066_067_068_Nitrate_Use_Screen_Test() throws IOException, InterruptedException, java.io.IOException {
        
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_FRD_064_065_066_067_068_Nitrate_Use_Screen";
        PetrosPageObj pageObj = new PetrosPageObj(driver);
        user = new PetrosUser();
        CommonPageFeatures common = new CommonPageFeatures(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.PETROS);
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.nitrateUse, report);
        common.verifyYesNoUnsurePresent(report);
        report.addScreenshotStep("Nitrate USe Screen Options", driver);
        
        common.clickYesNoNextToPage("Yes", pageObj.clarification,report);
        report.addScreenshotStep("Clarification on Nitrate Use Screen ", driver);
        
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.nitrateUse, report);
        common.clickYesNoUnsure("Unsure", report);
        common.clickNextToPage(pageObj.clarification, report);
        report.addScreenshotStep("Not Sure Clarification", driver);
        report.addScreenshotStep("Clarification Is seen Screen Options", driver);
        
        new PetrosNavigations(driver).partialNavigationIA(user, pageObj.nitrateUse, report);
        common.clickYesNoNextToPage("No", pageObj.clarification, report);
        report.addScreenshotStep("Clarification Is seen Screen Options 3", driver);
    }
}
