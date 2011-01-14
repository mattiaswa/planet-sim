package sim

import scala.swing._
import javax.swing._
import java.awt._
import java.util.concurrent._
import Constants._
import java.lang.Math._
object UniverseCanvas {

  val (width, heigth) = (1024, 768)
  val (halfWidth, halfHeigth) = (width / 2, heigth / 2)
  val frame = new JFrame("Sim")

  frame.setSize(new java.awt.Dimension(width, heigth))
  val panel = new PlanetPanel
  frame.getContentPane().add(panel, BorderLayout.CENTER)
  frame.setVisible(true)

  var cnt = 1
  def show(p: Seq[Planet]) {
    SwingUtilities.invokeLater(new Runnable {
      def run = panel.show(p)
    })
  }
}

class PlanetPanel extends JComponent {
  val screenPosition = new Vector(UniverseCanvas.halfWidth, UniverseCanvas.halfHeigth)

  setDoubleBuffered(true)

  var planets: Seq[Planet] = scala.collection.immutable.List()

  def show(planets: Seq[Planet]) = {
    this.planets = planets
    repaint()
  }

  import Constants._
  import java.lang.Math._
  override def paintComponent(g: java.awt.Graphics) {
    super.paintComponent(g)
    for (p <- planets) {
      val radius = getRadius(p)
      val (x, y) = toScreenPosition(p.pos)

      g.fillOval(x, y, radius, radius)
      g.drawString(p.name, x, y - 10)
    }

    def getRadius(p: Planet) = ((pow(p.mass, .1) / pow(SUN_MASS, .1)) * 50).asInstanceOf[Int]

    def toScreenPosition(v: Vector) = {
      val res = screenPosition ** (v / MAX_DISTANCE) + screenPosition
      (res.i(0).asInstanceOf[Int], res.i(1).asInstanceOf[Int])
    }
  }
}

