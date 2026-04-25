package ElementFunction;

import Report.ScreenshotUtil;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextBoxFunc {

    private Page page;

    // Textbox locators (initialized in constructor)
    private Locator textBox;
    private Locator fullNameInput;
    private Locator emailInput;
    private Locator currentAddressInput;
    private Locator permanentAddressInput;
    private Locator submitButton;
    private Locator textBoxresult;

    public TextBoxFunc(Page page){
        this.page = page;
        this.textBox = page.locator("//span[contains(text(), 'Text Box')]");
        this.fullNameInput = page.getByPlaceholder("Full Name");
        this.emailInput = page.getByPlaceholder("name@example.com");
        this.currentAddressInput = page.getByPlaceholder("Current Address");
        this.permanentAddressInput = page.locator("//textarea[@id='permanentAddress']");
        this.submitButton = page.locator("#submit");
        this.textBoxresult = page.locator("#output div");
    }

    public Map<String, String> TextBox(String fullName, String email, String currentAddress, String permanentAddress){
        textBox.click();
        fullNameInput.fill(fullName);
        emailInput.fill(email);//"bala@example.com"
        currentAddressInput.fill(currentAddress);//"Chennai, India"
        permanentAddressInput.fill(permanentAddress);//"Permanent Address Example"
        String screenshotPath = ScreenshotUtil.captureScreenshot(page, "TextBox");
        submitButton.click();

        List<String> ids = List.of("name","email","currentAddress","permanentAddress");
        Map<String, String> outs = new HashMap<>();
        for (String id : ids) {
            Locator loc = page.locator("//p[@id='"+ id +"']");
            loc.waitFor(new com.microsoft.playwright.Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            String fullText = loc.innerText();
            String[] parts = fullText.split(":",2);
            String value = parts.length > 1 ? parts[1].trim() : fullText.trim();
            outs.put(id, value);
            System.out.println(id + ": " + value);
        }
        return outs;
    }


}
