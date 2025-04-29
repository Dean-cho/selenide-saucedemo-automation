package com.saucedemo.common;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;


public class Utils {
    public static void setTestConfig() {
        String browser = System.getenv("browser") == null ? "chrome" : System.getenv("browser");
        System.out.println(browser);
        setBrowserOptions(browser);
        Configuration.timeout = PropertyLoader.getConfig().getTimeout();
        Configuration.browserSize = "1920x1080";
        Configuration.browserPosition = "0x0";
        Configuration.pageLoadTimeout = PropertyLoader.getConfig().getTimeout();
        Configuration.pageLoadStrategy = "normal";

    }

    private static void setBrowserOptions(final String browser) {
        Configuration.browser = browser;

        if ("chrome".equalsIgnoreCase(browser)) {
            ChromeOptions chromeOptions = new ChromeOptions().addArguments("--lang=ko");

            // 로컬 환경 설정
            if (!isCIEnvironment()) {
                // 비밀번호 팝업 방지 설정
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                chromeOptions.setExperimentalOption("prefs", prefs);

                // user-data-dir 설정 (로컬만)
                String userDataDir = System.getProperty("user.dir") + "/chrome-profile";
                chromeOptions.addArguments("--user-data-dir=" + userDataDir);
            }

            // 공통 옵션
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("--no-first-run");
            chromeOptions.addArguments("--no-default-browser-check");

            chromeOptions.addArguments("--disable-features=PasswordLeakDetection,AutofillServerCommunication,AutofillEnableAccountWalletStorage,AutofillCreditCardUpload");
            chromeOptions.addArguments("--password-store=basic");

            chromeOptions.addArguments("--headless=new");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--whitelisted-ips");
            chromeOptions.addArguments("--verbose");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--disable-popup-blocking");

            Configuration.browserCapabilities = chromeOptions;
        }
    }


    private static boolean isCIEnvironment() {
        // CI 환경에서는 환경 변수로 구분 (예: GitHub Actions)
        return System.getenv("CI") != null;
    }

    public static String getUrl() {
        return WebDriverRunner.url();
    }

    public static boolean urlContains(String url) {
        return getUrl().contains(url);
    }

    public static CharSequence paste() {
        final boolean isMac = System.getProperty("os.name").toLowerCase().contains("mac");
        return isMac ? Keys.COMMAND + "v" : Keys.LEFT_CONTROL + "v";
    }

    public static SelenideElement waitFor(final SelenideElement element) {
        return waitFor(element, 10000, 100);
    }

    public static SelenideElement waitFor(final SelenideElement element, final long timeout) {
        return waitFor(element, timeout, 100);
    }

    public static SelenideElement waitFor(final SelenideElement element, final long timeout, final long sleepAmount) {
        final long count = (timeout / sleepAmount) + 1;
        boolean exists = false;
        for (int i = 0; i < count; ++i) {
            if (element.exists()) {
                exists = true;
                break;
            }
            try {
                Thread.sleep(sleepAmount);
            } catch (Exception e) {
            }
        }
        if (!exists) {
            throw new RuntimeException(element + " not exist");
        }
        return element;
    }

    public static void waitFor(final Callable<Boolean> func) {
        waitFor(func, 10000, 100);
    }

    public static void waitFor(final Callable<Boolean> func, final long timeout) {
        waitFor(func, timeout, 100);
    }

    public static void waitFor(final Callable<Boolean> func, final long timeout, final long sleepAmount) {
        final long count = (timeout / sleepAmount) + 1;
        for (int i = 0; i < count; ++i) {
            try {
                final Boolean status = func.call();
                if (Boolean.TRUE.equals(status)) {
                    return;
                }
                Thread.sleep(sleepAmount);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void sleep(final long sleepAmount) {
        try {
            Thread.sleep(sleepAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
