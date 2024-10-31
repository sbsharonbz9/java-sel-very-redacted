package galen.enums.common;

import org.openqa.selenium.By;

public enum OAuthType {
    GUEST (By.xpath("//button[@testid='guest-sign-in']")),
    GOOGLE (By.xpath("//button[@testid='google-sign-in']")),
    APPLE (By.xpath("//button[@testid='apple-sign-in']"));

    public final By nav;

    OAuthType (By nav) {
        this.nav = nav;
    }
}

