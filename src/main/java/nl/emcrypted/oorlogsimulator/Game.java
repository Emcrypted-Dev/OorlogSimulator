package nl.emcrypted.oorlogsimulator;

import nl.emcrypted.oorlogsimulator.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.UUID;

public class Game {

    private Arena arena;
    private static ArrayList<Team> teams;

    public Game(Arena arena, ArrayList<Team> teams) {
        this.arena = arena;
        this.teams = teams;
        start();
    }

    public void start() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        for (int i = 0; i <= teams.size() - 1; i++) {
            scoreboard.registerNewTeam(String.valueOf(i));
            scoreboard.getTeam(String.valueOf(i)).setAllowFriendlyFire(false);
            scoreboard.getTeam(String.valueOf(i)).setCanSeeFriendlyInvisibles(true);
            scoreboard.getTeam(String.valueOf(i)).setPrefix(Color.translate(OorlogSimulator.getArenaConfig().getString(arena.getId() + ".teams." + i + ".kleur")));
        }
        for (UUID uuid : arena.getPlayers()) {
            double x = (int) (Math.random() * (teams.size() - 1)) + 1;
            scoreboard.getTeam(String.valueOf(x)).addPlayer(Bukkit.getOfflinePlayer(uuid));
        }
    }
}
