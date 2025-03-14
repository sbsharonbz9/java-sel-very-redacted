package galen.enums.tenant.dx;

public enum SmokeType {
    DO_NOT_SMOKE("I don't smoke or Vape"),
    SMOKE_REGULARLY("I smoke or Vape regularly"),
    SMOKE_OCCASIONALLY("I smoke or Vape occasionally"),
    RARELY_SMOKE("I rarely smoke or Vape");

    public final String label;

    SmokeType (String label) {
        this.label = label;
    }

}
