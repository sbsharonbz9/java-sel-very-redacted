package galen.tenant.dx.SinglePageContent;

import galen.base.BaseTest;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class VTP_DEX_FRD_260_Liver_Disease_Liver_Cancer_Screen extends BaseTest {
    static String OBJECTIVE = "To verify the Liver Disease including Liver Cancer Screen shall provide controls "+
            "that require the user to select all conditions that they currently have or have had from the following list, or select \"None of these\":\n" +
            "•\tLiver Disease\n" +
            "•\tLiver Cancer\n" +
            "•\tHepatitis C\n";
    static String NOTES = "The following scenario(s) are verified in this protocol:\n" +
            "•\tAll expected fields are displayed\n" +
            "•\tNext button is disabled by default\n" +
            "•\tAfter selection of a checkbox, the Next button is enabled\n" +
            "•\tUser is able to make multiple selections\n" +
            "•\tSelection of “None of these” checkbox removes selection of all other checkboxes\n";
    static String REQUIREMENTS = "DEX_FRD_259";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_260_Liver_Disease_Liver_Cancer_Screen";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_260_Liver_Disease_Liver_Cancer_Screen()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_260_Liver_Disease_Liver_Cancer_Screen_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_260 – Liver Disease including Liver Cancer Screen";

        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);
        CommonPageFeatures common = new CommonPageFeatures(driver);

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.liverCancer, report);
        pageObj.liverCancer.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step2_Liver_Cancer_Screen");

        user.cancerList=new ArrayList<>(Arrays.asList("Liver disease", "Liver cancer", "Hepatitis C"));
        pageObj.liverCancer.selectCheckboxReponses(user.cancerList, report);
        sleep(1000);
        pageObj.liverCancer.verifySelectedCheckboxes(user.cancerList, report);
        pageObj.liverCancer.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step3_Next_Button_Enabled");

        user.cancerList=new ArrayList<>(Arrays.asList("None of these"));
        pageObj.liverCancer.selectCheckboxReponses(user.cancerList, report);
        sleep(1000);
        pageObj.liverCancer.verifySelectedCheckboxes(user.cancerList, report);
        pageObj.liverCancer.verifyNextButtonEnabled(report);
        report.addScreenshotStep("Step4_Next_Button_Enabled");

        user.cancerList=new ArrayList<>(Arrays.asList("None of these"));
        pageObj.liverCancer.selectCheckboxReponses(user.cancerList, report);
        sleep(1000);
        pageObj.liverCancer.verifyNextButtonDisabled(report);
        report.addScreenshotStep("Step5_Next_Button_Disabled");
    }
}
