package nl.emcrypted.oorlogsimulator.events;

import nl.emcrypted.oorlogsimulator.OorlogSimulator;
import nl.emcrypted.oorlogsimulator.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

public class JoinEvent implements Listener {

    private OorlogSimulator main;

    public JoinEvent(OorlogSimulator main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        if (!OorlogSimulator.getDataConfig().getKeys(false).contains(event.getPlayer().getUniqueId().toString())) {
           OorlogSimulator.getDataConfig().set(event.getPlayer().getUniqueId().toString()+".rank", "&7Default");
           OorlogSimulator.getDataConfig().set(event.getPlayer().getUniqueId().toString()+".kills", 0);
        }

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy");
        objective.setDisplayName(Color.translate(main.getConfig().getString("scoreboard.lobby.title")));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        for (int i = 0; i <= main.getConfig().getStringList("scoreboard.lobby.content").size(); i++) {
            Team newTeam = scoreboard.registerNewTeam(String.valueOf(i));
            newTeam.addEntry(Color.translate("&" + i));
            newTeam.setPrefix(main.getConfig().getStringList("scoreboard.lobby.content")
                    .get(main.getConfig().getStringList("scoreboard.lobby.content").size() - i).replace("<name>", event.getPlayer().getName())
                    .replace("<rank>", OorlogSimulator.getDataConfig().getString(event.getPlayer().getUniqueId().toString() + ".rank"))
                    .replace("<kills>", String.valueOf(OorlogSimulator.getDataConfig().getInt(event.getPlayer().getUniqueId().toString() + ".kills")))
                    .replace("<deaths>", String.valueOf(OorlogSimulator.getDataConfig().getInt(event.getPlayer().getUniqueId().toString() + ".deaths")))
                    .replace("<gewonnen>", String.valueOf(OorlogSimulator.getDataConfig().getInt(event.getPlayer().getUniqueId().toString() + ".wins")))
                    .replace("<verloren>", String.valueOf(OorlogSimulator.getDataConfig().getInt(event.getPlayer().getUniqueId().toString() + ".losses"))));


        }

        event.getPlayer().setScoreboard(scoreboard);
    }
}
