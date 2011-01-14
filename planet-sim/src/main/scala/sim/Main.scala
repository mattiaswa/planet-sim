package sim

import Constants._
object Main extends Logging{
  def main(args: Array[String]) {

    implicit object PlanetOrdering extends Ordering[Planet] {
      def compare(x: Planet, y: Planet) = (x.mass - y.mass).asInstanceOf[Int]
    }

    val planets = List[Planet](
      Planet("Sun", SUN_MASS, Vector(0, 0), Vector(0, 0)),
      Planet("Mercury", EARTH_MASS * 0.06, Vector(0.3876 * AU, 0), Vector(0, 45e3)),
      Planet("Venus", EARTH_MASS * .82, Vector(.7233 * AU, 0), Vector(0, 30e3)),
      Planet("Earth", EARTH_MASS, Vector(AU, 0), Vector(0, 22e3)),
      Planet("Jupiter", 317.89 * EARTH_MASS, Vector(AU * 5.203, 0), Vector(0, 10e3)))

    debug("Planet max: "+ planets.max)
    debug("Planet min: " + planets.min)
    
    info("Staring simulation")
    Universe.startSim(planets)
  }
}
