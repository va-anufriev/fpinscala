package fpinscala.errorhandling

import org.scalatest.flatspec.AnyFlatSpec
import scala.{Option => _, Some => _, None => _, Either => _, _}

class OptionSpec extends AnyFlatSpec {
  val none: Option[Int] = None

  it should "map Some" in {
    assert(Some(4).map(x => x * x) == Option(16))
  }

  it should "map None" in {
    assert(none.map(x => x * x) == None)
  }

  it should "getOrElse Some" in {
    assert(Option(4).getOrElse(0) == 4)
  }

  it should "getOrElse None" in {
    assert(none.getOrElse(0) == 0)
  }

  it should "filter Some" in {
    assert(Option(4).filter(x => x == 4) == Option(4))
  }

  it should "filter None" in {
    assert(Option(4).filter(x => x == 666) == None)
  }
}
