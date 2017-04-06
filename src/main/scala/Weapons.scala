
object Weapons {

  case class Sword(nickname: String, damage: Int)

  object Sword {

    implicit val attack: Attack[Sword] = new Attack[Sword] {
      def attack[B](weapon: Sword, entity: LifePoints[B])(implicit dmg: Damageable[B]): LifePoints[B] = {
        entity.copy(currentLifePoints = entity.currentLifePoints - dmg.physicalDmg(weapon.damage))
      }
    }
  }

  case class FireSpell(name: String, hitDmg: Int, burningDmg: Int)

  object FireSpell{

    implicit val attack: Attack[FireSpell] = new Attack[FireSpell] {
      def attack[B](weapon: FireSpell, entity: LifePoints[B])(implicit dmg: Damageable[B]): LifePoints[B] = {
        entity.copy(currentLifePoints = entity.currentLifePoints - dmg.physicalDmg(weapon.hitDmg) - dmg.magicDmg(weapon.burningDmg))
      }
    }
  }
}
