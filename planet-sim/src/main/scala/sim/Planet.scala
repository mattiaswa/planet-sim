package sim

import Double2DoubleWrapper._

class Planet(val name: String, val mass: Double, val pos: Vector, val velocity: Vector) {
  var force: Vector = new Vector(0,0)
  def appyForce(f: Vector) = force = force + f

  def update = {
    val posDelta = (force * Constants.SIM_STEP * Constants.SIM_STEP / 2 / mass) + velocity * Constants.SIM_STEP
    Planet(name, mass, pos + posDelta, posDelta / Constants.SIM_STEP)
  }

  override def toString = name + ", Vect pos: " + pos
}

object Planet {
  def apply(name: String, mass: Double, pos: Vector, vel: Vector) = new Planet(name, mass, pos, vel)
}
