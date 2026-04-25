package Report;

import com.microsoft.playwright.Page;

public class ScreenshotUtil {
    public static String captureScreenshot(Page page, String fileName) {
        String path = "screenshots/" + fileName + ".png";
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(path)));
        return path;
    }
}
