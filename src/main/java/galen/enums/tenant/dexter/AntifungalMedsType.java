package galen.enums.tenant.dexter;

// All options listed are in UI. Unused ones were intended for use in future tests.
public enum AntifungalMedsType {
    FLUCONAZOLE("Fluconazole"),
    GRISEOFULVIN("Griseofulvin"),
    GRISEOFULVIN_ULTRAMICROCRYSTALLINE("Griseofulvin Ultramicrocrystalline"),
    ITRACONAZOLE("Itraconazole"),
    KETOCONAZOLE("Ketoconazole"),
    VORICONAZOLE("Voriconazole"),
    NONE_OF_THESE("None of these");

    public final String label;

    AntifungalMedsType(String label) {
        this.label = label;
    }
}
