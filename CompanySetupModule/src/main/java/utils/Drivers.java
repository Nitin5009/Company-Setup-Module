package utils;

//import com.browserstack.local.Local;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Drivers {

    public WebDriver driver;
    public static final String path= System.getProperty("user.dir") + "\\src\\main\\resources\\downloadedFiles\\";
    public WebDriver getWebDriver(String browser) throws MalformedURLException {

        if (browser.equals("firefox")) {

            String downloadFilepath = System.getProperty("user.dir") + "\\src\\main\\resources\\downloadedFiles\\";
            String firefoxDownloadDir = System.getProperty("user.dir") + "\\geckodriver.exe";
            FirefoxOptions options = new FirefoxOptions();
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.folderList", 2); //Use for the default download directory the last folder specified for a download
            profile.setPreference("browser.download.dir", downloadFilepath); //Set the last directory used for saving a file from the "What should (browser) do with this file?" dialog.
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf, application/octet-stream, application/x-winzip, application/x-pdf, application/x-gzip, application/zip"); //list of MIME types to save to disk without asking what to use to open the file
            profile.setPreference("browser.helperApps.neverAsk.openFile", "application/pdf, application/octet-stream, application/x-winzip, application/x-pdf, application/x-gzip, application/zip");
            options.setProfile(profile);
            System.setProperty("webdriver.gecko.driver", firefoxDownloadDir);

            driver = new FirefoxDriver(options);
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        } else if (browser.equals("chrome")) {

            String downloadFilepath = System.getProperty("user.dir") + "\\src\\main\\resources\\downloadedFiles\\";
            String chromeDownloadDir = System.getProperty("user.dir") + "\\chromedriver.exe";

            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFilepath);
            chromePrefs.put("download.prompt_for_download", false);
            chromePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);


            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--disable-extensions");


            System.setProperty("webdriver.chrome.driver", chromeDownloadDir);
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        }

        return driver;
    }


}
