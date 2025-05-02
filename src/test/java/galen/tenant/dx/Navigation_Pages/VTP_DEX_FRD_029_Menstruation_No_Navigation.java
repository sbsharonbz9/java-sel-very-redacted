package galen.tenant.dx.Navigation_Pages;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_029_Menstruation_No_Navigation extends BaseTest {
    static String OBJECTIVE = "On the Menstrual Periods screen if a user answers no, indicating they do not have " +
            "menstrual periods, the application shall navigate to the Hormonal Birth Control B screen.";
    static String NOTES = "None";
    static String REQUIREMENTS = "DEX_FRD_029";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_029_Menstruation_No_Navigation";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_029_Menstruation_No_Navigation()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_029_Menstruation_No_Navigation_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_029 – Menstruation No Navigates Hormonal BC screen";

        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.menstrual, report);
        new CommonPageFeatures(driver).clickYesNoNextToPage("No", pageObj.birthControlB, report);
        report.addScreenshotStep("Step2_Hormonal BC(B)");
    }
}
