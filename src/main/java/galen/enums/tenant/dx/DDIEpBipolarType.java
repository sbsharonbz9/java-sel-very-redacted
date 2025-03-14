package galen.enums.tenant.dx;

import org.openqa.selenium.By;

public enum DDIEpBipolarType {
    BARBITUATES("Barbiturates", By.id("")),
    FELBAMATE("Felbamate",By.id("")),
    LAMOTRAGINE("Lamotrigine",By.id("")),
    PHENYTOIN("Phenytoin",By.id("")),
    PRIMADONE("Primidone",By.id("")),
    RUFINAMIDE("Rufinamide",By.id("")),
    NONE_OF_THESE("None of these",By.xpath("none"));

    public final String label;
    public final By locator;

    DDIEpBipolarType(String label, By byValue) {
        this.locator=byValue;
        this.label = label;
    }
}
