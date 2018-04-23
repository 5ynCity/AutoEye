package com.heirteir.autoeye.check.checks.movement;

import com.heirteir.autoeye.Autoeye;
import com.heirteir.autoeye.check.Check;
import com.heirteir.autoeye.event.events.EventExecutor;
import com.heirteir.autoeye.event.events.event.Event;
import com.heirteir.autoeye.event.events.event.PlayerMoveEvent;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Location;

public class InvalidPitch extends Check {
    public InvalidPitch(Autoeye autoeye) {
        super(autoeye, "Invalid Pitch");
    }

    @EventExecutor public boolean check(PlayerMoveEvent event) {
        //use math.abs since bounds is within 90. Only need to check when packet has look.
        return event.getPacket().isHasLook() && Math.abs(event.getPlayer().getLocationData().getDirection().getY()) > 90;
    }

    @Override public <T extends Event> boolean revert(T event) {
        World world;
        //reset player pitch when caught
        event.getPlayer().getPlayer().teleport(new Location(event.getPlayer().getPlayer().getWorld(), event.getPlayer().getLocationData().getLocation().getX(), event.getPlayer().getLocationData().getLocation().getX(), event.getPlayer().getLocationData().getLocation().getX(), event.getPlayer().getLocationData().getDirection().getX(), 0F));
        return true;
    }
}
