package galen.pages.sp;

import galen.pages.common.GalenPageObj;
import org.openqa.selenium.WebDriver;

public class StudyAdminPageObj extends GalenPageObj {
    public Login login;
    public Participants participants;
    public DownloadRecords downloadRecords;
    public ViewRecords viewRecords;
    public MyPassword myPassword;
    public Studies study;
    public Sites sites;
    public Accounts accounts;

    public StudyAdminPageObj(WebDriver driver) {
        super(driver);
        login = new Login(driver);
        participants = new Participants(driver);
        downloadRecords = new DownloadRecords(driver);
        viewRecords = new ViewRecords(driver);
        myPassword = new MyPassword(driver);
        sites = new Sites(driver);
        study = new Studies(driver);
        accounts = new Accounts(driver);
    }
}
