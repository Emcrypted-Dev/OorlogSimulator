package nl.emcrypted.oorlogsimulator;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.UUID;

public class Team {

    int id;
    String kleur;
    ArrayList<UUID> members;
    private Location teamSpawn;

    public Team(int id, int teamId) {
        this.id = teamId;
        teamSpawn = new Location(Bukkit.getWorld(OorlogSimulator.getArenaConfig().getString(id + ".teams." + teamId + ".spawn.world")),
                OorlogSimulator.getArenaConfig().getDouble(id + ".teams." + teamId + ".spawn.x"),
                OorlogSimulator.getArenaConfig().getDouble(id + ".teams." + teamId + ".spawn.x"),
                OorlogSimulator.getArenaConfig().getDouble(id + ".teams." + teamId + ".spawn.x"));
        kleur = OorlogSimulator.getArenaConfig().getString(id + ".teams." + teamId + ".kleur");
    }


    public void addMember(UUID uuid) {
        members.add(uuid);
    }
}
