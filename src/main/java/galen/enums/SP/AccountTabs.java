package galen.enums.SP;

// All options listed are in UI. Unused ones were intended for use in future tests.
public enum AccountTabs {
    ACCOUNTS("//a[@href='/dashboard/accounts']"),
    PARTICIPANTS("//a[@href='/dashboard/participants']"),
    RECORDS("//a[@href='/dashboard/records']" ),
    STUDY("//a[@href='/dashboard/studies']"),
    SITE("//a[@href='/dashboard/sites']"),
    MY_PASSWORD("//a[@href='/dashboard/my-password']");


    public final String selector;

    AccountTabs(String selector ) {
        this.selector=selector;
    }
}

