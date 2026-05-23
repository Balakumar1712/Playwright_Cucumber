package FormsFunction;

import CommonFunction.FileChooserHelper;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.List;

import static CommonFunction.ScreenshotUtil.captureScreenshot;
import static CommonFunction.FileChooserHelper.openFileChooser;

public class PraticeForm {

    private Page page;

    private Locator praticeForm;
    private Locator praticeFormTitle;
    private Locator firstName;
    private Locator lastName;
    private Locator email;
    private Locator mobilenumber;
    private Locator DOB;
    private Locator subject;
    private Locator picture;
    private Locator currentAddress;
    private Locator stateAndCity;
    private Locator submit;

    public PraticeForm(Page page){
        this.page = page;
        this.praticeForm = page.locator("//span[text()= 'Practice Form']");
        this.praticeFormTitle = page.locator("//h1[text()='Practice Form']");
        this.firstName = page.locator("//input[@id='firstName']");
        this.lastName = page.getByPlaceholder("Last Name");
        this.email = page.getByPlaceholder("name@example.com");
        this.mobilenumber = page.getByPlaceholder("Mobile Number");
        this.DOB = page.locator("#dateOfBirthInput");
        this.subject = page.locator("#subjectsInput");
        this.picture = page.getByLabel("Select picture");
        this.currentAddress = page.getByPlaceholder("Current Address");
        this.stateAndCity = page.locator("//div[@id='react-select-6-placeholder']");
        this.submit = page.locator("#submit");
    }

    public void Form(){
        String userProvidedFilePath = null; // User can set this to a file path for automated selection

        Form(userProvidedFilePath);
    }

    /**
     * Overloaded Form method that allows both manual file selection (dialog) and automated file path input.
     * @param userProvidedFilePath The file path to use. If null/empty, a file chooser dialog will be opened.
     */
    public void Form(String userProvidedFilePath){

        int mobileNumberInput = 1234567890;
        String gender = "Male";
        String month = "December";
        String year = "2000";
        String date = "17";
        String hobby = "Music";

        praticeForm.click();
        captureScreenshot(page, "Pratice Form");
        firstName.fill("xxxx");
        lastName.fill("yyy");
        email.fill("bala@gmail.com");

        String mobileStr = String.valueOf(mobileNumberInput);
        if (mobileStr.matches("\\d{10}")) {
            mobilenumber.fill(mobileStr);
            System.out.println("Mobile number entered successfully: " + mobileStr);
        } else {
            throw new IllegalArgumentException("Invalid mobile number. Must be 10 digits only.");
        }

        page.locator("//label[text()='"+ gender +"']").click();
        captureScreenshot(page, "Mid of Pratice Form");

        DOB.click();
        Locator loc = page.getByRole(AriaRole.DIALOG);
        loc.waitFor(new com.microsoft.playwright.Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Locator monthDropdown = page.locator("//select[@class = 'react-datepicker__month-select']");
        monthDropdown.selectOption(new SelectOption().setLabel(month));
        Locator yearDropdown = page.locator("//select[@class = 'react-datepicker__year-select']");
        yearDropdown.selectOption(new SelectOption().setLabel(year));
        Locator dateloc = page.locator("xpath=//div[text()='"+ date +"']").nth(0);
        dateloc.click();

        subject.fill("english");
        subject.press("Enter");

        Locator hobbies = page.getByLabel(hobby);
        hobbies.check();
        assert hobbies.isChecked();

        // File Selection: Two options available
        // Option 1: User provides a file path -> validated and canonical path returned
        // Option 2: User provides null -> file chooser dialog opens
        String canonicalFilePath = openFileChooser(userProvidedFilePath);
        
        if (canonicalFilePath != null) {
            System.out.println("File selected with canonical path: " + canonicalFilePath);
        } else {
            System.out.println("No valid file was selected.");
        }

    }

}
