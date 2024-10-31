package galen.helpers.tenant.dexter;

import galen.enums.tenant.dexter.BloodPressureType;
import galen.enums.tenant.dexter.CancerType;
import galen.enums.tenant.dexter.DDIConditionType;
import galen.enums.tenant.dexter.SmokeType;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;

public class DexterUserTemplates {
    public DexterUser createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker() {
        DexterUser user = new DexterUser();
        user.menstrualPeriod = "Yes";
        user.birthControl = "No";
        user.smokingType = SmokeType.DO_NOT_SMOKE;
        return user;
    }

    public DexterUser createHappyFlow_IA_Initial_Assessment_to_Checkout_wBP_Smoker() {
        DexterUser user = new DexterUser();
        user.smokingType = SmokeType.SMOKE_REGULARLY;
        user.dobYear = String.valueOf((Year.now().getValue() - 20));
        user.hadBirthday = "Yes";
        user.menstrualPeriod = "Yes";
        user.birthControl = "No";
        user.depression = "No";
        return user;
    }

    public DexterUser createHappyFlow_IA_Initial_Assessment_to_Checkout_noBP_NonSmoker() {
        DexterUser user = new DexterUser();
        user.knowBPType = BloodPressureType.No_DO_NOT_Know_BP;
        return user;
    }

    public DexterUser createHappyFlow_IA_Initial_Assessment_ADBU_noBP() {
        DexterUser user = createHappyFlow_IA_Initial_Assessment_ADBU_wBP();
        user.knowBPType= BloodPressureType.No_DO_NOT_Know_BP;
        return user;
    }

    public DexterUser createHappyFlow_IA_Initial_Assessment_ADBU_wBP() {
        DexterUser user = new DexterUser();
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
