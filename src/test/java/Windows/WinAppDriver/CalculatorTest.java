package Windows.WinAppDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.net.URL;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import io.appium.java_client.windows.WindowsDriver;

public class CalculatorTest {

	private static WindowsDriver CalculatorSession = null;
	private static WebElement CalculatorResult = null;

	@BeforeClass
	public static void setup() throws IOException {

		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
			capabilities.setCapability("platformName", "Windows");
			capabilities.setCapability("deviceName", "WindowsPC");
			CalculatorSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
			CalculatorResult = CalculatorSession.findElementByAccessibilityId("CalculatorResults");
			Assert.assertNotNull(CalculatorSession);
			Assert.assertNotNull(CalculatorSession.getSessionId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	@BeforeMethod
	public void Clear() {
		CalculatorSession.findElementByName("Clear").click();
	}

	@AfterClass
	public static void TearDown() {
		CalculatorResult = null;
		if (CalculatorSession != null) {
			CalculatorSession.quit();
		}
		CalculatorSession = null;
	}

	@Test(priority = 1)
	public void Addition() {
		CalculatorSession.findElementByName("One").click();
		CalculatorSession.findElementByName("Plus").click();
		CalculatorSession.findElementByName("Seven").click();
		CalculatorSession.findElementByName("Equals").click();
		System.out.println("Addition Results - " + GetResults());
		Assert.assertEquals("8", GetResults());
	}

	@Test(priority = 2)
	public void Combination() {
		CalculatorSession.findElementByName("Seven").click();
		CalculatorSession.findElementByName("Multiply by").click();
		CalculatorSession.findElementByName("Nine").click();
		CalculatorSession.findElementByName("Plus").click();
		CalculatorSession.findElementByName("One").click();
		CalculatorSession.findElementByName("Equals").click();
		CalculatorSession.findElementByName("Divide by").click();
		CalculatorSession.findElementByName("Eight").click();
		CalculatorSession.findElementByName("Equals").click();
		System.out.println("Combination Results - " + GetResults());
		Assert.assertEquals("8", GetResults());
	}

	@Test(priority = 3)
	public void Division() {
		CalculatorSession.findElementByName("Eight").click();
		CalculatorSession.findElementByName("Eight").click();
		CalculatorSession.findElementByName("Divide by").click();
		CalculatorSession.findElementByName("One").click();
		CalculatorSession.findElementByName("One").click();
		CalculatorSession.findElementByName("Equals").click();
		System.out.println("Division Result - " + GetResults());
		Assert.assertEquals("8", GetResults());
	}

	@Test(priority = 4)
	public void Multiplication() {
		CalculatorSession.findElementByName("Nine").click();
		CalculatorSession.findElementByName("Multiply by").click();
		CalculatorSession.findElementByName("Nine").click();
		CalculatorSession.findElementByName("Equals").click();
		System.out.println("Multiplication Result - " + GetResults());
		Assert.assertEquals("81", GetResults());
	}

	@Test(priority = 5)
	public void Subtraction() {
		CalculatorSession.findElementByName("Nine").click();
		CalculatorSession.findElementByName("Minus").click();
		CalculatorSession.findElementByName("One").click();
		CalculatorSession.findElementByName("Equals").click();
		System.out.println("Subtraction Result - " + GetResults());
		Assert.assertEquals("8", GetResults());
	}

	public String GetResults() {
		return CalculatorResult.getText().replace("Display is", "").trim();
	}

}
