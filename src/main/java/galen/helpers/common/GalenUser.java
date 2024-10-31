package galen.helpers.common;

import galen.enums.common.OAuthType;

public class GalenUser {
    public String dobYear="1995";
    public String email="";
    public OAuthType accountType=OAuthType.GUEST;
    public CheckoutInfo checkoutInfo = new CheckoutInfo();
    public String assessmentID="";
}

