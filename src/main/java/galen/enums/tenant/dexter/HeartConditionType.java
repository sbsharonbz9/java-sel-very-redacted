package galen.enums.tenant.dexter;

// All options listed are in UI. Unused ones were intended for use in future tests.
public enum HeartConditionType {
    CHEST_PAIN("Chest pain"),
    HEART_ATTACK("Heart attack"),
    STROKE("Stroke"),
    MINI_STROKE("Mini stroke"),
    NONE_OF_THESE("None of these");

    public final String label;

    HeartConditionType (String label) {
        this.label = label;
    }
}
