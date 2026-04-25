package TestScript;

import CommonFunction.Dashboard;
import ElementFunction.TextBoxFunc;
import InitPlaywright.BrowserLaunch;
import Report.ReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.Map;

public class TC01_TestElement_TextBox extends BrowserLaunch {

    @Test
    public void TextBoxTestFlow(){

        String fullName = "Bala Kumar";
        String email = "bala@example.com";
        String currentAddress = "Chennai, India";
        String permanentAddress = "Permanent Address Example";

        ExtentReports extent = ReportManager.getInstance();
        ExtentTest test = extent.createTest(TC01_TestElement_TextBox.class.getSimpleName());
        Dashboard dashboard = new Dashboard(page);
        TextBoxFunc elementFunc = new TextBoxFunc(page);

        dashboard.Elements();
        Map<String, String> outs = elementFunc.TextBox(fullName, email, currentAddress, permanentAddress);

        Assert.assertEquals(outs.get("name"), fullName, "Full Name mismatch!");
        Assert.assertEquals(outs.get("email"), email, "Email mismatch!");
        Assert.assertEquals(outs.get("currentAddress"), currentAddress, "Current Address mismatch!");
        Assert.assertEquals(outs.get("permanentAddress"), permanentAddress, "Permanent Address mismatch!");
        try{
        test.pass("Text Box executed successfully");}
         catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
        }
        extent.flush();
    }
}
