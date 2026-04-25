package InitPlaywright;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.io.InputStream;

public class BrowserLaunch {

    protected Browser browser;
    protected Playwright playwright;
    protected Page page;

    @BeforeMethod
    public  void Setup() throws IOException {
        TestData testData = loadTestData();

        playwright = Playwright.create();
        browser = createBrowser(playwright, testData);
        page = browser.newPage();
        page.navigate(testData.baseUrl);
    }

    @AfterMethod
    public void  TearDown(){
        if (page != null){
            page.close();
        }
        if(browser != null){
            browser.close();
        }
        if (playwright != null){
            playwright.close();
        }
    }

    private static TestData loadTestData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = BrowserLaunch.class.getClassLoader().getResourceAsStream("test-data.json")) {
            if (inputStream == null) {
                throw new IOException("test-data.json not found in resources");
            }
            return objectMapper.readValue(inputStream, TestData.class);
        }
    }

    private static Browser createBrowser(Playwright playwright, TestData testData) {
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(testData.headless);
        switch (testData.browser.toLowerCase()) {
            case "firefox":
                return playwright.firefox().launch(launchOptions);
            case "webkit":
                return playwright.webkit().launch(launchOptions);
            case "chromium":
            default:
                return playwright.chromium().launch(launchOptions);
        }
    }

    static class TestData {
        public String baseUrl;
        public String browser;
        public boolean headless;
        public TestUser testUser;
    }

    static class TestUser {
        public String firstName;
        public String lastName;
        public String email;
    }
}
