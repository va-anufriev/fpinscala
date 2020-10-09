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
    assert(none.filter(x => x == 666) == None)
  }

  it should "filter false" in {
    assert(Option(4).filter(x => x == 666) == None)
  }

  it should "flatMap Some" in {
    assert(Option(4).flatMap(x => Option(x * x)) == Option(16))
  }

  it should "flatMap None" in {
    assert(none.flatMap(x => Option(x * x)) == None)
  }

  it should "flatMap Some with f => None" in {
    assert(Option(3).flatMap(_ => none) == None)
  }

  it should "orElse Some" in {
    assert((Option(19) orElse Option(0)) == Option(19))
  }

  it should "orElse None" in {
    assert((none orElse Option(0)) == Option(0))
  }

  it should "map2 - Some and Some is Some" in {
    assert(
      Option.map2[Int, Double, String](
        a = Some(2),
        b = Some(3.0)
      )((a, b) => (a * b).toString) == Some("6.0")
    )
  }

  it should "map2 - None and Some is None" in {
    assert(
      Option.map2[Int, Double, String](
        a = none,
        b = Some(2.0)
      )((a, b) => (a * b).toString) == None
    )
  }
}
