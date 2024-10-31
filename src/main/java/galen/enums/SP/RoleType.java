package galen.enums.SP;

import galen.utils.ConfigLoader;

public enum RoleType {
    ADMIN( "admin"+ ConfigLoader.getInstance().getSPDexSuffix(), "Admin"),
    STUDY_STAFF_LEAD("sslead"+ ConfigLoader.getInstance().getSPDexSuffix(), "Study Staff Lead"),
    STUDY_STAFF("studystaff"+ ConfigLoader.getInstance().getSPDexSuffix(), "Study Staff"),
    CENTRAL_ASSESSOR("centralassessor"+ ConfigLoader.getInstance().getSPDexSuffix(), "Central Assessor"),
    CLINICIAN("clinician"+ ConfigLoader.getInstance().getSPDexSuffix(),"Clinician"),
    CLINICIAN_LEAD("clinicianlead"+ ConfigLoader.getInstance().getSPDexSuffix(), "Clinician Lead");

    public final String email;
    public final String ui_name;

    RoleType(String email, String ui_name) {
        this.email = email;
        this.ui_name = ui_name;
    }
}
