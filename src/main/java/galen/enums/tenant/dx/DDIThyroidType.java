package galen.enums.tenant.dx;

import org.openqa.selenium.By;

public enum DDIThyroidType {
    LEVOTHYROXINE( "Levothyroxine,Often called Synthroid®",By.id("thyroidMeds-levothyroxine"),"levothyroxine" ),
    DIFFERENT_THYROID_MEDS("I'm taking a different thyroid medication", By.id("thyroidMeds-other_thyroid_medication"),
            "other thyroid medication"),
    NO_THYROID_MEDS("No thyroid medication",By.xpath("none"), "none");

    public final String label;
    public final By locator;
    public final String adbuText;

    DDIThyroidType(String label, By byValue, String adbu) {
        this.locator=byValue;
        this.label = label;
        this.adbuText=adbu;
    }
}
