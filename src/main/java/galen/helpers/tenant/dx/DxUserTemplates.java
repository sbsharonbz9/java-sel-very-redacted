package galen.helpers.tenant.dx;

import galen.enums.tenant.dx.BloodPressureType;
import galen.enums.tenant.dx.SmokeType;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;

public class DxUserTemplates {
    public DxUser createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker() {
        DxUser user = new DxUser();
        user.menstrualPeriod = "Yes";
        user.birthControl = "No";
        user.smokingType = SmokeType.DO_NOT_SMOKE;
        return user;
    }

    public DxUser createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_Smoker() {
        DxUser user = new DxUser();
        user.smokingType = SmokeType.SMOKE_REGULARLY;
        user.dobYear = String.valueOf((Year.now().getValue() - 20));
        user.hadBirthday = "Yes";
        user.menstrualPeriod = "Yes";
        user.birthControl = "No";
        user.depression = "No";
        return user;
    }

    public DxUser createHappyFlow_IA_Initial_Assessment_to_Checkout_noBP_NonSmoker() {
        DxUser user = new DxUser();
        user.knowBPType = BloodPressureType.No_DO_NOT_Know_BP;
        return user;
    }

    public DxUser createHappyFlow_IA_Initial_Assessment_ADBU_noBP() {
        DxUser user = createHappyFlow_IA_Initial_Assessment_ADBU_wBP();
        user.knowBPType= BloodPressureType.No_DO_NOT_Know_BP;
        return user;
    }

    public DxUser createHappyFlow_IA_Initial_Assessment_ADBU_wBP() {
        DxUser user = new DxUser();
        user.everHadCancer="Yes";
        user.hadBirthday="No";
        user.systolic= "110";
        user.diastolic= "70";
        user.knowBPType= BloodPressureType.Yes_Know_BP;
        user.conditionType=new ArrayList<>(Arrays.asList("Hepatitis C", "Thyroid disease",
                "Epilepsy", "Bipolar disorder", "HIV","High cholesterol"));

        return user;
    }
}
