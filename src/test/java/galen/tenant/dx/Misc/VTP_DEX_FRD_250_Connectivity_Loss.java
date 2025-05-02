package galen.tenant.dx.Misc;

import galen.base.BaseTest;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxNavigations;
import galen.helpers.tenant.dx.DxUser;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_250_Connectivity_Loss extends BaseTest {
    static String OBJECTIVE = "To verify If network connectivity is lost, the application shall present a pop up " +
            "notifying the user and preventing further progress until connectivity is restored. Once connectivity is " +
            "restored, the pop up shall close and the user may resume progress.";
    static String NOTES = "This protocol contains the following scenario(s):\n" +
            "•\tConnectivity Loss Pop-Up is displayed when the internet connection is lost\n" +
            "•\tConnectivity Loss Pop-Up is no longer displayed once internet connection is restored\n" +
            "•\tUser is able to proceed with assessment once connection has been restored";
    static String REQUIREMENTS = "DEX_FRD_250";
    static String REFERENCES = "N/A";
    String reportName = "VTP_DEX_FRD_250_Connectivity_Loss";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_250_Connectivity_Loss() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_250_Connectivity_Loss_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_250 – Connectivity Loss";

        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired();
        new DxNavigations(driver).partialNavigationIA(user, pageObj.privacyPage, report);
        new BasicHelpers(driver).setOfflineMode(true, report);
        new CommonPageFeatures(driver).verifyModalDisplayed(report);
        report.addScreenshotStep("Step4_Offline");
        new BasicHelpers(driver).setOfflineMode(false, report);
        new CommonPageFeatures(driver).verifyModalDismissed(report);
        report.addScreenshotStep("Step5_Not Offline");
        pageObj.privacyPage.clickIAcceptBtnToPage(pageObj.numbers, report);
        report.addScreenshotStep("Step6_Numbers Page");
    }
}