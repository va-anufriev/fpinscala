package fpinscala.laziness

import org.scalatest.flatspec.AnyFlatSpec

class StreamSpec extends AnyFlatSpec {
  val xs: Stream[Int] = Stream(1, 2, 3)

  it should "headOption" in {
    assert(xs.headOption.contains(1))
  }
}
