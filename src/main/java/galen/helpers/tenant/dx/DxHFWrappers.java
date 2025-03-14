package galen.helpers.tenant.dx;

import galen.helpers.common.GalenReport;
import galen.pages.common.BasePage;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;

public class DxHFWrappers extends DxNavigations {

    public DxHFWrappers(WebDriver driver) {
        super(driver);
    }

    public void executeHappyFlow(DxUser user, BasePage endPage, String HFType, @Nullable GalenReport report) {
        boolean result;
        try {
            result = endPage.getClass() == new DxNavigations(driver).partialNavigationIA(user, endPage,
                    null).getClass();
        } catch (Exception e ) {
            result=false;
        }
        basicHelpers.reportHappyFlow(HFType, endPage.reportText, result, report);
    }

    public void runDxHFNonsmokingwBP(DxUser user, BasePage endPage, @Nullable GalenReport report) {
        String HFType = "HappyFlow_IA_Initial_Assessment_to_Checkout\n_wBP_NonSmoker.docx";
        executeHappyFlow(user, endPage, HFType, report);
    }

    public void runDxHFSmokingwBP(DxUser user, BasePage endPage, @Nullable GalenReport report)  {
        String HFType = "HappyFlow_IA_Initial_Assessment_to_Checkout\n_wBP_Smoker.docx";
        executeHappyFlow(user, endPage, HFType, report);
    }

    public void runDxHFnoBPNonsmoker(DxUser user, BasePage endPage, @Nullable GalenReport report) {
        String HFType = "HappyFlow_IA_Initial_Assessment_to_Checkout\n_noBP_NonSmoker.docx";
        executeHappyFlow(user, endPage, HFType, report);
    }

    public void runDxHFADBUwBP(DxUser user, BasePage endPage, @Nullable GalenReport report)  {
        String HFType = "HappyFlow_IA_Initial_Assessment_ADBU_wBP.docx";
        executeHappyFlow(user, endPage, HFType, report);
    }

    public void runDxHFADBUNoBP(DxUser user, BasePage endPage, @Nullable GalenReport report)  {
        String HFType = "HappyFlow_IA_Initial_Assessment_ADBU_noBP.docx";
        executeHappyFlow(user, endPage, HFType, report);
    }

    public void runAntifungalToADBU(DxUser user, BasePage startPage, BasePage endPage, @Nullable GalenReport report)  {
        String HFType = "HappyFlow_IA_Initial_Assessment_to_Checkout\n_wBP_NonSmoker.docx";
        boolean result = new DxNavigations(driver).antiFungalToADBU(user, endPage, null).
                getClass().equals(endPage.getClass());
        basicHelpers.reportContinueHappyFlow(HFType, startPage.reportText, endPage.reportText, result, report);
    }
}
