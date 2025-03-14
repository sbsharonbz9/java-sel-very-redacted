package galen.tenant.dx.HappyFlows;

import galen.base.BaseTest;
import galen.driver.DriverManager;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxUser;
import galen.helpers.tenant.dx.DxUserTemplates;
import galen.pages.tenant.dx.InitialAssessment.DxPageObj;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_Smoker extends BaseTest {
    static String OBJECTIVE = "This Happy Path flow covers the use-case scenario where the user successfully takes the Initial Assessment and completes checkout.\n" +
            "This is intended to provide a reference during testing for a user";
    static String NOTES = "None";
    static String REQUIREMENTS = "None";
    static String REFERENCES = "None";
    DxPageObj pageObj;
    DxUser user;
    String reportName = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_Smoker";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;

    HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_Smoker()  {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_Smoker_Test() throws IOException, InterruptedException {
        WebDriver driver = DriverManager.getDriver();
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES,
                VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_Smoker";
        bh = new BasicHelpers(driver);
        pageObj = new DxPageObj(driver);
        user = new DxUserTemplates().createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_Smoker();
        CommonPageFeatures commonPageFeatures = new CommonPageFeatures(driver);
        pageObj.pritUnl.load(UrlType.DX);

        pageObj.welcomePage.verifyAtPage(report);
        report.addScreenshotStep("Welcome");

        pageObj.welcomePage.clickBegin(report);
        report.addScreenshotStep("Privacy");

        pageObj.privacyPage.clickIAcceptBtnToPage(pageObj.numbers, report);
        report.addScreenshotStep("Numbers");

        pageObj.numbers.clickNextToPage(pageObj.oAuth, report);
        report.addScreenshotStep("Sign In");

        pageObj.oAuth.chooseAccountTypeAndProgress(user, pageObj.usedProduct,report);
        report.addScreenshotStep("HaveUsedProduct");

        pageObj.orderForSelf.clickYesNoNextToModal(user.productUsed, "Tooltip",report);
        pageObj.orderForSelf.clickConfirmModalToPage(pageObj.orderForSelf, report);
        report.addScreenshotStep("ConfirmCustomer");

        commonPageFeatures.clickYesNoNextToPage(user.orderForSelf,pageObj.pregnancy, report);
        report.addScreenshotStep("Prevent Pregnancy");

        commonPageFeatures.clickYesNoNextToPage(user.preventPregnancy,pageObj.menstrual, report);
        report.addScreenshotStep("Menstruation");

        commonPageFeatures.clickYesNoNextToPage(user.menstrualPeriod,pageObj.birthControl, report);
        report.addScreenshotStep("Birth Control");

        commonPageFeatures.clickYesNoNextToPage(user.birthControl,pageObj.smoking, report);
        report.addScreenshotStep("Smoking");

        pageObj.smoking.selectRadioResponseAndProgress(user.smokingType.label, pageObj.birthYear,report);
        report.addScreenshotStep("Smoking Birth Year");

        pageObj.birthYear.fillOutBirthdayAndProgress(user,pageObj.smokingDisclaimer, report);
        report.addScreenshotStep("Smoking Disclaimer");

        commonPageFeatures.clickNextToPage(pageObj.everHadCancer, report);
        report.addScreenshotStep("Ever Had Cancer");

        commonPageFeatures.clickYesNoNextToPage(user.everHadCancer, pageObj.bloodPressureMeds,report);
        report.addScreenshotStep("Blood Pressure Meds");

        commonPageFeatures.clickYesNoNextToPage(user.bloodPressureMeds,pageObj.cardiacRisk, report);
        report.addScreenshotStep("Heart Conditions");

        pageObj.cardiacRisk.selectCheckboxesAndProgress(user.chestPainType, pageObj.bloodClot, report);
        report.addScreenshotStep("Blood Clot");

        pageObj.bloodClot.selectCheckboxesAndProgress(user.bloodClot, pageObj.irregularHeartBeat, report);
        report.addScreenshotStep("Irregular Heartbeat");

        commonPageFeatures.clickYesNoNextToPage(user.irregularHeartBeat, pageObj.liverCancer, report);
        report.addScreenshotStep("Liver Cancer");

        pageObj.liverCancer.selectCheckboxesAndProgress(user.liverCancer, pageObj.vaginalBleeding, report);
        report.addScreenshotStep("Vaginal Bleeding");

        pageObj.vaginalBleeding.clickYesNoNextToPage(user.vaginalBleeding,pageObj.diabetes, report);
        report.addScreenshotStep("Diabetes");

        commonPageFeatures.clickYesNoNextToPage(user.diabetes, pageObj.pregnant, report);
        report.addScreenshotStep("Pregnant");

        commonPageFeatures.clickYesNoNextToPage(user.pregnant,pageObj.breastFeeding, report);
        report.addScreenshotStep("Breastfeeding");

        commonPageFeatures.clickYesNoNextToPage(user.breastfeeding,pageObj.pregnancyLoss, report);
        report.addScreenshotStep("Pregnancy Loss");

        commonPageFeatures.clickYesNoNextToPage(user.pregnancyLoss, pageObj.migraines, report);
        report.addScreenshotStep("Migraines");

        commonPageFeatures.clickYesNoNextToPage(user.migraines, pageObj.obesity,report);
        report.addScreenshotStep("Obesity");

        pageObj.obesity.enterHeightAndWeightAndProgress(user, pageObj.ddiCondition,report);
        report.addScreenshotStep("DDI Conditions");

        pageObj.ddiCondition.selectCheckboxesAndProgress(user.conditionType, pageObj.antifungal, report);
        report.addScreenshotStep("Antifungal");

        commonPageFeatures.clickYesNoNextToPage(user.isAntifungal, pageObj.otherMedication, report);
        report.addScreenshotStep("Other Medications");

        pageObj.otherMedication.selectCheckboxesAndProgress(user.otherMedicationType, pageObj.gallbladder, report);
        report.addScreenshotStep("Gall Bladder");

        commonPageFeatures.clickYesNoNextToPage(user.gallbladder, pageObj.depression,report);
        report.addScreenshotStep("Depression");

        commonPageFeatures.clickYesNoNextToPage(user.depression, pageObj.knowBPNumber,report);
        report.addScreenshotStep("KnowBP");

        pageObj.knowBPNumber.clickYesAndOpenModal(report);
        report.addScreenshotStep("VerifyThreeMonths");

        pageObj.knowBPNumber.clickYesNoModalToPage(user.measuredIn3Months, pageObj.enterBP, report);
        report.addScreenshotStep("Enter BP");

        pageObj.enterBP.enterBPAndProgress(user, pageObj.review,report);
        report.addScreenshotStep("Editable Summary");

        pageObj.review.clickConfirmToOpenModal(report);
        report.addScreenshotStep("Confirm Modal");

        pageObj.review.clickFinishToOauth(report);
        report.addScreenshotStep("Sign In");

        pageObj.oAuthPostReview.chooseAccountTypeAndProgress(user, pageObj.purchaseOptions,report);
        report.addScreenshotStep("Purchase Options");
    }
}
