package xyz.luenn.autoannounce.manager;


import xyz.luenn.autoannounce.MarpleAutoAnnounce;

public final class AnnounceAPI {

    private static boolean initialized = false;
    private static AnnounceManager announceManager;

    private AnnounceAPI() {} // 외부에서 new 불가

    public static void init() {
        if (initialized) {
            throw new IllegalStateException("AnnounceAPI has already been initialized!");
        }
        initialized = true;
        announceManager = new AnnounceManager(MarpleAutoAnnounce.getInstance());
    }

    public static AnnounceManager get() {
        if (!initialized || announceManager == null) {
            throw new IllegalStateException("AnnounceAPI is not initialized yet!");
        }
        return announceManager;
    }
}
