package nl.emcrypted.oorlogsimulator.commands;

import nl.emcrypted.oorlogsimulator.utils.Color;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CreateArena implements ISubCommand{
    @Override
    public String getPermission() {
        return "oorlogsimulatie.create";
    }

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "Maak een nieuwe arena";
    }

    @Override
    public String getUsage() {
        return "create <arena>";
    }

    @Override
    public void execute(Player player, Command cmd, String[] args) {
        if(args.length != 1 ) {
            player.sendMessage(Color.translate("&cGebruik: /os create <arena>"));
            return;
        }

    }
}
