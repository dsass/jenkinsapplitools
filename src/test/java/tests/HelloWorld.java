package tests;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

/**
 * Hello world!
 *
 */
public class HelloWorld
{
    @Test
    public void helloTest() throws Exception {

        // Open a Chrome browser.
        WebDriver driver = new ChromeDriver();

        // Initialize the eyes SDK and set your private API key.
        Eyes eyes = new Eyes();
        eyes.setApiKey(System.getenv("APPLITOOLS_ACCESS_KEY"));



        try{
//            eyes.setMatchTimeout(1000);
            //Set only once per Jenkins job
            BatchInfo mybatch = new BatchInfo(System.getenv("APPLITOOLS_BATCH_NAME"));
            String id = System.getenv("APPLITOOLS_BATCH_ID");
            if (id != null) {
                mybatch.setId(id);
            } else {
                throw new Exception("ID IS NULL");
            }
            //End of - Set only once per Jenkins job
            eyes.setBatch(mybatch);
            // Start the test and set the browser's viewport size to 800x600.
            eyes.open(driver, "Dynamic Loading", "Testing Match Timeout2",
                    new RectangleSize(800, 600));

            // Navigate the browser to the "hello world!" web-site.
            driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");

            // Visual checkpoint #1.
            eyes.checkWindow("Open");

//            eyes.checkRegion(By.id("content"), "content");

            // Click the "Click me!" button.
            driver.findElement(By.cssSelector("#start button")).click();


            eyes.checkWindow(0,"Loading Now");
            // Visual checkpoint #2.
            //Thread.sleep(5000);
            //eyes.checkWindow(100,"Loaded");

            //eyes.checkWindow("new one");

            // End the test.
            eyes.close();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            // Close the browser.
            driver.quit();

            // If the test was aborted before eyes.close was called, ends the test as aborted.
            eyes.abortIfNotClosed();
        }
    }
}
