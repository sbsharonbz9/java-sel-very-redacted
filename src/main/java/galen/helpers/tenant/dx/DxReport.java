package galen.helpers.tenant.dx;

import galen.helpers.common.GalenReport;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.ArrayList;

public class DxReport extends GalenReport {
    public DxReport(WebDriver driver, String reportName, String objective, String requirements, String references, String notes, ArrayList<String> versionEntries) throws IOException {
        super(driver, reportName, objective, requirements, references, notes, versionEntries);
    }
}
