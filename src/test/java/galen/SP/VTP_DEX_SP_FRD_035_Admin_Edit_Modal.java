package galen.SP;

import galen.base.BaseTest;
import galen.enums.SP.RoleType;
import galen.enums.framework.UrlType;
import galen.helpers.common.BasicHelpers;
import galen.helpers.common.CommonPageFeatures;
import galen.helpers.common.GalenReport;
import galen.helpers.sp.ParticipantClass;
import galen.pages.sp.Participants;
import galen.pages.sp.StudyAdminPageObj;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class VTP_DEX_SP_FRD_035_Admin_Edit_Modal extends BaseTest {

    public static final String OBJECTIVE = "To verify if an IE Admin user selects the “Edit” link in the Action column of the Participants screen, the portal shall display a popup screen that allows the user to: \n" +
            "-\tUpdate user email address \n" +
            "-\tUpdate study ID for the user \n" +
            "-\tUpdate study site for the user \n" +
            "-\tResend invitation email link \n" +
            "-\tDisable or re-enable the participant account (once account has been setup) \n" +
            "-\tSave any changes\n";
    public static final String NOTES ="This protocol contains the following scenarios:\n" +
            "-\tEdit Participant Modal is displayed with the following:\n" +
            "o\tEmail Address field\n" +
            "o\tStudy ID field\n" +
            "o\tStudy Site field\n" +
            "o\tResend Invitation link\n" +
            "o\tAccount Status toggle\n" +
            "o\tSave Changes button\n" +
            "o\tCancel button\n" +
            "-\tAbility to edit/update participant’s email address (if they have not yet claimed token and created an account)\n" +
            "-\tAbility to resend invitation link\n" +
            "-\tAbility to toggle Account Status\n";
    static String REQUIREMENTS = "DEX_SP_FRD_035";
    static String REFERENCES = "N/A";
    StudyAdminPageObj pageObj;
    String reportName = "VTP_DEX_SP_FRD_035_Admin_Edit_Modal";
    ArrayList<String> VERSIONHISTORY = new ArrayList<>();
    HashMap<String, String[]> PREEXECUTION = new HashMap<>();
    public BasicHelpers bh;
    public CommonPageFeatures commonPageFeatures;

    VTP_DEX_SP_FRD_035_Admin_Edit_Modal() {
        VERSIONHISTORY.add(" ; ; ; ");
    }

    @Test
    public void VTP_DEX_SP_FRD_035_Admin_Edit_Modal_Test() throws IOException {
        report = new GalenReport(driver, reportName, OBJECTIVE, REQUIREMENTS, REFERENCES, NOTES, VERSIONHISTORY, PREEXECUTION);
        report.reportTitle = "VTP_DEX_SP_FRD_035 – Admin Edit Participant Modal";
        bh = new BasicHelpers(driver);
        pageObj = new StudyAdminPageObj(driver);
        Participants par = pageObj.participants;
        pageObj.pritUnl.authenticateUserIfRequired(UrlType.STUDY);
        pageObj.login.logIn(RoleType.ADMIN.email, report);
        par.clickEditPart(report);
        par.verifyModalDisplayed(report);
        par.verifyAllEditModalElementsDisplayed(report);
        report.addScreenshotStep("Step3_Modal Is Displayed");

        ParticipantClass parUser = new ParticipantClass(bh.getUniqueEmail(), "001",
                "01");
        par.editParticipant(parUser, report);
        par.findEmailInTable(parUser.email, report);
        report.addScreenshotStep("Step4_New email " + parUser.email + " in table");

        par.clickEditPart(report);
        par.verifyModalDisplayed(report);
        bh.clickFlex(par.activeToggle, "'Deactivate' toggle",report);
        bh.verifyDisplayedFlex(par.inactiveToggle, "Indication toggle deactivated", report);
        report.addScreenshotStep("Step6_New user deactivated");

        par.clickSaveChangesButton(report);
        par.verifyDisplayedRedDotStatus(parUser.email, report);
        report.addScreenshotStep("Step7_Red dot");

        par.clickEditPart(report);
        par.verifyModalDisplayed(report);
        bh.clickFlex(par.activeToggle, "'Reactivate' toggle",report);
        bh.verifyDisplayedFlex(par.activeToggle, "Indication toggle reactivated", report);
        report.addScreenshotStep("Step9_New user reactivated");

        par.clickSaveChangesButton(report);
        par.verifyNotDisplayedRedDotStatus(parUser.email, report);
        report.addScreenshotStep("Step10_Red dot");
    }
}
