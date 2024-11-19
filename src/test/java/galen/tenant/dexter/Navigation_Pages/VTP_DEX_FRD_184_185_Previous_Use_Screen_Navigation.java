package galen.tenant.dexter.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.pages.sp.StudyAdminPageObj;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_184_185_Previous_Use_Screen_Navigation extends BaseTest {
    static String OBJECTIVE = "DEX_FRD_184: To verify on the Previous Product Use screen, the application shall prompt " +
            "the user to indicate if they have used the product before.\n" +
            "DEX_FRD_185: To verify on the Previous Product Use screen, if a guest user selects “Yes” or “No”" +
            " and selects “next”, the application shall record their answer, then navigate to the confirm " +
            "customer screen and present a modal explaining more info links if the token provided by the user" +
            " is active.";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "-\tPrevious Use Screen has options for stating whether the user has previously used Zena before or" +
            " not\n" +
            "-\tWhen the user submits Yes, the app records their answer and navigates to the Confirm Customer " +
            "Screen with More Info tooltip\n" +
            "o\tMetrics records YES for Prior Use\n" +
            "-\tWhen the user submits No, the app records their answer and navigates to the Confirm Customer " +
            "Screen with More Info tooltip\n" +
            "o\tMetrics records NO for Prior Use\n" +
            "Unable to test the negative scenario due to the user being blocked on the Welcome Screen when " +
            "using an inactive token.";
    static String REQUIREMENTS = "DEX_FRD_184, FRD_DEX_185";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_184_185_Previous_Use_Screen_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_184_185_Previous_Use_Screen_Navigation()  {
        VERSIONHISTORY.add("1.0;20JUN2024;Initial Test Script;Tester");
    }

    @Test
    public void VTP_DEX_FRD_184_185_Previous_Use_Screen_Navigation_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_184_185 – Previous Use Screen Navigation";

        DexterUser user = new DexterUser();
        DexterPageObj pageObj = new DexterPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);
        BasicHelpers bh = new BasicHelpers(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.usedProduct, report);
        report.addScreenshotStep("Step3_Prior Use");

        common.clickYesNoNextToPage("Yes", pageObj.orderForSelf, report);
        report.addScreenshotStep("Step4_Confirm Customer");

        pageObj.orderForSelf.clickCloseToDismiss(null);
        common.verifyModalDismissed(null);

        common.load(UrlType.STUDY);
        StudyAdminPageObj sp = new StudyAdminPageObj(driver);
        bh.downloadCSVAndVerify("Step5_Yes", sp, report);
        String testOutputPath = "reports/"+reportName+"/Step5_Yes";
        File testOutput = new File(testOutputPath);
        bh.compareCSVValueByAssessmentID(testOutput, user.assessmentID, "PriorUse",
                "TRUE", report);

        common.load(UrlType.DEXTER);
        new DexterHFWrappers(driver).runDexterHFNonsmokingwBP(user, pageObj.usedProduct, report);
        common.clickYesNoNextToPage("No", pageObj.orderForSelf, report);
        report.addScreenshotStep("Step9_Confirm Customer");

        common.load(UrlType.STUDY);
        bh.downloadCSVAndVerify("Step10_No",sp, report);
        testOutputPath = "reports/"+reportName+"/Step10_No";
        testOutput = new File(testOutputPath);
        bh.compareCSVValueByAssessmentID(testOutput, user.assessmentID, "PriorUse",
                "FALSE", report);

    }
}
