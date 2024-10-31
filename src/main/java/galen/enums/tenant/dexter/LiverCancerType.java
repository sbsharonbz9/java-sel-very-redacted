package galen.enums.tenant.dexter;


public enum LiverCancerType {
    LIVER_DISEASE( "Liver disease"),
    LIVER_CANCER("Liver cancer"),
    HEPATITIS_C("Hepatitis C"),
    NONE_OF_THESE("None of these");

    public final String label;

    LiverCancerType(String label) {
        this.label = label;
    }
}
