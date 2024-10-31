package galen.enums.tenant.dexter;

public enum BloodPressureType {
    Yes_Know_BP("Yes, I know my blood pressure numbers"),
    No_Know_BP("No, I don't know my blood pressure numbers but I know my blood pressure is normal"),
    No_DO_NOT_Know_BP("No, I don't know my blood pressure");

    public final String label;

    BloodPressureType(String label) {
        this.label = label;
    }
}

