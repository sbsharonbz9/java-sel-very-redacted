package galen.tenant.dx.DNU;

import galen.base.BaseTest;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_FRD_027_Confirm_Custome_No_DNU extends BaseTest {
    static String OBJECTIVE = "On the Confirm Customer screen, if a user indicates they are not ordering for " +
            "themselves, the application shall end the health survey and navigate to the DNU screen with messaging" +
            " that it is for intended user only";
    static String NOTES = "None";
    static String REQUIREMENTS = "DEX_FRD_027";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_027_Confirm_Custome_No_DNU";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_027_Confirm_Custome_No_DNU()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_027_Confirm_Custome_No_DNU_Test()  {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_027 – Confirm Customer No Navigates to DNU screen";

        DxUser user = new DxUser();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired();
        new DxHFWrappers(driver).runDxHFNonsmokingwBP(user, pageObj.orderForSelf, report);
        pageObj.orderForSelf.clickCloseToDismiss(report);
        pageObj.orderForSelf.clickYesNoNextToPage("No", pageObj.kickoutPage, report);
        report.addScreenshotStep("Step4_Confirm Customer DNU");
    }
}
