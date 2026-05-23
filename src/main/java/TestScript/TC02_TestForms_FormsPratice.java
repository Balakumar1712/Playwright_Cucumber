package TestScript;

import CommonFunction.Dashboard;
import FormsFunction.PraticeForm;
import InitPlaywright.BrowserLaunch;
import Report.ReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;

public class TC02_TestForms_FormsPratice extends BrowserLaunch {

    @Test
    public void TestFormPraticeFlow(){

        ExtentReports extent = ReportManager.getInstance();
        ExtentTest test = extent.createTest(TC02_TestForms_FormsPratice.class.getSimpleName());
        Dashboard dashboard = new Dashboard(page);
        PraticeForm praticeForm = new PraticeForm(page);

        dashboard.Forms();
        praticeForm.Form();

        try{
            test.pass("Text Box executed successfully");}
        catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
        }
        extent.flush();
    }
}
