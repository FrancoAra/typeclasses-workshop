import Entities.{Castle, EntityState, Mage, Warrior}
import Weapons.{FireSpell, Sword}

object HeroesOfTheLambda {

  def attack[A: Attack, B: Damageable](weapon: A, entity: EntityState[B]): LifePoints[B] =
    Attack[A].attack(weapon, entity._2)

  def simultaneousAttack[A, B](a: EntityState[A], b: EntityState[B])(implicit ha: Heroe[A], hb: Heroe[B]): (LifePoints[A], LifePoints[B]) =
    (hb.atk.attack(b._1, a._2)(ha.dmg), ha.atk.attack(a._1, b._2)(hb.dmg))

  val star = Sword("Seventh Star", 10)
  val fury = Sword("Luna's Fury", 9)
  val fireball = FireSpell("Fireball", 2, 6)
  val fireblast = FireSpell("Fireblast", 0, 8)

  val ragnar = LifePoints.heal(Warrior("Iron Clan", fury))
  val aela = LifePoints.heal(Warrior("Morning Star", star))
  val rohan = LifePoints.heal(Mage("Magma", fireball))
  val baern = LifePoints.heal(Mage("Magma", fireblast))
  val warwick = LifePoints.heal(Castle("England"))

  implicit class HeroeStateActions[A: Heroe](state: EntityState[A]) {

    val heroe = implicitly[Heroe[A]]

    def attack[B: Damageable](b: EntityState[B]): EntityState[B] =
      b._1 -> heroe.atk.attack(state._1, b._2)

    def heal: EntityState[A] = LifePoints.heal(state._1)(heroe.dmg)
  }
}

object examples {

  import HeroesOfTheLambda._
  val a1 = attack(star, ragnar)
  val a2 = attack(baern, aela)
  val a3 = rohan attack warwick

  val someFireball: Option[FireSpell] = Some(fireball)
  val noneSword: Option[Sword] = None

  val b1 = attack(someFireball, baern)
  val b2 = attack(noneSword, baern)

  // ! val c1 = attack(warwick, aela)
  // ! val c2 = simultaneousAttack(star, rohan)

  val d1 = simultaneousAttack(ragnar, rohan)
}

/**
  * Challenges:
  *
  * Add a healing weapons, that instead of decreasing life, increases it.
  * Add a Priest profession that uses healing weapons.
  *
  * Add an Attack recursive resolution for a tuple of two Attack things.
  * Add daggers to the game.
  * Add a Rogue profession which weapon is a tuple of daggers.
  *
  * Add an Attack recursive resolution for a list of Attack things.
  * Add cannons to the game.
  * Make castles be able to attack with a list of cannons.
  */
