package galen.helpers.sp;

public class ParticipantClass {

    public String email;
    public String studyID;
    public String siteID;
    public String participantID;

    public ParticipantClass(String emailValue, String site, String study) {
        this.studyID=study;
        this.siteID=site;
        this.email=emailValue;
    }

    public ParticipantClass() {

    }
}
