package galen.enums.tenant.dx


enum ReviewAnswersLinks {
    FOR_SELF("Buying Zena"),
    PREVENT_PREGNANCY("Buying to Prevent Pregnancy"),
    MENSTRUAL("Menstrual Periods"),
    BIRTH_CONTROL("Hormonal Birth Control"),
    BIRTH_CONTROLB("Birth control"),
    SMOKING("Smoking or Vaping"),
    YEAR_OF_BIRTH("Year of Birth"),
    HAD_BIRTHDAY("Have you had your birthday yet this year?"),
    EVER_HAD_CANCER("Ever Had Cancer"),
    CANCER_LIST("Any Cancer"),
    BP_MEDS("Blood Pressure Medication"),
    CARDIAC("Chest Pain/Heart Attack/Stroke/Mini Stroke"),
    BLOOD_CLOTS("Blood Clots/Family History or Personal History of Blood Clots"),
    HEART_ISSUES("Other Heart Issues"),
    LIVER_DISEASE("Liver Disease/Liver Cancer/Hepatitis C"),
    BLEEDING("Unexplained Vaginal Bleeding"),
    DIABETIC("Diabetic"),
    PREGNANCY("Currently Pregnant"),
    BREASTFEEDING("Breastfeeding"),
    PREGNANCY_LOSS("Pregnancy ended in last 4 weeks"),
    MIGRAINES("Migraines with Aura"),
    HEIGHT("Height"),
    WEIGHT("Current Weight"),
    DDI("Medical Conditions"),
    HEPC("Hepatitis C Medications"),
    THYROID("Thyroid Disease Medications"),
    EP_BIPOLAR("Epilepsy or Bipolar Disorder Medications"),
    HIV("HIV Medications"),
    HC("Cholesterol Medications"),
    ANITFUNGAL("Antifungal Products"),
    ANTIFUNGAL_MEDS("Antifungal Medications"),
    OTHER_MEDICATIONS("Other Medications"),
    GALLBLADDER("Gallbladder Disease"),
    DEPRESSION("Depression"),
    CLINICAL_DEPRESSION("Clinically Diagnosed Depression"),
    KNOW_BP("Knows Blood Pressure Numbers"),
    BP("Blood Pressure");

    String reviewText

    ReviewAnswersLinks(String reviewText) {
        this.reviewText = reviewText
    }
}