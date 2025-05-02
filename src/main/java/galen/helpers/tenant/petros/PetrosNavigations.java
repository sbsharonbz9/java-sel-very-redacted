package galen.helpers.tenant.petros;

import galen.helpers.common.GalenReport;
import galen.helpers.common.Navigations;
import galen.pages.common.BasePage;
import galen.pages.common.OAuth;
import galen.pages.common.PrivacyPage;
import galen.pages.tenant.petros.InitialAssessment.*;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;
import java.io.IOException;

public class PetrosNavigations extends Navigations {
    PetrosPageObj pages;

    public PetrosNavigations(WebDriver driver) {
        super(driver);
        this.pages = new PetrosPageObj(driver);
    }

    public void petrosHappyFlowTo(PetrosUser user, BasePage toPage, @Nullable GalenReport report) throws IOException, InterruptedException {
        boolean atPage;
        new PetrosNavigations(driver).partialNavigationIA(user, toPage, null);
        atPage = toPage.verifyAtPage();
        if (report != null) {
            report.addStep("Execute HappyFlow to '" + toPage.titleText + "' page", "At '" +
                    toPage.titleText + " page", atPage ? "As Expected" : "FAIL", atPage, true);
        }
    }

    public BasePage partialNavigationIA(PetrosUser user, BasePage endPage, GalenReport report) throws IOException, InterruptedException {

        if (pages.welcomePage.verifyAtPage()) {
            pages.welcomePage.clickBegin(report);
        }

        if (pages.privacyPage.verifyAtPage()) {
            if (endPage.getClass().equals(PrivacyPage.class)) { return endPage; }
            pages.privacyPage.clickIAcceptBtnToPage(pages.purchaseSelf,report);
        }

        if (pages.purchaseSelf.verifyAtPage()) {
            if (endPage.getClass().equals(PurchaseSelf.class)) { return endPage; }
            commonPageFeatures.clickYesNoNextToPage( user.forSelf, pages.sexAndBirthYear, report);
        }

        if (pages.sexAndBirthYear.verifyAtPage()) {
            if (endPage.getClass().equals(SexAndBirthYear.class)) { return endPage; }
            pages.sexAndBirthYear.fillOutForm(user, report);
        }

        if (pages.oAuth.verifyAtPage()) {
            if (endPage.getClass().equals(OAuth.class)) { return endPage; }
            pages.oAuth.chooseAccountType( user, report);
        }

        if (pages.erectileDysfunction.verifyAtPage()) {
            if (endPage.getClass().equals(ErectileDysfunction.class)) { return endPage; }
            pages.erectileDysfunction.verifyAtPage(report);
            commonPageFeatures.clickYesNoUnsure( user.recurringDifficulties, report);
            pages.erectileDysfunction.clickNextToPage(pages.questionnaireIntroduction, report);
        }

        if (pages.questionnaireIntroduction.verifyAtPage()) {
            if (endPage.getClass().equals(SHIM_QuestionnaireIntroduction.class)) { return endPage; }
            pages.questionnaireIntroduction.clickNextToPage(pages.questionnaire1, report);
        }

        if (pages.questionnaire1.verifyAtPage()) {
            if (endPage.getClass().equals(SHIM_Questionnaire_1.class)) { return endPage; }
            pages.questionnaire1.selectRadioResponseAndProgress( user.confidence_ss1, pages.questionnaire2, report);
        }

        if (pages.questionnaire2.verifyAtPage()) {
            if (endPage.getClass().equals(SHIM_Questionnaire_2.class)) { return endPage; }
            pages.questionnaire2.selectRadioResponseAndProgress( user.erectionOverTime, pages.questionnaire3, report);
        }

        if (pages.questionnaire3.verifyAtPage()) {
            if (endPage.getClass().equals(SHIM_Questionnaire_3.class)) { return endPage; }
            pages.questionnaire3.selectRadioResponseAndProgress( user.maintainance1, pages.questionnaire4, report);
        }

        if (pages.questionnaire4.verifyAtPage()) {
            if (endPage.getClass().equals(SHIM_Questionnaire_4.class)) { return endPage; }
            pages.questionnaire4.selectRadioResponseAndProgress( user.maintainance2, pages.questionnaire5, report);
        }

        if (pages.questionnaire5.verifyAtPage()) {
            if (endPage.getClass().equals(SHIM_Questionnaire_5.class)) { return endPage; }
            pages.questionnaire5.selectRadioResponseAndProgress( user.satisfaction, pages.otherEDMeds, report);
        }

        if (pages.otherEDMeds.verifyAtPage()) {
            if (endPage.getClass().equals(ADOPBU_OtherED_Meds.class)) { return endPage; }
            commonPageFeatures.clickYesNoNextToPage( user.otherEDMeds, pages.nitrateUse, report);
        }

        if (user.otherEDMeds.equals("Yes") && pages.onceADayInstruction.verifyAtPage()) {
            if (endPage.getClass().equals(ADOPBU_OnceADayInstruction.class)) { return endPage; }
            pages.onceADayInstruction.clickNextToPage(pages.nitrateUse, report);
        }

        if (pages.nitrateUse.verifyAtPage()) {
            if (endPage.getClass().equals(NitrateUse.class)) { return endPage; }
            commonPageFeatures.clickYesNoUnsure( user.nitrateUse, report);
            pages.nitrateUse.clickNextToPage(pages.clarification, report);
        }

        if (pages.clarification.verifyAtPage()) {
            if (endPage.getClass().equals(ClarificationOnNitrateUse.class)) { return endPage; }
            pages.clarification.selectCheckboxesAndProgress( user.clarificationOptions, pages.nitratePoppersUse, report);
        }

        if (pages.nitratePoppersUse.verifyAtPage()) {
            if (endPage.getClass().equals(NitratePoppersUse.class)) { return endPage; }
            pages.nitratePoppersUse.clickYesNoUnsure( user.nitratePopperUse, report);
            pages.nitratePoppersUse.clickNextToPage(pages.heartProblemsInstruction, report);
        }

        if (pages.heartProblemsInstruction.verifyAtPage()) {
            if (endPage.getClass().equals(HeartProblemsInstructional.class)) { return endPage; }
            pages.heartProblemsInstruction.clickNextToPage(pages.heartNotSafeForSex, report);
        }

        if (pages.heartNotSafeForSex.verifyAtPage()) {
            if (endPage.getClass().equals(HeartNotSafeForSex.class)) { return endPage; }
            pages.heartNotSafeForSex.clickYesNoNextToPage( user.heartNotSafeForSex, pages.cardiacConditions1,report);
        }

        if (pages.cardiacConditions1.verifyAtPage()) {
            if (endPage.getClass().equals(ADBU_CardiacConditions_1.class)) { return endPage; }
            pages.cardiacConditions1.selectCheckboxesAndProgress( user.cc1, pages.heartFailure, report);
        }

        if (pages.heartFailure.verifyAtPage()) {
            if (endPage.getClass().equals(HeartFailure.class)) { return endPage; }
            commonPageFeatures.clickYesNoUnsure( user.heartFailure, report);
            pages.heartFailure.clickNextToPage(pages.cardiacConditions2, report);
        }

        if (pages.cardiacConditions2.verifyAtPage()) {
            if (endPage.getClass().equals(ADBU_CardiacConditions_2.class)) { return endPage; }
            pages.cardiacConditions2.selectCheckboxesAndProgress( user.cc2, pages.cardiacConditions3, report);
        }

        if (pages.cardiacConditions3.verifyAtPage()) {
            if (endPage.getClass().equals(ADBU_CardiacConditions_3.class)) { return endPage; }
            pages.cardiacConditions3.selectCheckboxesAndProgress( user.cc3, pages.cardiacConditions4, report);
        }

        if (pages.cardiacConditions4.verifyAtPage()) {
            if (endPage.getClass().equals(ADBU_CardiacConditions_4.class)) { return endPage; }
            pages.cardiacConditions4.selectCheckboxesAndProgress( user.cc4, pages.hypertensionsMedicine, report);
        }

        if (pages.hypertensionsMedicine.verifyAtPage()) {
            if (endPage.getClass().equals(PulmonaryHypertensionsMedicine.class)) { return endPage; }
            commonPageFeatures.clickYesNoUnsure( user.hypertension, report);
            pages.heartFailure.clickNextToPage(pages.highBloodPressureMedicines, report);
        }

        if (pages.highBloodPressureMedicines.verifyAtPage()) {
            if (endPage.getClass().equals(ADOPBU_HighBloodPressureMedicines.class)) { return endPage; }
            commonPageFeatures.clickYesNoUnsure( user.hbp, report);
            pages.highBloodPressureMedicines.clickNextToPage(pages.antifungals, report);
        }

        if (pages.antifungals.verifyAtPage()) {
            if (endPage.getClass().equals(Antifungals.class)) { return endPage; }
            commonPageFeatures.clickYesNoNextToPage( user.antifungals, pages.antibiotics, report);
        }

        if (pages.antibiotics.verifyAtPage()) {
            if (endPage.getClass().equals(Antibiotics.class)) { return endPage; }
            commonPageFeatures.clickYesNoNextToPage( user.antibiotics, pages.antidepressants,report);
        }

        if (pages.antidepressants.verifyAtPage()) {
            if (endPage.getClass().equals(Antidepressants.class)) { return endPage; }
            commonPageFeatures.clickYesNoNextToPage( user.antidepressants, pages.hivMedicines, report);
        }

        if (pages.hivMedicines.verifyAtPage()) {
            if (endPage.getClass().equals(HIVMedicines.class)) { return endPage; }
            commonPageFeatures.clickYesNoNextToPage( user.hiv, pages.noncardiacHealthConditions,report);
        }

        if (pages.noncardiacHealthConditions.verifyAtPage()) {
            if (endPage.getClass().equals(NoncardiacHealthConditions.class)) { return endPage; }
            pages.noncardiacHealthConditions.selectCheckboxesAndProgress( user.nonCardiacHealth,
                    pages.noncardiacAllergicReactions, report);
        }

        if (pages.noncardiacAllergicReactions.verifyAtPage()) {
            if (endPage.getClass().equals(NoncardiacAllergicReactions.class)) { return endPage; }
            commonPageFeatures.clickYesNoNextToPage( user.allergies, pages.erectionLongerThan4Hours, report);
        }

        if (pages.erectionLongerThan4Hours.verifyAtPage()) {
            if (endPage.getClass().equals(ADBU_ErectionLongerThan4Hours.class)) { return endPage; }
            pages.erectionLongerThan4Hours.clickYesNoUnsure( user.longErection, report);
            pages.erectionLongerThan4Hours.clickNextToPage(pages.noncardiacConditions1, report);
        }

        if (pages.noncardiacConditions1.verifyAtPage()) {
            if (endPage.getClass().equals(ADBU_NoncardiacConditions_1.class)) { return endPage; }
            pages.noncardiacConditions1.selectCheckboxesAndProgress( user.nonCardiac_1,
                    pages.peyroniesDisease, report);
        }

        if (pages.peyroniesDisease.verifyAtPage()) {
            if (endPage.getClass().equals(ADBU_PeyroniesDisease.class)) { return endPage; }
            commonPageFeatures.clickYesNoNextToPage( user.peyronies, pages.noncardiacConditions2, report);
        }

        if (pages.noncardiacConditions2.verifyAtPage()) {
            if (endPage.getClass().equals(ADBU_NoncardiacConditions_2.class)) { return endPage; }
            pages.noncardiacConditions2.selectCheckboxesAndProgress( user.nonCardiac_2,
                    pages.noncardiacConditions3, report);
        }

        if (pages.noncardiacConditions3.verifyAtPage()) {
            if (endPage.getClass().equals(ADBU_NoncardiacConditions_3.class)) { return endPage; }
            pages.noncardiacConditions3.selectCheckboxesAndProgress( user.nonCardiac_3,
                    pages.enlargedProstateMedicine, report);
        }

        if (pages.enlargedProstateMedicine.verifyAtPage()) {
            if (endPage.getClass().equals(ADOPBU_EnlargedProstateMedicine.class)) { return endPage; }
            commonPageFeatures.clickYesNoNextToPage( user.enlargedProstate, pages.edAndHeartDiseaseInfo, report);
        }

        if (pages.edAndHeartDiseaseInfo.verifyAtPage()) {
            if (endPage.getClass().equals(ED_AndHeartDiseaseInfo.class)) { return endPage; }
            pages.edAndHeartDiseaseInfo.clickNextToPage( pages.review, report);
        }

        if (pages.review.verifyAtPage()) {
            if (endPage.getClass().equals(ReviewAnswers.class)) { return endPage;}
            pages.review.addressConfirmationsAndProgress(pages.oAuth, report);
            pages.oAuth.chooseAccountType(user, report);
        }
        return endPage;
    }
}
