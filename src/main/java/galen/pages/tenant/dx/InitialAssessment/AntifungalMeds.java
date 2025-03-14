package galen.pages.tenant.dx.InitialAssessment;

import galen.enums.tenant.dx.AntifungalMedsType;
import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class AntifungalMeds extends CheckboxPage {

    public By afHeader = By.className("AntiBacterialProductListScreen");
    public ArrayList<String> antifungalMedsOptions = new ArrayList<>(Arrays.asList(
            AntifungalMedsType.FLUCONAZOLE.label,
            AntifungalMedsType.GRISEOFULVIN.label,
            AntifungalMedsType.GRISEOFULVIN_ULTRAMICROCRYSTALLINE.label,
            AntifungalMedsType.ITRACONAZOLE.label,
            AntifungalMedsType.KETOCONAZOLE.label,
            AntifungalMedsType.VORICONAZOLE.label,
            AntifungalMedsType.NONE_OF_THESE.label));

    public AntifungalMeds(WebDriver driver) {
        super(driver);
        headingTitle=afHeader;
        titleText="Are you currently taking any of these antifungal medications?";
        reportText="Antifungal Meds Screen";
        options=antifungalMedsOptions;
    }

}