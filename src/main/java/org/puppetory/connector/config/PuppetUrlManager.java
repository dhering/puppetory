package org.puppetory.connector.config;

public class PuppetUrlManager {

    private String serverUrl;
    private String serverVersion;
    private ConfigManager configManager;

    public PuppetUrlManager(ConfigManager configManager) {
        this.configManager = configManager;

        this.serverUrl = configManager.getConfig(ConfigManager.KEYS.PUPPET_SERVER_URL);
        this.serverVersion = configManager.getConfig(ConfigManager.KEYS.PUPPET_API_VERSION);
    }

    public String getQueryBaseUrl() {
        return serverUrl + serverVersion + "/query/";
    }
}