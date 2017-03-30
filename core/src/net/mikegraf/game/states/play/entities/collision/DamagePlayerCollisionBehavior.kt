package net.mikegraf.game.states.play.entities.collision

import net.mikegraf.game.states.play.contact.CollisionInfo
import net.mikegraf.game.states.play.entities.player.Player

class DamagePlayerCollisionBehavior(val damageAmount: Int) : ICollisionBehavior {
    override fun handleCollision(info: CollisionInfo?) {
        val entity = info?.otherEntity
        val thisEntity = info?.thisEntity
        if (thisEntity != null && entity != null && entity is Player) {
            entity.damage(damageAmount)
            entity.push(thisEntity.position, 10f)
        }
    }

    override fun handleEndCollision(info: CollisionInfo?) { }
}
