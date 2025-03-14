package galen.tenant.dx.Misc;

import galen.base.BaseTest;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.enums.tenant.dx.CancerType;
import galen.enums.tenant.dx.HeartConditionType;
import galen.enums.tenant.dx.LiverCancerType;
import galen.enums.tenant.dx.SmokeType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxHFWrappers;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.common.PritUnlPage;
import galen.pages.sp.StudyAdminPageObj;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.testng.annotations.Test;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class VTP_DEX_FRD_236_Clinicians_Return_To_Portal extends BaseTest {
    static String OBJECTIVE = "To verify upon receiving a result from an assessment performed by a clinician, the screen" +
            " navigated to shall provide a link that closes the assessment tab and instructs the portal tab to refresh";
    static String NOTES = "This protocol contains the following scenarios:\n" +
            "-\tReturn to Study Portal link is displayed and clicking link to return to Study Portal closes the current " +
            "tab and the new Clinician Record is displayed on the refreshed View Records Screen from the following final" +
            " screens:\n" +
            "o\tSafe To Use Screen\n" +
            "o\tADBU End Screen\n" +
            "o\tGet BP Measured Screen\n" +
            "o\tConfirm Customer DNU Screen\n" +
            "o\tPrevent Pregnancy DNU Screen\n" +
            "o\tMenstruation DNU Screen\n" +
            "o\tSmoker Over 35 DNU Screen\n" +
            "o\tCancers DNU Screen\n" +
            "o\tBP Meds DNU Screen\n" +
            "o\tChest Pain DNU Screen\n" +
            "o\tBlood Clots DNU Screen\n" +
            "o\tIrregular Heartbeat DNU Screen\n" +
            "o\tLiver Disease DNU Screen\n" +
            "o\tVaginal Bleeding DNU Screen\n" +
            "o\tDiabetes DNU Screen\n" +
            "o\tPregnant DNU Screen\n" +
            "o\tBreastfeeding DNU Screen\n" +
            "o\tPregnancy Loss DNU Screen\n" +
            "o\tMigraines with Aura DNU Screen\n" +
            "o\tBP DNU Screen\n" +
            "o\tBP Immediate DNU Screen";
    static String REQUIREMENTS = "DEX_FRD_236";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_ADBU_wBP.docx;  \n" +
            "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx;\n" +
            "HappyFlow_IA_Initial_Assessment_ADBU_noBP.docx";
    String reportName = "VTP_DEX_FRD_236_Clinicians_Return_To_Portal";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    StudyAdminPageObj sp;
    DxPageObj pageObj;
    String original;
    CommonPageFeatures common;
    BasicHelpers bh;

    VTP_DEX_FRD_236_Clinicians_Return_To_Portal() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    void startNewRecord() {
        sp.viewRecords.load(UrlType.STUDY);
        sp.login.logIn(RoleType.CLINICIAN.email, null);
        sp.participants.verifyAtPage();
        sp.participants.clickViewRecords(null);
        sp.viewRecords.createNewRecord(report);
    }

    void endNewRecord(int i) throws InterruptedException {
        sleep(2000);
        bh.verifyDisplayedFlex(common.sapLink, "Link back to SAP", report);
        report.addScreenshotStep("Step" + i + "_Link");
        bh.clickFlex(common.sapLink, "Link back to Study Admin Portal", report);
        driver.switchTo().window(original);
        sp.viewRecords.verifyAtPage(report);
        i++;
        report.addScreenshotStep("Step" + i + "_Welcome");
        sp.viewRecords.logout(null);
    }

    @Test
    public void VTP_DEX_FRD_236_Clinicians_Return_To_Portal_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_236 – Clinicians Return to Portal";
        DxUser user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        pageObj = new DxPageObj(driver);
        sp = new StudyAdminPageObj(driver);
        DxHFWrappers hf = new DxHFWrappers(driver);
        common = new CommonPageFeatures(driver);
        original = driver.getWindowHandle();
        bh = new BasicHelpers(driver);
        pageObj.pritUnlauthenticateUserIfRequired(UrlType.STUDY);

        sp.viewRecords.load(UrlType.STUDY);
        sp.login.logIn(RoleType.CLINICIAN.email, report);
        sp.participants.verifyAtPage(report);
        sp.participants.clickViewRecords(report);
        sp.viewRecords.createNewRecord(report);
        hf.runDxHFNonsmokingwBP(user,pageObj.purchaseOptions, report);
        endNewRecord(12);

        startNewRecord();
        user=new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_wBP();
        hf.runDxHFADBUwBP(user,pageObj.adbu, report);
        pageObj.adbu.addressConfirmations("No", report);
        pageObj.docAttestationNo.verifyAtPage(report);
        endNewRecord(24);

        startNewRecord();
        user=new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_ADBU_noBP();
        hf.runDxHFADBUNoBP(user, pageObj.adbubpScreen, report);
        pageObj.adbubpScreen.addressConfirmations("Yes", report);
        pageObj.adbubpNormal.verifyAtPage(report);
        endNewRecord(36);

        startNewRecord();
        user=new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        hf.runDxHFNonsmokingwBP(user, pageObj.orderForSelf, report);
        pageObj.orderForSelf.clickCloseToDismiss(report);
        common.clickYesNoNextToPage("No", pageObj.kickoutPage, report);
        endNewRecord(44);

        startNewRecord();
        hf.runDxHFNonsmokingwBP(user, pageObj.pregnancy, report);
        common.clickYesNoNextToPage("No", pageObj.kickoutPage, report);
        endNewRecord(53);

        startNewRecord();
        hf.runDxHFNonsmokingwBP(user, pageObj.menstrual, report);
        common.clickYesNoNextToPage("No", pageObj.birthControlB, report);
        common.clickYesNoNextToPage("No", pageObj.kickoutPage, report);
        endNewRecord(64);

        startNewRecord();
        hf.runDxHFNonsmokingwBP(user, pageObj.smoking, report);
        user.dobYear=String.valueOf((Year.now().getValue()-40));
        pageObj.smoking.selectRadioResponseAndProgress(SmokeType.SMOKE_REGULARLY.label, pageObj.birthYear, report);
        pageObj.birthYear.fillOutBirthdayAndProgress(user,pageObj.kickoutPage, report);
        endNewRecord(77);

        startNewRecord();
        user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker();
        hf.runDxHFNonsmokingwBP(user, pageObj.everHadCancer, report);
        common.clickYesNoNextToPage("Yes", pageObj.cancerList, report);
        pageObj.cancerList.selectCheckboxAndProgress(CancerType.Breast_Cancer.label, pageObj.kickoutPage, report);
        endNewRecord(88);

        startNewRecord();
        hf.runDxHFNonsmokingwBP(user, pageObj.bloodPressureMeds, report);
        common.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        endNewRecord(96);

        startNewRecord();
        hf.runDxHFNonsmokingwBP(user, pageObj.cardiacRisk, report);
        pageObj.cancerList.selectCheckboxAndProgress(HeartConditionType.HEART_ATTACK.label, pageObj.kickoutPage, report);
        endNewRecord(105);

        startNewRecord();
        hf.runDxHFNonsmokingwBP(user, pageObj.bloodClot, report);
        pageObj.bloodClot.selectCheckboxAndProgress("Blood clots", pageObj.kickoutPage, report);
        endNewRecord(114);

        startNewRecord();
        hf.runDxHFNonsmokingwBP(user, pageObj.irregularHeartBeat, report);
        common.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        endNewRecord(123);

        startNewRecord();
        hf.runDxHFNonsmokingwBP(user, pageObj.liverCancer, report);
        pageObj.liverCancer.selectCheckboxAndProgress(LiverCancerType.LIVER_DISEASE.label, pageObj.kickoutPage, report);
        endNewRecord(132);

        startNewRecord();
        hf.runDxHFNonsmokingwBP(user, pageObj.vaginalBleeding, report);
        common.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        endNewRecord(141);

        startNewRecord();
        hf.runDxHFNonsmokingwBP(user, pageObj.diabetes, report);
        common.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        endNewRecord(150);

        startNewRecord();
        hf.runDxHFNonsmokingwBP(user, pageObj.pregnant, report);
        common.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        endNewRecord(159);

        startNewRecord();
        hf.runDxHFNonsmokingwBP(user, pageObj.breastFeeding, report);
        common.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        endNewRecord(168);

        startNewRecord();
        hf.runDxHFNonsmokingwBP(user, pageObj.pregnancyLoss, report);
        common.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        endNewRecord(177);

        startNewRecord();
        hf.runDxHFNonsmokingwBP(user, pageObj.migraines, report);
        common.clickYesNoNextToPage("Yes", pageObj.kickoutPage, report);
        endNewRecord(186);

        startNewRecord();
        hf.runDxHFNonsmokingwBP(user, pageObj.enterBP, report);
        user.systolic="121";
        user.diastolic="81";
        pageObj.enterBP.enterBPAndProgress(user, pageObj.kickoutPage,report);
        endNewRecord(196);

        startNewRecord();
        hf.runDxHFNonsmokingwBP(user, pageObj.enterBP, report);
        user.systolic="190";
        user.diastolic="130";
        pageObj.enterBP.enterBPAndProgress(user, pageObj.kickoutPage,report);
        endNewRecord(204);
    }
}
