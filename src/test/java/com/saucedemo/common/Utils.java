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

            // 비밀번호 팝업 방지 옵션
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);           // 크롬 Credential Service 비활성화
            prefs.put("profile.password_manager_enabled", false);     // 비밀번호 저장 관리자 비활성화
            chromeOptions.setExperimentalOption("prefs", prefs);

            // 고정된 사용자 프로필 디렉토리 (비밀번호 팝업 방지 설정 유지 목적)
            String userDataDir = System.getProperty("user.dir") + "/chrome-profile";
            chromeOptions.addArguments("--user-data-dir=" + userDataDir);

            // 비밀번호 유출 경고 차단 관련 옵션
            chromeOptions.addArguments("--disable-features=PasswordLeakDetection,AutofillServerCommunication,AutofillEnableAccountWalletStorage,AutofillCreditCardUpload");
            chromeOptions.addArguments("--password-store=basic");

            // 기타 브라우저 설정
//      chromeOptions.setHeadless(true);
            chromeOptions.addArguments("--whitelisted-ips");
            chromeOptions.addArguments("--verbose");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--disable-popup-blocking");

            Configuration.browserCapabilities = chromeOptions;
        }
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
