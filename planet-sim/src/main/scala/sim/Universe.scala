package sim

object Constants {
  val SIM_STEP = 1000
  val EARTH_MASS = 5.97e24
  val G: Double = 6.7e-11
  val SUN_MASS = 332950 * EARTH_MASS
  val AU = 149.60e9

  val MAX_DISTANCE = 7 * AU

}

object Universe {

  import Double2DoubleWrapper._
  import java.lang.Math._

  def startSim(p: Seq[Planet]) = {
    val d = UniverseCanvas
    var planets = p
    while (true) {
      d.show(planets)

      updateForces(planets)
      planets = planets.map(_.update)

      def updateForces(p2: Seq[Planet]): Unit = {
        p2 match {
          case currentPlanet :: rest => {
            rest.foreach(counterPlanet => {
              val f = Constants.G * counterPlanet.mass * currentPlanet.mass * (counterPlanet.pos - currentPlanet.pos) / pow(counterPlanet.pos distanceSquared currentPlanet.pos, 1.5)

              currentPlanet.appyForce(f);
              counterPlanet.appyForce(-1 * f)
            })
            updateForces(rest)
          }
          case Nil =>
        }

      }
    }
  }
}