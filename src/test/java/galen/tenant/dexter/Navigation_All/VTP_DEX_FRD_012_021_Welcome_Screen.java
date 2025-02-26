package galen.tenant.dexter.Navigation_All;

import galen.base.BaseTest;
import galen.helpers.common.GalenReport;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.WelcomePage;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_012_021_Welcome_Screen extends BaseTest {
    static String OBJECTIVE = "DEX_FRD_012: Verify the application shall display a Zena Welcome Screen upon entry of the" +
            " Dexter App. DEX_FRD_021: Verify on the Welcome screen, the application shall present a link to start the health survey.";
    static String NOTES = "None";
    static String REQUIREMENTS = "FRD_012, FRD_021";
    static String REFERENCES = "N/A";
    String reportName = "VTP_DEX_FRD_012_021_Welcome_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_012_021_Welcome_Screen()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_012_021_Welcome_Screen_Test() throws IOException, InterruptedException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_012_021 – Welcome Screen";

        new PritUnlPage(driver).authenticateUserIfRequired();
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.verifyAtPage(report);
        welcomePage.verifyBeginButtonPresent(report);
        report.addScreenshotStep("Step1_Welcome Page");
    }

}
