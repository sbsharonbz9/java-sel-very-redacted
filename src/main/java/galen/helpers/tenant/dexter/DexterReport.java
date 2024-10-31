package galen.helpers.tenant.dexter;

import galen.helpers.common.GalenReport;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.ArrayList;

public class DexterReport extends GalenReport {
    public DexterReport(WebDriver driver, String reportName, String objective, String requirements, String references, String notes, ArrayList<String> versionEntries) throws IOException {
        super(driver, reportName, objective, requirements, references, notes, versionEntries);
    }
}
