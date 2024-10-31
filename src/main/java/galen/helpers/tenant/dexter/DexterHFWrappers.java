package galen.helpers.tenant.dexter;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import galen.pages.tenant.dexter.InitialAssessment.Antifungal;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;
import java.io.IOException;

public class DexterHFWrappers extends DexterNavigations {

    public DexterHFWrappers(WebDriver driver) {
        super(driver);
    }

    public void executeHappyFlow(DexterUser user, BasePage endPage, String HFType, @Nullable GalenReport report) {
        boolean result;
        try {
            result = endPage.getClass() == new DexterNavigations(driver).partialNavigationIA(user, endPage,
                    null).getClass();
        } catch (Exception e ) {
            result=false;
        }
        basicHelpers.reportHappyFlow(HFType, endPage.reportText, result, report);
    }

    public void runDexterHFNonsmokingwBP(DexterUser user, BasePage endPage, @Nullable GalenReport report) {
        String HFType = "HappyFlow_IA_Initial_Assessment_to_Checkout\n_wBP_NonSmoker.docx";
        executeHappyFlow(user, endPage, HFType, report);
    }

    public void runDexterHFSmokingwBP(DexterUser user, BasePage endPage, @Nullable GalenReport report)  {
        String HFType = "HappyFlow_IA_Initial_Assessment_to_Checkout\n_wBP_Smoker.docx";
        executeHappyFlow(user, endPage, HFType, report);
    }

    public void runDexterHFnoBPNonsmoker(DexterUser user, BasePage endPage, @Nullable GalenReport report) {
        String HFType = "HappyFlow_IA_Initial_Assessment_to_Checkout\n_noBP_NonSmoker.docx";
        executeHappyFlow(user, endPage, HFType, report);
    }

    public void runDexterHFADBUwBP(DexterUser user, BasePage endPage, @Nullable GalenReport report)  {
        String HFType = "HappyFlow_IA_Initial_Assessment_ADBU_wBP.docx";
        executeHappyFlow(user, endPage, HFType, report);
    }

    public void runDexterHFADBUNoBP(DexterUser user, BasePage endPage, @Nullable GalenReport report)  {
        String HFType = "HappyFlow_IA_Initial_Assessment_ADBU_noBP.docx";
        executeHappyFlow(user, endPage, HFType, report);
    }

    public void runAntifungalToADBU(DexterUser user, BasePage startPage, BasePage endPage, @Nullable GalenReport report)  {
        String HFType = "HappyFlow_IA_Initial_Assessment_to_Checkout\n_wBP_NonSmoker.docx";
        boolean result = new DexterNavigations(driver).antiFungalToADBU(user, endPage, null).
                getClass().equals(endPage.getClass());
        basicHelpers.reportContinueHappyFlow(HFType, startPage.reportText, endPage.reportText, result, report);
    }
}
