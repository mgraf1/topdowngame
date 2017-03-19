package net.mikegraf.game.states.play.entities.collision

import net.mikegraf.game.states.play.contact.CollisionInfo
import net.mikegraf.game.states.play.entities.player.Player

class DamagePlayerCollisionBehavior(val damageAmount: Int) : ICollisionBehavior {
    override fun handleCollision(info: CollisionInfo?) {
        val entity = info?.otherEntity
        if (entity != null && entity is Player) {
            entity.damage(damageAmount)
        }
    }

    override fun handleEndCollision(info: CollisionInfo?) { }
}
