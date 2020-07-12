package Windows.WinAppDriver;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;

public class NotepadTest {

	private static WindowsDriver NotepadSession = null;
	private static String NotepadAppId = "C:/Windows/System32/notepad.exe";

	@BeforeClass
	public static void setup() throws IOException {

		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("app", NotepadAppId);
			capabilities.setCapability("platformName", "Windows");
			capabilities.setCapability("deviceName", "WindowsPC");
			NotepadSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
			NotepadSession.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			Assert.assertNotNull(NotepadSession.getSessionId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	

	@Test
	public static void Writing() throws InterruptedException {
		WebElement editAreaBox = NotepadSession.findElementByName("Text Editor");
		editAreaBox.sendKeys("Test");
		System.out.println(editAreaBox.getText());
		Assert.assertNotNull(editAreaBox);
		editAreaBox.clear();
		editAreaBox.sendKeys("Test Test");
		Assert.assertEquals("Test Test", editAreaBox.getText());
		WebElement closeButton = WaitForElementToBeVisible(NotepadSession, "name", "Close");
		closeButton.click();
		WebElement donotSaveButton = WaitForElementToBeVisible(NotepadSession, "id", "CommandButton_7");
		donotSaveButton.click();
	}

	@AfterClass
	public static void TearDown() throws InterruptedException {

		NotepadSession = null;
		if (NotepadSession != null) {
			NotepadSession.quit();
		}
		NotepadSession = null;

	}

	public static WebElement WaitForElementToBeVisible(WindowsDriver<WindowsElement> NotepadSession, String locatorType,
			String locatorValue) throws InterruptedException {

		WebElement element = null;

		for (int a = 0; a < 100; a++) {

			if (locatorType.contains("name")) {
				element = NotepadSession.findElementByName(locatorValue);
				element.isDisplayed();
				break;
			}
			if (locatorType.contains("id")) {
				element = NotepadSession.findElementByAccessibilityId(locatorValue);
				element.isDisplayed();
				break;
			}
			Thread.sleep(1000);
		}
		return element;
	}

}
