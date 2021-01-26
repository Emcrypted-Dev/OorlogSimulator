package nl.emcrypted.oorlogsimulator;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.graalvm.compiler.nodes.TypeCheckHints;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    private int id;
    private ArrayList<UUID> players;
    private Location spawn;
    private GameState state;
    private CountDown countDown;
    private ArrayList<Team> teams;

    public int getRequiredPlayers() {
        return requiredPlayers;
    }

    private int requiredPlayers;




    public Arena(int id) {
        teams = new ArrayList<>();
        this.id = id;
        players = new ArrayList<>();
        spawn = Config.getArenaLobbyLocation(id);
        state = GameState.RECRUITING;
        countDown = new CountDown(this);
        requiredPlayers = OorlogSimulator.getArenaConfig().getInt(id+".min-spelers");
       for(int a = 0; a <= (OorlogSimulator.getArenaConfig().getConfigurationSection(id+".teams").getKeys(false).size() -1); a++) {
           teams.add(new Team(id, a));
        }
    }

    public void sendMessage(String message) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
    }

    public int getId() { return id; }

    public List<UUID> getPlayers() { return players; }

    public Location getSpawn() { return spawn; }

    public void addPlayer(Player player) {
        players.add(player.getUniqueId());
        player.teleport(spawn);

        if (players.size() >= getRequiredPlayers()) {
            countDown.begin();
        }
    }

    public void removePlayer(Player player) {
        players.remove(player);
        player.teleport(Config.getMainSpawnLocation());
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }


    public void start() {
        new Game(this, teams);
    }
}
