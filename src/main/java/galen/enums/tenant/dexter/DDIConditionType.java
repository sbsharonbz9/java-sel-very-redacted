package galen.enums.tenant.dexter;

public enum DDIConditionType {
    NONE_OF_THESE("None of these"),
    HEPATITIS_C( "Hepatitis C"),
    THYROID_DISEASE("Thyroid disease"),
    BIPOLAR_DISORDER("Bipolar disorder"),
    EPILEPSY("Epilepsy"),
    HIV("HIV"),
    HIGH_CHOLESTEROL("High cholesterol");

    public final String label;

    DDIConditionType (String label) {
        this.label = label;
    }
}
