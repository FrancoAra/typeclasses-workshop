import Weapons.{FireSpell, Sword}

object Entities {

  type EntityState[A] = (A, LifePoints[A])

  case class Mage(order: String, spell: FireSpell)

  object Mage {

    implicit val dmg: Damageable[Mage] =
      Damageable.constructor(80, 5, 2)

    implicit val atk: Attack[Mage] = new Attack[Mage] {
      def attack[B](weapon: Mage, entity: LifePoints[B])(implicit dmg: Damageable[B]): LifePoints[B] =
        Attack[FireSpell].attack(weapon.spell, entity)
    }
  }

  case class Warrior(guild: String, sword: Sword)

  object Warrior {

    implicit val dmg: Damageable[Warrior] =
      Damageable.constructor(100, 2, 5)

    implicit val atk: Attack[Warrior] = new Attack[Warrior] {
      def attack[B](weapon: Warrior, entity: LifePoints[B])(implicit dmg: Damageable[B]): LifePoints[B] =
        Attack[Sword].attack(weapon.sword, entity)
    }
  }

  case class Castle(country: String)

  object Castle {

    implicit val dmg: Damageable[Castle] =
      Damageable.constructor(1000, 5, 20)
  }
}
