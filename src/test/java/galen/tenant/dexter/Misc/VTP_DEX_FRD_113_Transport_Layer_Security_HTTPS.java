package galen.tenant.dexter.Misc;

import galen.base.BaseTest;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.GalenReport;
import galen.pages.tenant.dexter.InitialAssessment.DexterPageObj;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class VTP_DEX_FRD_113_Transport_Layer_Security_HTTPS extends BaseTest {
    static String OBJECTIVE = "To verify the Dexter App shall provide encryption of the consumer-application interaction" +
            " using Transport Layer Security (TLS) for security and privacy.";
    static String NOTES = "This protocol contains the following verification scenarios:\n" +
            "o\tNavigating to Dexter ENV using URL not containing ‘S’ automatically updates to ‘HTTPS’\n" +
            "o\tConfirmation of trusted certificate in the browser";
    static String REQUIREMENTS = "DEX_FRD_113";
    static String REFERENCES = "HappyFlow_IA_Initial_Assessment_to_Checkout_wBP_NonSmoker.docx";
    String reportName = "VTP_DEX_FRD_113_Transport_Layer_Security_HTTPS";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();

    VTP_DEX_FRD_113_Transport_Layer_Security_HTTPS()  {
        VERSIONHISTORY.add("1.0;07MAY2023;Initial Test Script;Name Redacted");
    }

    @Test
    public void VTP_DEX_FRD_113_Transport_Layer_Security_HTTPS_Test() throws Exception {

        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY,
                PREEXECUTION);
        report.reportTitle = "VTP_DEX_FRD_113 – Transport Layer Security with HTTPS";
        BasicHelpers bh = new BasicHelpers(driver);
        DexterPageObj pageObj = new DexterPageObj(driver);
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.DEXTER);
        String httpsURL="https://cadence.qa.codescripts.com/?invite=7GZ6F66T_fKn1bhuLq5Ei_LT8NQ";
        sleep(1000);
        driver.navigate().to(httpsURL);
        sleep(1000);

        driver.navigate().to("http://cadence.qa.codescripts.com/?invite=7GZ6F66T_fKn1bhuLq5Ei_LT8NQ");
        sleep(1000);
        bh.verifyCondition(()-> httpsURL.equals(driver.getCurrentUrl()), "URL has been changed to https",
                false,report);
        // Driver configured to not accept invalid certs.Error page would appear
        bh.verifyCondition(()-> pageObj.welcomePage.verifyAtPage(), "Connection is secure and " +
                        "Certificate is valid", false,report);
    }
}
