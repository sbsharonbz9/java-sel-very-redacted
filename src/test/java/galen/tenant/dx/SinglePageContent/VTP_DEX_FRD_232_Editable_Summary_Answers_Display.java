package galen.tenant.dx.SinglePageContent;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dx.AntifungalMedsType;
import galen.enums.tenant.dx.BloodPressureType;
import galen.enums.tenant.dx.ReviewAnswersLinks;
import galen.enums.tenant.dx.SmokeType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class VTP_DEX_FRD_232_Editable_Summary_Answers_Display extends BaseTest {

    static String OBJECTIVE = "To verify on the Summary screen, the application shall display user responses to the answered assessment questions.";
    static String NOTES = "This protocol contains the following scenario(s):\n" +
            "-\tEditable Summary displays all questions answered during the health survey\n" +
            "-\tEditable Summary displays updated questions answered during the health survey\n";

    static String REQUIREMENTS = "DEX_FRD_232";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_ADBU_wBP.docx;";
    String reportName = "VTP_DEX_FRD_232_Editable_Summary_Answers_Display";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_232_Editable_Summary_Answers_Display() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_FRD_232_Editable_Summary_Answers_Display_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_232 – Editable Summary Answers Display";

        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_wBP();
        DxPageObj pageObj = new DxPageObj(driver);

        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DX);
        new DxHFWrappers(driver).runDxHFADBUwBP(user, pageObj.review, report);
        pageObj.review.verifyAllAnswersCorrect(user, report);

        report.addScreenshotStep("Step_32_Review_Screen");

        pageObj.review.clickEditToPage(ReviewAnswersLinks.SMOKING, pageObj.smoking, report);
        user.smokingType = SmokeType.SMOKE_REGULARLY;
        pageObj.smoking.selectRadioResponseAndProgress(user.smokingType.label, pageObj.birthYear, report);
        pageObj.birthYear.fillOutBirthday(user, report);
        pageObj.birthYear.clickNextToPage(pageObj.smokingDisclaimer, report);
        pageObj.smokingDisclaimer.clickNextToPage(pageObj.review, report);

        pageObj.review.clickEditToPage(ReviewAnswersLinks.MENSTRUAL, pageObj.menstrual, report);
        pageObj.menstrual.verifyAtPage(report);
        user.menstrualPeriod = "No";
        pageObj.menstrual.clickYesNoNextToPage(user.menstrualPeriod, pageObj.birthControlB, report);

        user.birthControl = "Yes";
        pageObj.birthControlB.addressConfirmationsAndProgress(pageObj.review, report);
        pageObj.review.clickEditToPage(ReviewAnswersLinks.EVER_HAD_CANCER, pageObj.everHadCancer, report);
        user.everHadCancer = "Yes";
        pageObj.everHadCancer.clickYesNoNextToPage(user.everHadCancer, pageObj.cancerList, report);
        pageObj.cancerList.selectCheckboxesAndProgress(user.cancerList, pageObj.review, report);

        pageObj.review.clickEditToPage(ReviewAnswersLinks.DDI, pageObj.ddiCondition, report);
        user.conditionType=user.defaultNone;
        pageObj.ddiCondition.selectCheckboxesAndProgress(user.conditionType, pageObj.review, report);

        pageObj.review.clickEditToPage(ReviewAnswersLinks.ANITFUNGAL, pageObj.antifungal, report);
        user.isAntifungal = "Yes";
        pageObj.antifungal.clickYesNoNextToPage(user.isAntifungal, pageObj.antifungalMeds, report);
        user.antiFungalMeds= pageObj.antifungalMeds.getCondition(AntifungalMedsType.FLUCONAZOLE.label);
        pageObj.antifungalMeds.selectCheckboxesAndProgress(user.antiFungalMeds, pageObj.review, report);

        pageObj.review.clickEditToPage(ReviewAnswersLinks.DEPRESSION, pageObj.depression, report);
        user.depression = "Yes";
        pageObj.depression.clickYesNoNextToPage(user.depression, pageObj.diagnosedDepression, report);

        user.diagnosedDepression = "Yes";
        pageObj.diagnosedDepression.clickYesAndAddressModalToPage(pageObj.review, report);
        pageObj.review.verifyAtPage(report);

        pageObj.review.clickEditToPage(ReviewAnswersLinks.KNOW_BP, pageObj.knowBPNumber, report);
        user.knowBPType= BloodPressureType.No_DO_NOT_Know_BP;
        pageObj.knowBPNumber.selectRadioResponseAndProgress(user.knowBPType.label, pageObj.review, report);

        pageObj.review.verifyAllAnswersCorrect(user, report);
        report.addScreenshotStep("Step_54_Review_Screen");
    }
}