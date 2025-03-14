package galen.enums.tenant.dx;

import org.openqa.selenium.By;

public enum DDIHighCholType {
    ATORVASTATIN("Atorvastatin", By.id("")),
    COLESEVELAM("Colesevelam", By.id("")),
    ROSUVASTATIN("Rosuvastatin", By.id("")),
    NONE_OF_THESE("None of these",By.xpath("none"));

    public final String label;
    public final By locator;

    DDIHighCholType(String label, By byValue) {
        this.locator=byValue;
        this.label = label;
    }
}
