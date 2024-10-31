package galen.pages.tenant.dexter.InitialAssessment;

import galen.pages.common.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class AntifungalMeds extends CheckboxPage {

    public By afHeader = By.className("AntiBacterialProductListScreen");
    public ArrayList<String> antifungalMedsOptions = new ArrayList<>(Arrays.asList(
            "Fluconazole",// * Antifungal • Often called Diflucan®",
            "Griseofulvin", //* Antifungal • Often called Grifulvin V®",
            "Griseofulvin Ultramicrocrystalline", //* Antifungal • Often called Gris-PEG®",
            "Itraconazole", //* Antifungal • Often called Sporanox®",
            "Ketoconazole", //* Antifungal • Often called Nizoral®",
            "Voriconazole", //* Antifungal • Often called Vfend®",
            "None of these"));
    public ArrayList<String> allButNone = new ArrayList<>(Arrays.asList(
            "Fluconazole",// * Antifungal • Often called Diflucan®",
            "Griseofulvin", //* Antifungal • Often called Grifulvin V®",
            "Griseofulvin Ultramicrocrystalline", //* Antifungal • Often called Gris-PEG®",
            "Itraconazole", //* Antifungal • Often called Sporanox®",
            "Ketoconazole", //* Antifungal • Often called Nizoral®",
            "Voriconazole")); //* Antifungal • Often called Vfend®");

    public AntifungalMeds(WebDriver driver) {
        super(driver);
        headingTitle=afHeader;
        titleText="Are you currently taking any of these antifungal medications?";
        reportText="Antifungal Meds Screen";
        options=antifungalMedsOptions;
    }

}