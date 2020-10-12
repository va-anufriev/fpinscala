package fpinscala.laziness

import org.scalatest.flatspec.AnyFlatSpec

class StreamSpec extends AnyFlatSpec {
  val xs: Stream[Int] = Stream(1, 2, 3)

  it should "headOption" in {
    assert(xs.headOption.contains(1))
  }

  it should "toList" in {
    assert(xs.toList == List(1, 2, 3))
  }

  it should "drop" in {
    assert(xs.drop(2).toList == List(3))
  }

  it should "take" in {
    assert(xs.take(2).toList == List(1, 2))
  }

  it should "takeWhile" in {
    assert(xs.takeWhile(x => x <= 2).toList == List(1, 2))
  }

  it should "forAll" in {
    assert(!xs.forAll(_ != 1))
  }
}
