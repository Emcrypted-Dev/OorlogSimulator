package nl.emcrypted.oorlogsimulator.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public interface ISubCommand
{
    String getPermission();

    String getName();

    String getDescription();

    String getUsage();

    void execute(final Player p0, final Command p1, final String[] p2);
}
