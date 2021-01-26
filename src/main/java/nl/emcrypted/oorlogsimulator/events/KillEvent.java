package nl.emcrypted.oorlogsimulator.events;

import nl.emcrypted.oorlogsimulator.OorlogSimulator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillEvent implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {
            try {
                OorlogSimulator.getDataConfig().set(event.getEntity().getKiller().getUniqueId().toString() + ".kills", OorlogSimulator.getDataConfig().getInt(event.getEntity().getKiller().getUniqueId().toString() + ".kills") + 1);
            } catch (NullPointerException e) {
                OorlogSimulator.getDataConfig().set(event.getEntity().getKiller().getUniqueId().toString() + ".kills", 1);
            }

            try {
                OorlogSimulator.getDataConfig().set(event.getEntity().getUniqueId().toString() + ".deaths", OorlogSimulator.getDataConfig().getInt(event.getEntity().getUniqueId().toString() + ".deaths") + 1);
            } catch (NullPointerException exception) {
                OorlogSimulator.getDataConfig().set(event.getEntity().getUniqueId().toString() + ".deaths", 1);
            }
        }
    }
}
