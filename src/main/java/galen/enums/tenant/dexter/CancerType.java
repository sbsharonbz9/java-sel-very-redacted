package galen.enums.tenant.dexter;


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

