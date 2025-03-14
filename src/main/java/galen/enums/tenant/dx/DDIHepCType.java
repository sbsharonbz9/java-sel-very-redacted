package galen.enums.tenant.dx;

// All options listed are in UI. Unused ones were intended for use in future tests.
public enum DDIHepCType {
    OMBITASVIR("Ombitasvir"),
    PARITAPREVIR("Paritaprevir"),
    RITONAVIR("Ritonavir with dasabuvir"),
    NONE_OF_THESE("None of these");

    public final String label;

    DDIHepCType(String label) {
        this.label = label;
    }
}
