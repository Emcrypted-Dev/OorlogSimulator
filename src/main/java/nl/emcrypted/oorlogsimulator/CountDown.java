package nl.emcrypted.oorlogsimulator;

import nl.emcrypted.oorlogsimulator.utils.Color;
import org.bukkit.scheduler.BukkitRunnable;

public class CountDown extends BukkitRunnable {

    private Arena arena;
    private int seconds;

    public CountDown(Arena arena) {
        this.arena = arena;
        this.seconds = Config.getCountDownSeconds();

    }

    public void begin() {
        arena.setState(GameState.COUNTDOWN);
        this.runTaskTimer(OorlogSimulator.getInstance(), 0L, 20L);
    }

    @Override
    public void run() {


        if (seconds == 0) {
            cancel();
            arena.start();
            return;
        }

        if (seconds % 30 == 0 || seconds <= 10) {
            if (seconds == 1) {
                arena.sendMessage(Color.translate("Simulatie begint in 1 seconde!"));
            } else {
                arena.sendMessage(Color.translate("Simulatie begint in " + seconds + " seconden!"));
            }
        }

        if (arena.getPlayers().size() < arena.getRequiredPlayers()) {
            cancel();
            arena.setState(GameState.RECRUITING);
            arena.sendMessage(Color.translate("Niet genoeg spelers om te beginnen!"));
            return;
        }
        seconds--;
    }
}
