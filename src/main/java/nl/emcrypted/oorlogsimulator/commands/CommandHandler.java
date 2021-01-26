package nl.emcrypted.oorlogsimulator.commands;

import nl.emcrypted.oorlogsimulator.utils.Color;
import nl.emcrypted.oorlogsimulator.utils.Error;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandHandler implements CommandExecutor
{
    public static List<ISubCommand> commands;

    static {
        CommandHandler.commands = new ArrayList<ISubCommand>();
    }

    public static void init() {
        commands.add(new CreateArena());
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length <= 0) {
            sender.sendMessage(this.colorFormat("&6Oorlog Simulator"));
            for (ISubCommand cmd: CommandHandler.getCommands()) {
                sender.sendMessage(Color.translate("/oorlog "+cmd.getUsage()));
            }
            return true;
        }
        final String subCommand = args[0];
        final List<String> newArgsList = new ArrayList<>(Arrays.asList(args));
        newArgsList.remove(0);
        if (sender instanceof Player) {
            for (final ISubCommand subcommand : CommandHandler.commands) {
                if (subcommand.getName().equalsIgnoreCase(subCommand)) {
                    if (subcommand.getPermission() == null) {
                        subcommand.execute((Player)sender, command, newArgsList.toArray(new String[0]));
                        return true;
                    }
                    if (sender.hasPermission(subcommand.getPermission())) {
                        subcommand.execute((Player)sender, command, newArgsList.toArray(new String[0]));
                        return true;
                    }
                    sender.sendMessage(Error.NO_PERMISSION.getError());
                    return true;
                }
            }
        }
        return true;
    }

    public static List<ISubCommand> getCommands() {
        return CommandHandler.commands;
    }

    public String colorFormat(final String s) {
        return s.replace('&', '§');
    }
}

