package com.minecraftdimensions.bungeesuiteteleports.tasks;

import com.minecraftdimensions.bungeesuiteteleports.BungeeSuiteTeleports;
import com.minecraftdimensions.bungeesuiteteleports.utils.OptionalUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PluginMessageTask extends BukkitRunnable {

    private final ByteArrayOutputStream bytes;

    public PluginMessageTask( ByteArrayOutputStream bytes ) {
        this.bytes = bytes;
    }

    public PluginMessageTask( ByteArrayOutputStream b, boolean empty ) {
        this.bytes = b;
    }

    @SuppressWarnings("unchecked")
    public void run() {
        List<Player> players = getOnlinePlayers();
        if ( players.size() == 0 ) {
            return;
        }
        Player p = players.get(0);
        if ( p == null ) {
            return;
        }
        p.sendPluginMessage( BungeeSuiteTeleports.instance, BungeeSuiteTeleports.OUTGOING_PLUGIN_CHANNEL, bytes.toByteArray() );
    }

    public static List<Player> getOnlinePlayers(){
        return new OptionalUtils<>(()->{
            List<Player> onlinePlayers = new ArrayList<>();
            Bukkit.getWorlds().forEach(world -> onlinePlayers.addAll(world.getPlayers()));
            return onlinePlayers;
        }).getOptional().orElseGet(ArrayList::new);
    }
}