package galen.helpers.tenant.petros;

import galen.enums.common.Gender;
import galen.helpers.common.GalenUser;

public class PetrosUserTemplates {

    public PetrosUser make_IA_Checkout_w_GuestUser() {
        PetrosUser user = new PetrosUser();
        user.gender = Gender.Male;
        user.dobDay = "01";
        user.dobMonth = "01";
        user.dobYear = "1995";
        return user;
    }
}
