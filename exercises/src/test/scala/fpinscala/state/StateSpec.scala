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
}
