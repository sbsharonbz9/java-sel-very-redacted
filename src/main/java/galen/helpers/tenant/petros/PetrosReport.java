package galen.helpers.tenant.petros;

import galen.helpers.common.GalenReport;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PetrosReport extends GalenReport {
    public PetrosReport(WebDriver driver, String reportName, String objective, String requirements,
                        String references, String notes, ArrayList<String> versionEntries,
                        HashMap<String, String[]> preexec) throws IOException {
        super(driver, reportName, objective, requirements, references, notes, versionEntries, preexec);
    }
}
