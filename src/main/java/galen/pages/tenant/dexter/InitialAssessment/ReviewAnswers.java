package galen.pages.tenant.dexter.InitialAssessment;

import galen.enums.tenant.dexter.BloodPressureType;
import galen.enums.tenant.dexter.DDIConditionType;
import galen.enums.tenant.dexter.ReviewAnswersLinks;
import galen.enums.tenant.dexter.SmokeType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dexter.DexterUser;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.List;

public class ReviewAnswers extends BasePage {

    public By confirmButton = By.className("summary-button");
    By uniqueID = By.className("assessment-content");
    By confirmModal = By.className("confirm-answers-modal");
    By getGoBackButton = By.cssSelector(".confirm-answers-buttons > button:nth-child(1)");
    By finishButton = By.cssSelector(".confirm-answers-buttons > button:nth-child(2)");

    By editBuyingZenaLink = By.xpath("//button[@value='4']");
    By preventPregnancyEdit = By.xpath("//button[@value='5']");
    By menstrualEdit = By.xpath("//button[@value='6']");
    By editBirthControlLink = By.xpath("//h2[@class='summary__item__question'][contains(text(),'Birth Control')]//following-sibling::button");
    By editSmokingOrVapeLink = By.xpath("//button[@value='9']");
    By editHaveYouBirthDateLink = By.xpath("//h2[@class='summary__item__question'][contains(text(),'birthday')]//following-sibling::button");
    By editEverHadCancerLink = By.xpath("//button[@value='12']");
    By editAnyCancersLink = By.xpath("//h2[@class='summary__item__question'][text()='Any Cancer']//following-sibling::button");
    By editBPMedsLink = By.xpath("//button[@value='14']");
    By editCardiacLink = By.xpath("//h2[@class='summary__item__question'][text()='Chest Pain/Heart " +
            "Attack/Stroke/Mini Stroke']//following-sibling::button");
    By editBloodClotLink = By.xpath("//h2[@class='summary__item__question'][contains(text(),'Blood Clots')]//following-sibling::button");
    By editIrregularHeartbeatLink = By.xpath("//h2[@class='summary__item__question'][text()='Other Heart Issues']//following-sibling::button");
    By editLiverCancerLink = By.xpath("//h2[@class='summary__item__question'][contains(text(),'Liver')]//following-sibling::button");
    By editVaginalBleedingLink = By.xpath("//h2[@class='summary__item__question'][contains(text(),'Unexplained Vaginal " +
            "Bleeding')]//following-sibling::button");
    By editDiabetesLink = By.xpath("//h2[@class='summary__item__question'][contains(text(),'Diabetic')]//following-sibling::button");
    By editPregnancyLink = By.xpath("//h2[@class='summary__item__question'][contains(text(),'Pregnant')]//following-sibling::button");
    By editBreastfeedingLink = By.xpath("//h2[@class='summary__item__question'][contains(text(),'Breastfeeding')]//following-sibling::button");
    By editPregnancyLossLink = By.xpath("//h2[@class='summary__item__question'][contains(text(),'Pregnancy ended')]//following-sibling::button");
    By editMigrainesLink = By.xpath("//h2[@class='summary__item__question'][contains(text(),'Migraines')]//following-sibling::button");
    By editHeightLink = By.xpath("//h2[@class='summary__item__question'][contains(text(),'Height')]//following-sibling::button");
    By editCurrentWeightLink = By.xpath("//button[@value='25']");
    By editMedicalConditionsLink = By.xpath("//button[@value='26']");
    By editHepCMedicationsLink = By.xpath("//button[@value='27']");
    By editThyroidDiseaseMedicationsLink = By.xpath("//button[@value='28']");
    By editEpilepsyOrBipolarLink = By.xpath("//button[@value='29']");
    By editHIVMedicationsLink = By.xpath("//button[@value='30']");
    By editCholesterolMedicationLink = By.xpath("//button[@value='31']");
    By editAntifungalProductsLink = By.xpath("//button[@value='32']");
    By editAntifungalMedLink = By.xpath("//button[@value='33']");
    By editOtherMedLink = By.xpath("//button[@value='34']");
    By editGallbladderLink = By.xpath("//button[@value='35']");
    By editDepressionLink = By.xpath("//button[@value='36']");
    By editClinicallyDiagnosedLink = By.xpath("//button[@value='37']");
    By editKnowsBPNumbersLink = By.xpath("//button[@value='38']");
    By editBloodPressureLink = By.xpath("//button[@value='39']");

    public ReviewAnswers(WebDriver driver) {
        super(driver);
        headingTitle = uniqueID;
        titleText = "Review";
        reportText = "Editable Summary Screen";
        btnConfirmModal=confirmModal;
    }

    public WebElement getConfirmButton() {
        return basicHelpers.getWebElement(confirmButton);
    }

    public WebElement getConfirmModal() {
        return basicHelpers.getWebElement(confirmModal);
    }

    public WebElement getFinishButton() {
        return basicHelpers.getWebElement(finishButton);
    }

    public WebElement goBackButton() {
        return basicHelpers.getWebElement(getGoBackButton);
    }

    public boolean clickConfirmToOpenModal(@Nullable GalenReport report) {
        return basicHelpers.verifyClickToNavDisplayed(confirmButton, "Confirm", confirmModal, "Confirm Modal",
                report);
    }

    public void addressConfirmations(@Nullable GalenReport report) {
        clickConfirmToOpenModal(report);
        clickFinishToOauth(report);
    }

    public Boolean verifyConfirmModalOpen(@Nullable GalenReport report) {
        WebElement confirm = getConfirmModal();
        if (report != null) {
            report.verifyActionToNavDisplayed("Verify 'Confirm' modal appears", confirm,
                    "'Confirm' modal", confirm != null);
        }
        return confirm != null;
    }

    public void clickGoBack(@Nullable GalenReport report) {
        basicHelpers.clickFlex(btnBack, "Go Back", report);
    }

    public void clickFinishToOauth(@Nullable GalenReport report) {
        basicHelpers.verifyClickToPageTransition( new OAuthPostReview(driver), getFinishButton(),"Finish", report);
    }

    public void clickLiverEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(editLiverCancerLink);
        basicHelpers.clickFlex(element, "Liver Cancer Edit link", report);
    }

    public void clickPregnancyEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(editPregnancyLink);
        basicHelpers.clickFlex(element, "Pregnancy Edit link", report);
    }

    public void clickPregnancyLossEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(editPregnancyLossLink);
        basicHelpers.clickFlex(element, "Pregnancy Loss Edit link", report);
    }

    public void clickGallBladderEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(editGallbladderLink);
        basicHelpers.clickFlex(element, "Gallbladder Edit link", report);
    }

    public void clickBirthControlEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(editBirthControlLink);
        basicHelpers.clickFlex(element, "Birth Control Edit link", report);
    }

    public void clickCardiacEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(editCardiacLink);
        basicHelpers.clickFlex(element, "Cardiac Edit link", report);
    }

    public void clickDiabetesEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(editDiabetesLink);
        basicHelpers.clickFlex(element, "Diabetes Edit link", report);
    }

    public void clickMenstrualEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(menstrualEdit);
        basicHelpers.clickFlex(element, "Menstrual Edit link", report);
    }

    public void clickVaginalBleedingEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(editVaginalBleedingLink);
        basicHelpers.clickFlex(element, "Vaginal Bleeding link", report);
    }

    public void clickPreventPregnancyEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(preventPregnancyEdit);
        basicHelpers.clickFlex(element, "Prevent Pregnancy Edit link", report);
    }

    public void clickMigrainesEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(editMigrainesLink);
        basicHelpers.clickFlex(element, "Migraines Edit link", report);
    }

    public void clickBloodClotEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(editBloodClotLink);
        basicHelpers.clickFlex(element, "Blood Clot Edit link", report);
    }

    public void clickIrregularHeartbeatEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(editIrregularHeartbeatLink);
        basicHelpers.clickFlex(element, "Other Heart Issues Edit link", report);
    }

    public void clickBreastfeedingEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(editBreastfeedingLink);
        basicHelpers.clickFlex(element, "Breastfeeding Edit link", report);
    }

    public void clickDepressionEdit(@Nullable GalenReport report) {
        WebElement element = basicHelpers.getWebElement(editDepressionLink);
        basicHelpers.clickFlex(element, "Depression Edit Link", report);
    }

    public void clickEditAnyCancersLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editAnyCancersLink), "'Any Cancers' edit link",
                report);
    }

    public void clickEditCholesterolMedicationLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editCholesterolMedicationLink), "Cholesterol Medications link",
                report);
    }

    public void clickEditHIVMedicationsLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editHIVMedicationsLink), "HIV Medications",
                report);
    }

    public void clickEditBuyingZenaLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editBuyingZenaLink), "Buying Zena",
                report);
    }

    public void clickEditSmokingOrVapeLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editSmokingOrVapeLink), "Smoking or vape",
                report);
    }

    public void clickEditBirthYearLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editHaveYouBirthDateLink), "Have you birth year",
                report);
    }

    public void clickEditEverHadCancerLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editEverHadCancerLink), "Ever Had Cancer",
                report);
    }

    public void clickEditBPMedsLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editBPMedsLink), "BP Meds",
                report);
    }

    public void clickEditHeightLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editHeightLink), "Height",
                report);
    }

    public void clickEditCurrentWeightLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editCurrentWeightLink), "Current Weight",
                report);
    }

    public void clickEditMedicalConditionsLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editMedicalConditionsLink), "Medical Conditions",
                report);
    }

    public void clickEditHepCMedicationsLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editHepCMedicationsLink), "Hepatitis C Medications",
                report);
    }

    public void clickEditThyroidDiseaseMedicationsLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editThyroidDiseaseMedicationsLink), "Thyroid Disease Medications",
                report);
    }

    public void clickEditEpilepsyBipolarDisorderMedicationsLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editEpilepsyOrBipolarLink), "Epilepsy or Bipolar Disorder Medications",
                report);
    }

    public void clickEditAntifungalProductsLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editAntifungalProductsLink), "Antifungal Products",
                report);
    }

    public void clickEditKnowsBloodPressureNumbersLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editKnowsBPNumbersLink), "Knows Blood Pressure Numbers",
                report);
    }

    public void clickEditAntifungalMedLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editAntifungalMedLink), "Antifungal Med",
                report);
    }

    public void clickEditOtherMedLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editOtherMedLink), "Other Med",
                report);
    }

    public void clickEditClinicallyDiagnosedLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editClinicallyDiagnosedLink), "Clinically Diagnosed",
                report);
    }

    public void clickEditBloodPressureLink(@Nullable GalenReport report) {
        basicHelpers.clickFlex(basicHelpers.getWebElement(editBloodPressureLink), "Blood Pressure",
                report);
    }

    public boolean verifyAnswer(ReviewAnswersLinks rl, String expected, @Nullable GalenReport report) {
        By answer = By.xpath("//h2[@class='summary__item__question'][text()='"+ rl.getReviewText()+"']//following-sibling::div");
        WebElement element = basicHelpers.getWebElement(answer);
        return basicHelpers.verifyText(element, rl.getReviewText(), expected, report);
    }

    public void verifyDDIAnswers(DexterUser user, @Nullable GalenReport report) {
        verifyAnswer(ReviewAnswersLinks.DDI,basicHelpers.getStringList(user.conditionType), report);
        if (!user.conditionType.contains(DDIConditionType.NONE_OF_THESE.label)) {
            if (user.conditionType.contains(DDIConditionType.HEPATITIS_C.label)) {
                verifyAnswer(ReviewAnswersLinks.HEPC, basicHelpers.getStringList(user.hepCMeds), report);
            }
            if (user.conditionType.contains(DDIConditionType.THYROID_DISEASE.label)) {
                String thyroidExpected = basicHelpers.getStringList(user.thyroidMeds).replace("No thyroid " +
                        "medication", "None of these");
                verifyAnswer(ReviewAnswersLinks.THYROID, thyroidExpected, report);
            }
            if (user.conditionType.contains(DDIConditionType.EPILEPSY.label) ||
                    user.conditionType.contains(DDIConditionType.BIPOLAR_DISORDER.label)  ) {
                verifyAnswer(ReviewAnswersLinks.EP_BIPOLAR, basicHelpers.getStringList(user.epBipolarMeds), report);
            }
            if (user.conditionType.contains(DDIConditionType.HIV.label)) {
                verifyAnswer(ReviewAnswersLinks.HIV, basicHelpers.getStringList(user.hivMeds), report);
            }
            if (user.conditionType.contains(DDIConditionType.HIGH_CHOLESTEROL.label)) {
                verifyAnswer(ReviewAnswersLinks.HC, basicHelpers.getStringList(user.highCholMeds), report);
            }
        }
    }

    public void verifyAllAnswersCorrect(DexterUser user, @Nullable GalenReport report) {
        verifyAnswer(ReviewAnswersLinks.FOR_SELF,"I am buying for myself", report);
        verifyAnswer(ReviewAnswersLinks.PREVENT_PREGNANCY,"I intend to use this as birth control", report);
        verifyAnswer(ReviewAnswersLinks.MENSTRUAL,user.menstrualPeriod, report);
        if (user.menstrualPeriod.equals("Yes")) {
            verifyAnswer(ReviewAnswersLinks.BIRTH_CONTROL,user.birthControl, report);
        } else {
            verifyAnswer(ReviewAnswersLinks.BIRTH_CONTROLB, user.birthControl, report);
        }
        verifyAnswer(ReviewAnswersLinks.SMOKING, user.smokingType.label, report);
        if (user.smokingType!=SmokeType.DO_NOT_SMOKE) {
            verifyAnswer(ReviewAnswersLinks.YEAR_OF_BIRTH, user.dobYear, report);
            verifyAnswer(ReviewAnswersLinks.HAD_BIRTHDAY, user.hadBirthday, report);
        }
        verifyAnswer(ReviewAnswersLinks.EVER_HAD_CANCER,user.everHadCancer, report);
        if (user.everHadCancer.equals("Yes")) {
            verifyAnswer(ReviewAnswersLinks.CANCER_LIST, basicHelpers.getStringList(user.cancerList), report);
        }
        verifyAnswer(ReviewAnswersLinks.BP_MEDS,user.bloodPressureMeds, report);
        verifyAnswer(ReviewAnswersLinks.CARDIAC,basicHelpers.getStringList(user.chestPainType), report);
        verifyAnswer(ReviewAnswersLinks.BLOOD_CLOTS,basicHelpers.getStringList(user.bloodClot), report);
        verifyAnswer(ReviewAnswersLinks.HEART_ISSUES,user.irregularHeartBeat, report);
        verifyAnswer(ReviewAnswersLinks.LIVER_DISEASE,basicHelpers.getStringList(user.liverCancer), report);
        verifyAnswer(ReviewAnswersLinks.BLEEDING,user.vaginalBleeding, report);
        verifyAnswer(ReviewAnswersLinks.DIABETIC,user.diabetes, report);
        verifyAnswer(ReviewAnswersLinks.PREGNANCY,user.pregnant, report);
        verifyAnswer(ReviewAnswersLinks.BREASTFEEDING,user.breastfeeding, report);
        verifyAnswer(ReviewAnswersLinks.PREGNANCY_LOSS,user.pregnancyLoss, report);
        verifyAnswer(ReviewAnswersLinks.MIGRAINES,user.migraines, report);
        verifyAnswer(ReviewAnswersLinks.HEIGHT,user.height, report);
        verifyAnswer(ReviewAnswersLinks.WEIGHT,user.weight, report);
        verifyDDIAnswers(user, report);
        verifyAnswer(ReviewAnswersLinks.ANITFUNGAL,user.isAntifungal, report);
        if (user.isAntifungal.equals("Yes")) {
            verifyAnswer(ReviewAnswersLinks.ANTIFUNGAL_MEDS,basicHelpers.getStringList(user.antiFungalMeds), report);
        }
        verifyAnswer(ReviewAnswersLinks.OTHER_MEDICATIONS,basicHelpers.getStringList(user.otherMedicationType), report);
        verifyAnswer(ReviewAnswersLinks.GALLBLADDER,user.gallbladder, report);
        verifyAnswer(ReviewAnswersLinks.DEPRESSION,user.depression, report);
        if (user.depression.equals("Yes")) {
            verifyAnswer(ReviewAnswersLinks.CLINICAL_DEPRESSION,user.diagnosedDepression, report);
        }
        verifyAnswer(ReviewAnswersLinks.KNOW_BP, user.knowBPType.label, report);
        if (user.knowBPType.label.equals(BloodPressureType.Yes_Know_BP.label)) {
            verifyAnswer(ReviewAnswersLinks.BP, user.systolic + " / " + user.diastolic, report);
        }

    }

}
