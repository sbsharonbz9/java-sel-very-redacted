package galen.enums.tenant.dexter;

// All options listed are in UI. Unused ones were intended for use in future tests.
public enum CancerType {
    Breast_Cancer("Breast cancer"),
    Endometrial_Cancer("Endometrial (uterine) cancer"),
    Melanoma("Melanoma"),
    Ovarian_Cancer("Ovarian cancer"),
    Lung("Lung/bronchial adenocarcinoma"),
    Meningioma("Meningioma"),
    Liver_Cancer("Liver cancer"),
    Other_Cancer("Other cancer");

    public final String label;

    CancerType(String label) {
        this.label = label;
    }
}

