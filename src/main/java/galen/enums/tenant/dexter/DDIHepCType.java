package galen.enums.tenant.dexter;

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
