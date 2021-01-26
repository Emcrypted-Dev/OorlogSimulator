package nl.emcrypted.oorlogsimulator;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Config {

    private static OorlogSimulator main;

    public Config(OorlogSimulator main) {
        Config.main = main;
    }

    public static int getCountDownSeconds() {
        return main.getConfig().getInt("start-tijd");
    }

    public static Location getMainSpawnLocation() {
        return new Location(
                Bukkit.getWorld(main.getConfig().getString("main-lobby-spawn.world")),
                main.getConfig().getDouble("main-lobby-spawn.x"),
                main.getConfig().getDouble("main-lobby-spawn.y"),
                main.getConfig().getDouble("main-lobby-spawn.z"),
                90, 90);
    }

    public static Location getArenaLobbyLocation(int id) {
        return new Location(
                Bukkit.getWorld(OorlogSimulator.getArenaConfig().getString(id+"spawn.world")),
                OorlogSimulator.getArenaConfig().getDouble(id+"spawn.x"),
                OorlogSimulator.getArenaConfig().getDouble(id+"spawn.y"),
                OorlogSimulator.getArenaConfig().getDouble(id+"spawn.z"),
                90, 90);
    }

    public static int getArenaAmount() { return OorlogSimulator.getArenaConfig().getKeys(false).size(); }
}
