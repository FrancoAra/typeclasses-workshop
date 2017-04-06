import Entities.EntityState

case class Heroe[A: Attack: Damageable](atk: Attack[A], dmg: Damageable[A])

object Heroe {

  implicit def instance[A](implicit a: Attack[A], d: Damageable[A]): Heroe[A] = Heroe[A](a, d)
}
