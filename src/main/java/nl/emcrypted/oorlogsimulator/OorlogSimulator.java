package nl.emcrypted.oorlogsimulator;

import nl.emcrypted.oorlogsimulator.commands.CommandHandler;
import nl.emcrypted.oorlogsimulator.events.JoinEvent;
import nl.emcrypted.oorlogsimulator.events.KillEvent;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class OorlogSimulator extends JavaPlugin {

    private static YamlConfiguration arenaConfig;
    private File arenaFile;
    private File dataFile;

    public static YamlConfiguration getDataConfig() {
        return dataConfig;
    }

    private static YamlConfiguration dataConfig;

    public static OorlogSimulator getInstance() {
        return instance;
    }

    private static OorlogSimulator instance;

    public static YamlConfiguration getArenaConfig() {
        return arenaConfig;
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        try {
            generateFiles();
            setArenaConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
        instance = this;
        new Manager();
        this.getCommand("oorlog").setExecutor(new CommandHandler());
        CommandHandler.init();

        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
        getServer().getPluginManager().registerEvents(new KillEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void generateFiles() throws IOException {
        Files.createDirectories(Paths.get(String.valueOf(this.getDataFolder())));
        arenaFile = new File(this.getDataFolder() + "/arenas.yml");
        arenaFile.createNewFile();
        arenaConfig = YamlConfiguration.loadConfiguration(arenaFile);
        dataFile = new File(this.getDataFolder()+"/data.yml");
        dataFile.createNewFile();
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    public void setArenaConfig() throws IOException {
        arenaConfig.addDefault("0.spawn.world", "world");
        arenaConfig.addDefault("0.spawn.x", 100);
        arenaConfig.addDefault("0.spawn.y", 70);
        arenaConfig.addDefault("0.spawn.z", 100);
        arenaConfig.addDefault("0.min-spelers", 1);
        arenaConfig.addDefault("0.max-spelers", 10);
        arenaConfig.addDefault("0.name", "&6Voorbeeld");
        arenaConfig.addDefault("0.teams.1.kleur", "&c");
        arenaConfig.addDefault("0.teams.1.spawn.world", "world");
        arenaConfig.addDefault("0.teams.1.spawn.x", 150);
        arenaConfig.addDefault("0.teams.1.spawn.y", 70);
        arenaConfig.addDefault("0.teams.1.spawn.z", 150);
        arenaConfig.addDefault("0.teams.2.kleur", "&3");
        arenaConfig.addDefault("0.teams.1.spawn.world", "world");
        arenaConfig.addDefault("0.teams.2.spawn.x", 50);
        arenaConfig.addDefault("0.teams.2.spawn.y", 70);
        arenaConfig.addDefault("0.teams.2.spawn.z", 150);
        arenaConfig.save(arenaFile);
    }
}
