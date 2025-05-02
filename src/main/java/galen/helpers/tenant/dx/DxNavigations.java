package galen.helpers.tenant.dx;

import galen.enums.framework.UrlType;
import galen.enums.tenant.dx.BloodPressureType;
import galen.enums.tenant.dx.DDIConditionType;
import galen.enums.tenant.dx.SmokeType;
import galen.helpers.common.GalenReport;
import galen.helpers.common.Navigations;
import galen.pages.common.BasePage;
import galen.pages.common.OAuth;
import galen.pages.common.PrivacyPage;
import galen.pages.tenant.dx.InitialAssessment.*;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;
import java.io.IOException;
import java.time.Duration;


public class DxNavigations extends Navigations {
    DxPageObj pages;

    public DxNavigations(WebDriver driver) {
        super(driver);
        this.pages = new DxPageObj(driver);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(1));
    }

    public BasePage depressionPath(DxUser user, BasePage endPage, GalenReport report) {
        BasePage nextDepression = (user.depression.equals("Yes")) ? pages.diagnosedDepression : pages.knowBPNumber;
        commonPageFeatures.clickYesNoNextToPage(user.depression, nextDepression, report);

        if (pages.diagnosedDepression.verifyAtPage()) {
            if (endPage.getClass().equals(DiagnosedDepression.class)) { return endPage; }
            if (user.diagnosedDepression.equals("Yes")) {
                pages.diagnosedDepression.clickYesAndAddressModalToPage(pages.knowBPNumber, report);
            } else {
                commonPageFeatures.clickYesNoNextToPage(user.diagnosedDepression, pages.knowBPNumber,  report);
            }
        }
        return pages.knowBPNumber;
    }

    public BasePage DDIPath(DxUser user, BasePage endPage, @Nullable GalenReport report) {
        pages.ddiCondition.selectCheckboxReponses(user.conditionType, report);
        if (user.conditionType.contains(DDIConditionType.HEPATITIS_C.label)) {
            commonPageFeatures.clickNextToPage(pages.ddiHepC, report);
            if (endPage.getClass().equals(DDIHepC.class)) { return endPage; }
            pages.ddiHepC.selectCheckboxReponses(user.hepCMeds, report);
        }
        if (user.conditionType.contains(DDIConditionType.THYROID_DISEASE.label)) {
            commonPageFeatures.clickNextToPage(pages.ddiThyroid, report);
            if (endPage.getClass().equals(DDIThyroid.class)) { return endPage; }
            pages.ddiThyroid.selectCheckboxReponses(user.thyroidMeds, report);
        }
        if (user.conditionType.contains("Epilepsy") ||user.conditionType.contains("Bipolar") ) {
            commonPageFeatures.clickNextToPage(pages.ddiEpBipolar, report);
            if (endPage.getClass().equals(DDIEpBipolar.class)) { return endPage; }
            pages.ddiEpBipolar.selectCheckboxReponses(user.epBipolarMeds, report);
        }

        if (user.conditionType.contains(DDIConditionType.HIV.label)) {
            commonPageFeatures.clickNextToPage(pages.ddihiv, report);
            if (endPage.getClass().equals(DDIHIV.class)) { return endPage; }
            pages.ddihiv.selectCheckboxReponses(user.hivMeds, report);
        }
        if (user.conditionType.contains(DDIConditionType.HIGH_CHOLESTEROL.label)) {
            commonPageFeatures.clickNextToPage(pages.ddiHighCholesterol, report);
            if (endPage.getClass().equals(DDIHighCholesterol.class)) { return endPage; }
            pages.ddiHighCholesterol.selectCheckboxReponses(user.highCholMeds, report);
        }
        commonPageFeatures.clickNextToPage(pages.antifungal, report);
        return pages.antifungal;
    }

    public BasePage antiFungalToADBU(DxUser user, BasePage endPage, @Nullable GalenReport report) {
        if (pages.antifungal.verifyAtPage()) {
            if (endPage.getClass().equals(Antifungal.class)) { return endPage; }
            BasePage nextAntifungal = (user.isAntifungal.equals("Yes")) ? pages.antifungalMeds : pages.otherMedication;
            commonPageFeatures.clickYesNoNextToPage(user.isAntifungal, nextAntifungal, report);
        }

        if (pages.antifungalMeds.verifyAtPage()) {
            if (endPage.getClass().equals(AntifungalMeds.class)) {return endPage;}
            pages.antifungalMeds.selectCheckboxesAndProgress(user.antiFungalMeds, pages.otherMedication, report);
        }

        if (pages.otherMedication.verifyAtPage()) {
            if (endPage.getClass().equals(OtherMedication.class)) { return endPage; }
            pages.otherMedication.selectCheckboxesAndProgress(user.otherMedicationType, pages.gallbladder, report);
        }

        if (pages.gallbladder.verifyAtPage()) {
            if (endPage.getClass().equals(Gallbladder.class)) { return endPage; }
            commonPageFeatures.clickYesNoNextToPage(user.gallbladder, pages.depression, report);
        }

        if (pages.depression.verifyAtPage()) {
            if (pages.depression.verifyAtPage()) {
                if (endPage.getClass().equals(Depression.class)) { return endPage; }
                depressionPath(user, endPage, report);
                if (endPage.getClass().equals(DiagnosedDepression.class)) { return endPage; }
            }
        }

        if (pages.knowBPNumber.verifyAtPage()) {
            if (endPage.getClass().equals(KnowBPNumber.class)) { return endPage; }
            if (user.knowBPType == BloodPressureType.Yes_Know_BP) {
                BasePage nextPage=(user.measuredIn3Months.equals("Yes"))? pages.enterBP:pages.review;
                pages.knowBPNumber.clickYesAndAddressModalToPage( nextPage, user.measuredIn3Months, report);
                if (user.measuredIn3Months.equals("Yes")) {
                    if (endPage.getClass().equals(EnterBP.class)) {return endPage;}
                    pages.enterBP.enterBPAndProgress(user, pages.review, report);
                }
            } else {
                pages.knowBPNumber.selectRadioResponseAndProgress(user.knowBPType.label, pages.review, report);
            }
        }

        if (pages.review.verifyAtPage()) {
            if (endPage.getClass().equals(ReviewAnswers.class)) {return endPage;}
            pages.review.addressConfirmations(report);
        }

        if (pages.oAuthPostReview.verifyAtPage()) {
            if (endPage.getClass().equals(OAuthPostReview.class)) {return endPage;}
            pages.oAuthPostReview.chooseAccountType(user, report);
        }

        if (pages.adbubpScreen.verifyAtPage()) {
            if (endPage.getClass().equals(ADBUBP.class)) {return endPage;}
            pages.adbubpScreen.verifyAtPage(report);
            pages.adbubpScreen.addressConfirmations(user.askedDoctor,report);
        }

        if (pages.adbubpNormal.verifyAtPage()) {
            if (endPage.getClass().equals(ADBUBPNormal.class)) {return endPage;}
            pages.adbubpNormal.verifyAtPage(report);
            pages.adbubpNormal.clickGetBPButtonToEnterBP(report);
        }

        if (pages.adbu.verifyAtPage()) {
            if (endPage.getClass().equals(ADBU.class)) {return endPage;}
            pages.adbu.verifyAtPage(report);
            pages.adbu.addressConfirmations(user.askedDoctor,report);
        }

        return endPage;
    }

    public BasePage partialNavigationIA(DxUser user, BasePage endPage, GalenReport report) throws IOException, InterruptedException {
        if (!pages.welcomePage.verifyAtPage() || (pages.welcomePage.getAssessmentID().equals("N/A")) &&
                (!driver.getCurrentUrl().contains("clinician="))){
            pages.welcomePage.load(UrlType.DX);
        }
        user.assessmentID=pages.welcomePage.getAssessmentID();
        pages.welcomePage.verifyAtPage(report);
        pages.welcomePage.clickBegin(report);

        if (pages.privacyPage.verifyAtPage()) {
            if (endPage.getClass().equals(PrivacyPage.class)) { return endPage; }
            pages.privacyPage.clickIAcceptBtnToPage(pages.numbers, report);
        }

        if (pages.numbers.verifyAtPage()) {
            if (endPage.getClass().equals(Numbers.class)) { return endPage; }
            pages.numbers.clickNext( report);
        }

        if (pages.oAuth.verifyAtPage()) {
            if (endPage.getClass().equals(OAuth.class)) { return endPage;}
            pages.oAuth.verifyAtPage(report);
            pages.oAuth.chooseAccountType(user, report);
        }

        if (pages.usedProduct.verifyAtPage()) {
            if (endPage.getClass().equals(UsedProduct.class)) { return endPage; }
            pages.orderForSelf.clickYesNoNextToModal(user.productUsed, "Tooltip",report);
            pages.orderForSelf.clickCloseToDismiss(report);
        }

        if (pages.orderForSelf.verifyAtPage()) {
            if (endPage.getClass().equals(OrderForSelf.class)) {return endPage;}
            commonPageFeatures.clickYesNoNextToPage(user.orderForSelf,pages.pregnancy, report);
        }

        if (pages.pregnancy.verifyAtPage()) {
            if (endPage.getClass().equals(PreventPregnancy.class)) { return endPage; }
            commonPageFeatures.clickYesNoNextToPage(user.preventPregnancy,pages.menstrual, report);
        }

        if (pages.menstrual.verifyAtPage()) {
            if (endPage.getClass().equals(Menstrual.class)) { return endPage; }
            commonPageFeatures.clickYesNoNextToPage(user.menstrualPeriod,pages.birthControl, report);
        }

        if (pages.birthControl.verifyAtPage()) {
            if (endPage.getClass().equals(BirthControl.class)) { return endPage; }
            if (user.birthControl.equals("Yes")) {
                pages.birthControlB.addressConfirmationsAndProgress(pages.smoking, report);
            } else {
                commonPageFeatures.clickYesNoNextToPage(user.birthControl,pages.smoking, report);
            }
        }

        if (pages.smoking.verifyAtPage()) {
            if (endPage.getClass().equals(Smoking.class)) {return endPage;}
            pages.smoking.verifyAtPage(report);
            if (user.smokingType== SmokeType.DO_NOT_SMOKE) {
                pages.smoking.selectRadioResponseAndProgress(user.smokingType.label, pages.everHadCancer, report);
            } else {
                pages.smoking.selectRadioResponseAndProgress(user.smokingType.label, pages.birthYear, report);
                if (endPage.getClass().equals(BirthYear.class)) { return endPage; }
                pages.birthYear.fillOutBirthdayAndProgress(user,pages.smokingDisclaimer, report);
                if (endPage.getClass().equals(SmokingDisclaimer.class)) { return endPage; }
                pages.smokingDisclaimer.clickNextToPage(pages.everHadCancer,report);
            }
        }

        if (pages.everHadCancer.verifyAtPage()) {
            if (endPage.getClass().equals(EverHadCancer.class)) {return endPage;}
            if (user.everHadCancer.equals("Yes")){
                commonPageFeatures.clickYesNoNextToPage("Yes", pages.cancerList, report);
                if (endPage.getClass().equals(CancerList.class)) { return endPage; }
                pages.cancerList.selectCheckboxesAndProgress(user.cancerList, pages.bloodPressureMeds, report);
            } else {
                commonPageFeatures.clickYesNoNextToPage("No", pages.bloodPressureMeds, report);
            }
        }

        if (pages.bloodPressureMeds.verifyAtPage()) {
            if (endPage.getClass().equals(BloodPressureMeds.class)) {return endPage;}
            commonPageFeatures.clickYesNoNextToPage(user.bloodPressureMeds,pages.cardiacRisk, report);
        }

        if (pages.cardiacRisk.verifyAtPage()) {
            if (endPage.getClass().equals(CardiacRisk.class)) { return endPage; }
            pages.cardiacRisk.selectCheckboxesAndProgress(user.chestPainType, pages.bloodClot, report);
        }

        if (pages.bloodClot.verifyAtPage()) {
            if (endPage.getClass().equals(BloodClot.class)) { return endPage; }
            pages.bloodClot.selectCheckboxesAndProgress(user.bloodClot, pages.irregularHeartBeat, report);
        }

        if (pages.irregularHeartBeat.verifyAtPage()) {
            if (endPage.getClass().equals(IrregularHeartBeat.class)) { return endPage; }
            commonPageFeatures.clickYesNoNextToPage(user.irregularHeartBeat, pages.liverCancer, report);
        }

        if (pages.liverCancer.verifyAtPage()) {
            if (endPage.getClass().equals(LiverCancer.class)) {return endPage;}
            pages.liverCancer.selectCheckboxesAndProgress(user.liverCancer, pages.vaginalBleeding, report);
        }

        if (pages.vaginalBleeding.verifyAtPage()) {
            if (endPage.getClass().equals(VaginalBleeding.class)) {return endPage;}
            commonPageFeatures.clickYesNoNextToPage(user.vaginalBleeding,pages.diabetes, report);
        }

        if (pages.diabetes.verifyAtPage()) {
            if (endPage.getClass().equals(Diabetes.class)) {return endPage;}
            commonPageFeatures.clickYesNoNextToPage(user.diabetes, pages.pregnant,report);
        }

        if (pages.pregnant.verifyAtPage()) {
            if (endPage.getClass().equals(Pregnant.class)) {return endPage;}
            commonPageFeatures.clickYesNoNextToPage(user.pregnant, pages.breastFeeding,report);
        }

        if (pages.breastFeeding.verifyAtPage()) {
            if (endPage.getClass().equals(BreastFeeding.class)) {return endPage;}
            commonPageFeatures.clickYesNoNextToPage(user.breastfeeding, pages.pregnancyLoss,report);
        }

        if (pages.pregnancyLoss.verifyAtPage()) {
            if (endPage.getClass().equals(PregnancyLoss.class)) {return endPage;}
            commonPageFeatures.clickYesNoNextToPage(user.pregnancyLoss,pages.migraines, report);
        }

        if (pages.migraines.verifyAtPage()) {
            if (endPage.getClass().equals(Migraines.class)) {return endPage;}
            commonPageFeatures.clickYesNoNextToPage(user.migraines,pages.obesity, report);
        }

        if (pages.obesity.verifyAtPage()) {
            if (endPage.getClass().equals(ObesityBMI.class)) {return endPage;}
            pages.obesity.enterHeightAndWeightAndProgress(user, pages.ddiCondition,report);
        }

        if (pages.ddiCondition.verifyAtPage()) {
            if (endPage.getClass().equals(DDICondition.class)) {return endPage;}
            if (user.conditionType.contains(DDIConditionType.NONE_OF_THESE.label)) {
                pages.ddiCondition.selectCheckboxesAndProgress(user.conditionType, pages.antifungal, report);
            } else {
                if (DDIPath(user, endPage, report).getClass().toString().contains("DDI")) { return endPage; }
            }
        }
        antiFungalToADBU(user, endPage, report);
        return endPage;
    }

}