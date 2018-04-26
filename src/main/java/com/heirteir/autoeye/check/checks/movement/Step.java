/*
 * Created by Justin Heflin on 4/19/18 8:21 PM
 * Copyright (c) 2018.
 *
 * Can be redistributed non commercially as long as credit is given to original copyright owner.
 *
 * last modified: 4/19/18 8:20 PM
 */
package com.heirteir.autoeye.check.checks.movement;

import com.heirteir.autoeye.Autoeye;
import com.heirteir.autoeye.check.Check;
import com.heirteir.autoeye.player.AutoEyePlayer;

public class Step extends Check {
    public Step(Autoeye autoeye) {
        super(autoeye, "Step");
    }

    @Override public boolean check(AutoEyePlayer player) {
        return player.isConnected() && player.getTimeData().getLastVelocity().getDifference() > 500 && player.getLocationData().isChangedPos() && !player.getPhysics().isFlying() && !player.getLocationData().isOnPiston() && Math.abs(player.getPhysics().getCalculatedYVelocity()) < (player.getPhysics().getJumpVelocity() < 0.5625F ? 0.5625F : player.getPhysics().getJumpVelocity()) && (player.getPhysics().getJumpVelocity() < 0.5625F ? 0.5625F : player.getPhysics().getJumpVelocity()) < player.getPhysics().getClientVelocity().getY();
    }

    @Override public boolean revert(AutoEyePlayer player) {
        player.teleport(player.getLocationData().getTeleportLocation());
        return false;
    }
}
