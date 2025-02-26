package galen.tenant.dexter.SinglePageContent;

import galen.base.BaseTest;
import galen.enums.tenant.dexter.BloodPressureType;
import galen.enums.tenant.dexter.CancerType;
import galen.enums.tenant.dexter.ReviewAnswersLinks;
import galen.enums.tenant.dexter.SmokeType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterHFWrappers;
import galen.helpers.tenant.dexter.DexterUser;
import galen.helpers.tenant.dexter.DexterUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.tenant.dexter.InitialAssessment.BirthYear;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import galen.pages.tenant.dexter.InitialAssessment.SmokingDisclaimer;
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
        String none = "None of these";
        DexterUser user = new DexterUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_wBP();
        DexterPageObj pageObj = new DexterPageObj(driver);

        new PritUnlPage(driver).authenticateUserIfRequired();
        new DexterHFWrappers(driver).runDexterHFADBUwBP(user, pageObj.review, report);
        pageObj.review.verifyAllAnswersCorrect(user, report);

        report.addScreenshotStep("Step_32_Review_Screen");

        pageObj.review.clickEditSmokingOrVapeLink(report);
        user.smokingType = SmokeType.SMOKE_REGULARLY;
        pageObj.smoking.verifyAtPage(report);
        pageObj.smoking.selectRadioResponseAndProgress(user.smokingType.label, pageObj.birthYear, report);
        pageObj.birthYear.fillOutBirthday(user, report);
        pageObj.birthYear.clickNextToPage(pageObj.smokingDisclaimer, report);
        pageObj.smokingDisclaimer.clickNextToPage(pageObj.review, report);

        pageObj.review.clickMenstrualEdit(report);
        pageObj.menstrual.verifyAtPage(report);
        user.menstrualPeriod = "No";
        pageObj.menstrual.clickYesNoNextToPage(user.menstrualPeriod, pageObj.birthControlB, report);

        user.birthControl = "Yes";
        pageObj.birthControlB.addressConfirmationsAndProgress(pageObj.review, report);
        pageObj.review.clickEditEverHadCancerLink(report);
        pageObj.everHadCancer.verifyAtPage(report);
        user.everHadCancer = "Yes";
        pageObj.everHadCancer.clickYesNoNextToPage(user.everHadCancer, pageObj.cancerList, report);
        pageObj.cancerList.selectCheckboxesAndProgress(user.cancerList, pageObj.review, report);

        pageObj.review.clickEditMedicalConditionsLink(report);
        pageObj.ddiCondition.verifyAtPage(report);
        user.conditionType=new ArrayList<>(Arrays.asList("None of these"));
        pageObj.ddiCondition.selectCheckboxesAndProgress(user.conditionType, pageObj.review, report);

        pageObj.review.clickEditAntifungalProductsLink(report);
        pageObj.antifungal.verifyAtPage(report);
        user.isAntifungal = "Yes";
        pageObj.antifungal.clickYesNoNextToPage(user.isAntifungal, pageObj.antifungalMeds, report);
        user.antiFungalMeds=new ArrayList<>(Arrays.asList("Fluconazole"));
        pageObj.antifungalMeds.selectCheckboxesAndProgress(user.antiFungalMeds, pageObj.review, report);

        pageObj.review.clickDepressionEdit(report);
        pageObj.depression.verifyAtPage(report);
        user.depression = "Yes";
        pageObj.depression.clickYesNoNextToPage(user.depression, pageObj.diagnosedDepression, report);

        user.diagnosedDepression = "Yes";
        pageObj.diagnosedDepression.clickYesOrNo(user.diagnosedDepression, report);
        pageObj.diagnosedDepression.clickNext(report);

        pageObj.diagnosedDepression.verifyConfirmModalOpen(report);
        pageObj.diagnosedDepression.clickConfirm(report);
        pageObj.review.verifyAtPage(report);

        pageObj.review.clickEditKnowsBloodPressureNumbersLink(report);
        pageObj.knowBPNumber.verifyAtPage(report);
        user.knowBPType= BloodPressureType.No_DO_NOT_Know_BP;
        pageObj.knowBPNumber.selectRadioResponseAndProgress(user.knowBPType.label, pageObj.review, report);

        pageObj.review.verifyAllAnswersCorrect(user, report);
        report.addScreenshotStep("Step_54_Review_Screen");
    }
}