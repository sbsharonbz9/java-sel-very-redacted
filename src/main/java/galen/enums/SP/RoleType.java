package galen.enums.SP;

import galen.utils.ConfigLoader;

import java.util.Arrays;
import java.util.List;

public enum RoleType {
    ADMIN( "admin"+ ConfigLoader.getInstance().getSPDexSuffix(), "Admin",
            Arrays.asList(AccountTabs.PARTICIPANTS, AccountTabs.ACCOUNTS,
                    AccountTabs.RECORDS, AccountTabs.STUDY, AccountTabs.SITE, AccountTabs.MY_PASSWORD)),
    STUDY_STAFF_LEAD("sslead"+ ConfigLoader.getInstance().getSPDexSuffix(), "Study Staff Lead",Arrays.asList(AccountTabs.PARTICIPANTS, AccountTabs.ACCOUNTS,
            AccountTabs.RECORDS, AccountTabs.STUDY, AccountTabs.SITE, AccountTabs.MY_PASSWORD)),
    STUDY_STAFF("studystaff"+ ConfigLoader.getInstance().getSPDexSuffix(), "Study Staff",
            Arrays.asList(AccountTabs.PARTICIPANTS, AccountTabs.MY_PASSWORD)),
    CENTRAL_ASSESSOR("centralassessor"+ ConfigLoader.getInstance().getSPDexSuffix(), "Central Assessor",
            Arrays.asList(AccountTabs.PARTICIPANTS, AccountTabs.RECORDS, AccountTabs.MY_PASSWORD)),
    CLINICIAN("clinician"+ ConfigLoader.getInstance().getSPDexSuffix(),"Clinician",
            Arrays.asList(AccountTabs.PARTICIPANTS, AccountTabs.MY_PASSWORD)),
    CLINICIAN_LEAD("clinicianlead"+ ConfigLoader.getInstance().getSPDexSuffix(), "Clinician Lead",
            Arrays.asList(AccountTabs.PARTICIPANTS, AccountTabs.ACCOUNTS, AccountTabs.MY_PASSWORD));

    public final String email;
    public final String ui_name;
    public final List<AccountTabs> tabs;

    RoleType(String email, String ui_name, List<AccountTabs> tabs) {
        this.email = email;
        this.ui_name = ui_name;
        this.tabs = tabs;
    }
}
