package galen.enums.tenant.dexter;

public enum DDIOtherMedsType {

    RIFABUTIN("Rifabutin"),
    RIFAMPIN("Rifampin"),
    PREDNISONE("Prednisone"),
    TEMAZEPAM("Temazepam"),
    ST_JOHNS_WORT("St. John's Wort"),
    NONE_OF_THESE("None of these");

    public final String label;

    DDIOtherMedsType(String label) {
        this.label = label;
    }
}
