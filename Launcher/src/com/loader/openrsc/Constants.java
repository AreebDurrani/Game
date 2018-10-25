package com.loader.openrsc;

public class Constants {

    // Basic information
    public static final String GAME_NAME = "Open RSC";
    public static final String SERVER_DOMAIN = "game.openrsc.com"; // Only used for the server status display
    public static final int SERVER_PORT = 43594;

    // Cache
    public static final String base_url = "https://openrsc.com/"; // Cache and client jar download locations depend on this
    public static final String CONF_DIR = "Cache";
    public static final String CACHE_URL = base_url + "downloads/cache/";
    public static final String CLIENT_URL = CACHE_URL + "Open_RSC_Client.jar";

    // Launcher version checking
    public static final Double VERSION_NUMBER = 20181021.203000; //YYYYMMDD.HHMMSS format
    public static final String VERSION_UPDATE_URL = "https://raw.githubusercontent.com/open-rsc/Game/2.0.0/Launcher/src/com/loader/openrsc/Constants.java";
    public static final String UPDATE_JAR_URL = "https://openrsc.com/downloads/Open_RSC_Launcher.jar";
    public static final String JAR_FILENAME = "Open_RSC_Launcher.jar";
}
