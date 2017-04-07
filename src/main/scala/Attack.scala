import Entities.EntityState

trait Attack[A] {
  def attack[B](weapon: A, entity: LifePoints[B])(implicit dmg: Damageable[B]): LifePoints[B]
}

object Attack extends AttackInstances {

  /** Summoner function. */
  def apply[A](implicit s: Attack[A]): Attack[A] = s
}

trait AttackInstances {

  /** Optional recursive resolution */
  implicit def optInstance[A](implicit a: Attack[A]): Attack[Option[A]] =
    new Attack[Option[A]] {
      def attack[B](weapon: Option[A], entity: LifePoints[B])(implicit dmg: Damageable[B]): LifePoints[B] =
        weapon match {
          case Some(wp) => a.attack(wp, entity)
          case None => entity
        }
    }

  /** Entity state recursive resolution */
  implicit def entityStateInstance[A](implicit a: Attack[A]): Attack[EntityState[A]] =
    new Attack[(A, LifePoints[A])] {
      def attack[B](weapon: (A, LifePoints[A]), entity: LifePoints[B])(implicit dmg: Damageable[B]): LifePoints[B] =
        a.attack(weapon._1, entity)
    }
}