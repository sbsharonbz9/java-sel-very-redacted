package galen.helpers.tenant.dx;

import galen.enums.tenant.dx.BloodPressureType;
import galen.enums.tenant.dx.SmokeType;
import galen.helpers.common.GalenUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DxUser extends GalenUser {
    public String defaultNoneText = "None of these";
    public ArrayList<String> defaultNone = new ArrayList<>(Collections.singletonList(defaultNoneText));

    public String productUsed = "No";
    public String orderForSelf = "Yes";
    public String preventPregnancy = "Yes";
    public String menstrualPeriod = "Yes";
    public String birthControl ="No";
    public SmokeType smokingType = SmokeType.DO_NOT_SMOKE;
    public String hadBirthday="Yes";
    public String everHadCancer = "No";
    public ArrayList<String> cancerList = new ArrayList<>(Collections.singletonList("Other cancer"));
    public String bloodPressureMeds = "No";
    public ArrayList<String> chestPainType = defaultNone;
    public ArrayList<String> bloodClot = defaultNone;
    public String irregularHeartBeat = "No";
    public ArrayList<String> liverCancer = defaultNone;
    public String vaginalBleeding= "No";
    public String diabetes = "No";
    public String pregnant = "No";
    public String breastfeeding = "No";
    public String pregnancyLoss= "No";
    public String migraines= "No";
    public String weight="125";
    public String height="5 feet, 5 inches";
    public ArrayList<String> conditionType = defaultNone;
    public ArrayList<String> hepCMeds = defaultNone;
    public ArrayList<String> epBipolarMeds = defaultNone;
    public ArrayList<String> hivMeds = defaultNone;
    public ArrayList<String> thyroidMeds = new ArrayList<>(Collections.singletonList("No thyroid medication"));
    public ArrayList<String> highCholMeds = defaultNone;
    public ArrayList<String> antiFungalMeds = defaultNone;
    public String isAntifungal = "No";
    public ArrayList<String> otherMedicationType = defaultNone;
    public String gallbladder= "No";
    public String depression= "No";
    public String diagnosedDepression = "No";
    public BloodPressureType knowBPType = BloodPressureType.Yes_Know_BP;
    public String measuredIn3Months="Yes";
    public String systolic= "115";
    public String diastolic= "75";
    public String askedDoctor="Yes";
}

