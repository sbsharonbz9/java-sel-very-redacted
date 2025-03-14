package galen.pages.tenant.dx.InitialAssessment;

import galen.enums.tenant.dx.DDIOtherMedsType;
import galen.helpers.common.GalenReport;
import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

public class OtherMedication extends CheckboxPage {
    public By title = By.className("OtherMedicationScreen");
    public ArrayList<String> medsOptions = new ArrayList<>(Arrays.asList(DDIOtherMedsType.RIFABUTIN.label,
            DDIOtherMedsType.RIFAMPIN.label, DDIOtherMedsType.PREDNISONE.label,DDIOtherMedsType.TEMAZEPAM.label,
            DDIOtherMedsType.ST_JOHNS_WORT.label,
           DDIOtherMedsType.NONE_OF_THESE.label));

    public OtherMedication(WebDriver driver) {
        super(driver);
        headingTitle = title;
        titleText = "Are you currently taking any of these medications?";
        reportText = "Other Medication Screen";
        options = medsOptions;
    }

    public boolean verifyAllOptionsInADBU(@Nullable GalenReport report) {
        options = new ArrayList<>(Arrays.asList("Rifabutin",
                "Rifampin", "Prednisone", "Temazepam", "St John's Wort"));
        return super.verifyAllOptionsInADBU(report);
    }

}
