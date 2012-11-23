package com.fernferret.pigcontrol;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class PigControlEntityListener implements Listener {
    private PigControl plugin;
    private static final List<Material> allowedSpawns = new LinkedList<Material>();
    static {
        allowedSpawns.add(Material.NETHERRACK);
        allowedSpawns.add(Material.NETHER_BRICK);
        allowedSpawns.add(Material.NETHER_STALK);
        allowedSpawns.add(Material.NETHER_WARTS);
        allowedSpawns.add(Material.SOUL_SAND);
    }

    public PigControlEntityListener(PigControl plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void creatureSpawnEvent(CreatureSpawnEvent event) {
        // Perform a bunch of short circuiting to reduce load.
        if (event.isCancelled()) {
            return;
        }

        if (event.getEntityType() != EntityType.PIG_ZOMBIE) {
            return;
        }
        // for now, test with eggs
//        if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.DEFAULT) {
//            return;
//        }

        Block spawnBlock = event.getLocation().getBlock();

        if (spawnBlock.getLightLevel() > 7) {
            return;
        }
    }
}
