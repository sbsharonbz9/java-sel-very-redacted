package galen.pages.tenant.dx.InitialAssessment;

import galen.enums.tenant.dx.BloodPressureType;
import galen.enums.tenant.dx.DDIConditionType;
import galen.enums.tenant.dx.ReviewAnswersLinks;
import galen.enums.tenant.dx.SmokeType;
import galen.helpers.common.GalenReport;
import galen.helpers.tenant.dx.DxUser;
import galen.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;

public class ReviewAnswers extends BasePage {

    public By confirmButton = By.className("summary-button");
    By uniqueID = By.className("assessment-content");
    public By confirmModal = By.className("confirm-answers-modal");
    public By finishButton = By.cssSelector(".confirm-answers-buttons > button:nth-child(2)");

    public ReviewAnswers(WebDriver driver) {
        super(driver);
        headingTitle = uniqueID;
        titleText = "Review";
        reportText = "Editable Summary Screen";
        btnConfirm=confirmButton;
        modal=confirmModal;
    }

    public void addressConfirmations(@Nullable GalenReport report) {
        clickConfirmToOpenModal(report);
        clickFinishToOauth(report);
    }

    public void clickFinishToOauth(@Nullable GalenReport report) {
        basicHelpers.verifyClickToPageTransition( new OAuthPostReview(driver), finishButton,"Finish", report);
    }

    public void verifyAnswer(ReviewAnswersLinks rl, String expected, @Nullable GalenReport report) {
        By answer = By.xpath("//h2[@class='summary__item__question'][text()='"+ rl.getReviewText()+"']//following-sibling::div");
        basicHelpers.verifyText(answer, rl.getReviewText(), expected, report);
    }

    public void clickEditToPage(ReviewAnswersLinks rl, BasePage page, @Nullable GalenReport report) {
        By editButton = By.xpath("//h2[@class='summary__item__question'][text()='"+ rl.getReviewText()+
                "']//following-sibling::button");
        basicHelpers.verifyClickToPageTransition(page, editButton, "Edit link: " + rl.getReviewText(), report);
    }

    public void verifyDDIAnswers(DxUser user, @Nullable GalenReport report) {
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

    public void verifyAllAnswersCorrect(DxUser user, @Nullable GalenReport report) {
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
