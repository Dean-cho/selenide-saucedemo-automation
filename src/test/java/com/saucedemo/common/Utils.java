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
        Configuration.timeout = 10000;

    }

    private static void setBrowserOptions(final String browser) {
        Configuration.browser = browser;

        if ("chrome".equalsIgnoreCase(browser)) {
            ChromeOptions chromeOptions = new ChromeOptions().addArguments("--lang=ko");

            // 비밀번호 팝업 방지
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            chromeOptions.setExperimentalOption("prefs", prefs);

            // 로컬 전용 설정
            if (!isCIEnvironment()) {
                String userDataDir = System.getProperty("user.dir") + "/chrome-profile";
                chromeOptions.addArguments("--user-data-dir=" + userDataDir);
                chromeOptions.addArguments("--start-maximized"); // 브라우저 창 표시
            } else {
                chromeOptions.addArguments("--headless"); // CI 환경용
            }

            // 시크릿 모드 실행
            chromeOptions.addArguments("--incognito");

            // 자동 완성 관련 기능 및 비밀번호 유출 감지 기능 비활성화
            chromeOptions.addArguments("--disable-features=PasswordLeakDetection,AutofillServerCommunication,AutofillEnableAccountWalletStorage,AutofillCreditCardUpload");

            // Docker나 Linux 환경에서 /dev/shm 공유 메모리 문제 방지
            chromeOptions.addArguments("--disable-dev-shm-usage");

            // GPU 하드웨어 가속 기능 비활성화 (headless 환경에서 주로 사용)
            chromeOptions.addArguments("--disable-gpu");

            // 콘솔에 자세한 로그를 출력
            chromeOptions.addArguments("--verbose");

            // 보안 샌드박스를 비활성화 (Docker 등 제한된 환경에서 필수)
            chromeOptions.addArguments("--no-sandbox");

            // 확장 프로그램 비활성화 (테스트 안정성 확보)
            chromeOptions.addArguments("--disable-extensions");

            // 브라우저 알림 차단 (웹 푸시 알림 방지)
            chromeOptions.addArguments("--disable-notifications");

            // 팝업 차단 기능 비활성화 (테스트 중 팝업 확인을 위해)
            chromeOptions.addArguments("--disable-popup-blocking");

            Configuration.browserCapabilities = chromeOptions;
        }
    }


    private static boolean isCIEnvironment() {
        // CI 환경에서는 환경 변수로 구분 (GitHub Actions)
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
