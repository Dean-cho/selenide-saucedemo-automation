package com.saucedemo.common;

import java.util.Map;

public class Config {
    public long timeout;
    public String browser;
    public Map<String, String> urls;
    public Map<String, Account> accounts;

    public long getTimeout() {
        return timeout;
    }

    public String getBrowser() {
        return browser;
    }

    public Map<String, String> getUrls() {
        return urls;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public static class Account {
        public String id;
        public String pw;

        public String getId() {
            return id;
        }

        public String getPw() {
            return pw;
        }
    }
}
