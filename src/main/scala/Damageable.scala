
trait Damageable[A] {

  val totalLifePoints: Int
  val magicResistance: Int
  val physicalResistance: Int

  def magicDmg(dmg: Int): Int =
    if (magicResistance - dmg < 0) -1 * (magicResistance - dmg)
    else 0

  def physicalDmg(dmg: Int): Int =
    if (physicalResistance - dmg < 0) -1 * (physicalResistance - dmg)
    else 0
}

object Damageable {

  /** Summoner function. */
  def apply[A](implicit s: Damageable[A]): Damageable[A] = s

  /** Constructor function. */
  def constructor[A](life: Int, mResist: Int, pResist: Int): Damageable[A] = new Damageable[A] {
    val totalLifePoints = life
    val magicResistance = mResist
    val physicalResistance = pResist
  }
}
