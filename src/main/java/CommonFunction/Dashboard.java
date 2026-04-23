package CommonFunction;

import com.microsoft.playwright.Page;

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
        page.click(Elements);
    }

    public void Forms(){
        page.click(Forms);
    }
    public void AlertWindowFrame(){
        page.click(AlertWindowFrame);
    }
    public void Widgets(){
        page.click(Widgets);
    }

    public void Interaction(){
        page.click(Interaction);
    }
}
