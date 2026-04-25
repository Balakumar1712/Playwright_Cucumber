package CommonFunction;

import Report.ScreenshotUtil;
import com.microsoft.playwright.Page;

import static Report.ScreenshotUtil.captureScreenshot;

public class Dashboard {

    private Page page;

    //Constructor
    public Dashboard(Page page){
        this.page = page;
    }

    //Locators
    private String Elements = "//h5[contains(text(),'Elements')]";
    private String Forms = "//h5[contains(text(),'Forms')]";
    private String AlertWindowFrame = "//h5[contains(text(),'Alerts, Frame & Windows')]";
    private String Widgets = "//h5[contains(text(),'Widgets')]";
    private String Interaction = "//h5[contains(text(),'Interaction')]";

    public void Elements(){
        captureScreenshot(page, "DashBoard");
        page.click(Elements);
        captureScreenshot(page, "Elements");
    }

    public void Forms(){
        captureScreenshot(page, "DashBoard");
        page.click(Forms);
         captureScreenshot(page, "Forms");
    }
    public void AlertWindowFrame(){
        captureScreenshot(page, "DashBoard");
        page.click(AlertWindowFrame);
        captureScreenshot(page, "AlertWindowFrame");
    }
    public void Widgets(){
        captureScreenshot(page, "DashBoard");
        page.click(Widgets);
        captureScreenshot(page, "Widgets");
    }

    public void Interaction(){
        captureScreenshot(page, "DashBoard");
        page.click(Interaction);
        captureScreenshot(page, "Interaction");
    }
}
