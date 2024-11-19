package galen.enums.SP;

import java.util.ArrayList;

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

