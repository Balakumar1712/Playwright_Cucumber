package InitPlaywright;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser;

public class BrowserLaunch {
    public static void main (String[] args){

        Playwright playwright = Playwright.create();
        //interface
        Browser browser =  playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("https://demoqa.com");
    }
}
