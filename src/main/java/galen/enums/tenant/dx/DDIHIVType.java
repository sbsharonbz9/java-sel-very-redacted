package galen.enums.tenant.dx;

import org.openqa.selenium.By;

public enum DDIHIVType {
    FOSAMPRENAVIR("Fosamprenavir",By.id("")),
    DARUNAVIR("Darunavir", By.id("")),
    EFAVIRENZ("Efavirenz",By.id("")),
    ETRAVIRINE("Etravirine",By.id("")),
    NELFINAVIR("Nelfinavir",By.id("")),
    NEVIRAPINE("Nevirapine",By.id("")),
    RITONAVIR("Ritonavir", By.id("")),
    NONE_OF_THESE("None of these",By.xpath("none"));

    public final String label;
    public final By locator;

    DDIHIVType(String label, By byValue) {
        this.locator=byValue;
        this.label = label;
    }
}
