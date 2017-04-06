import Entities.EntityState

case class LifePoints[A: Damageable](currentLifePoints: Int)

object LifePoints {

  def heal[A](entity: A)(implicit dmg: Damageable[A]): EntityState[A] =
    entity -> LifePoints(dmg.totalLifePoints)
}
