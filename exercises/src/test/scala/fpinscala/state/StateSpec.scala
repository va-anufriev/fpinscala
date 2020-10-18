package fpinscala.state

import org.scalatest.flatspec.AnyFlatSpec

class StateSpec extends AnyFlatSpec {
  val rng: RNG.Simple = RNG.Simple(1)

  it should "nonNegativeInt" in {
    assert(
      RNG.nonNegativeInt(rng) match {
        case (value, _) => value >= 0
      }
    )
  }

  it should "double" in {
    assert(
      RNG.double(rng) match {
        case (value, _) => value >= 0.0 && value < 1.0
      }
    )
  }

  it should "ints" in {
    assert(RNG.ints(5)(rng)._1.toSet.size == 5)
  }
}
