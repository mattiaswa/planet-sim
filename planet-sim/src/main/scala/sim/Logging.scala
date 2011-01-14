package sim

import org.apache.log4j._
trait Logging {
  private[this] val logger = Logger.getLogger(getClass().getName())

  def debug(f: => String) = if (logger.isDebugEnabled) logger.debug(f)
  def info(f: => String) = if (logger.isInfoEnabled) logger.info(f)
  def trace(f: => String) = if (logger.isTraceEnabled) logger.trace(f)
}