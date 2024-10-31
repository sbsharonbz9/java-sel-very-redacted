package galen.helpers.tenant.petros;

import galen.enums.common.Gender;
import galen.helpers.common.GalenUser;

import java.util.ArrayList;
import java.util.Arrays;

public class PetrosUser extends GalenUser {
        ArrayList<String> defaultNone = (ArrayList<String>) Arrays.asList(new String[] {"None of these"});
        public Gender gender= Gender.Male;
        public String dobDay="01";
        public String dobMonth="01";
        public String forSelf = "Yes";
        public String recurringDifficulties = "Yes";
        public String otherEDMeds= "No";
        public String nitrateUse= "No";
        public String nitratePopperUse= "No";

        //SHIM
        public String confidence_ss1="Very low";
        public String erectionOverTime="Almost never or never";
        public String maintainance1="Almost never or never";
        public String maintainance2="Extremely difficult";
        public String satisfaction="Almost never or never";

        public ArrayList<String> clarificationOptions = defaultNone;
        public String heartNotSafeForSex= "No";
        public ArrayList<String> cc1 = defaultNone;
        public String heartFailure= "No";
        public ArrayList<String> cc2 = defaultNone;
        public ArrayList<String> cc3 = defaultNone;
        public ArrayList<String> cc4 = defaultNone;
        public String hypertension= "No";
        public String hbp= "No";
        public String antifungals= "No";
        public String antibiotics= "No";
        public String antidepressants= "No";
        public String hiv= "No";
        public ArrayList<String> nonCardiacHealth = defaultNone;
        public String allergies= "No";
        public String longErection= "No";
        public ArrayList<String> nonCardiac_1 = defaultNone;
        public String peyronies= "No";
        public ArrayList<String> nonCardiac_2 = defaultNone;
        public ArrayList<String> nonCardiac_3 = defaultNone;
        public String enlargedProstate= "No";
}
