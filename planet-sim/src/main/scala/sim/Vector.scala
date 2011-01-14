package sim

import java.lang.Math._

object Double2DoubleWrapper {
  implicit def double2Wrapper(doub: Double): DoubleWrapper = new DoubleWrapper(doub)
}

class DoubleWrapper(d: Double) {
  def *(vec: Vector): Vector = vec * d
}

object Vector {
  def apply(k: Double): Vector = new Vector(k)
}

case class Vector(val i: Double*) {

  private val mult: (Double, Double) => Double = { (x, y) => x * y }
  private val diff: (Double, Double) => Double = { (x, y) => x - y }
  private val add: (Double, Double) => Double = { (x, y) => x + y }
  private val sumSquare : (Double, Double) => Double = {_ + pow(_, 2)}
  /**
   * Component wise multiplication of the two vectors
   * @return
   */
  def **(rhs: Vector): Vector = new Vector(performOperationComponentwise(rhs, mult): _*)

  /**
   * Scalar product
   * @return
   */
  def *(rhs: Vector): Double = performOperationComponentwise(rhs, mult).foldLeft(0.0) { add }

  def distanceSquared(o: Vector): Double = performOperationComponentwise(o, diff).foldLeft(0.0) {sumSquare }

  def distance(o: Vector): Double = sqrt(distanceSquared(o))

  def len = sqrt(i.foldLeft(0.0) {sumSquare})
  
  def *(scalar: Double): Vector = scalarOperation(scalar * _)

  def /(scalar: Double): Vector = scalarOperation(_ / scalar)

  def -(rhs: Vector): Vector = new Vector(performOperationComponentwise(rhs, diff): _*)
  def +(rhs: Vector): Vector = new Vector(performOperationComponentwise(rhs, add): _*)

  /**
   * The direction between this and rhs, with a distance of 1
   */
  def ||(rhs: Vector): Vector = new Vector((this - rhs).i: _*) / (this distance rhs)
  

  private def scalarOperation(func: Double => Double) = new Vector(i.map(func(_)): _*)

  private def performOperationComponentwise(rhs: Vector, operation: (Double, Double) => Double) = for ((x, y) <- i.zip(rhs.i)) yield operation(x, y)
}

