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

  it should "takeWhileViaFold" in {
    assert(xs.takeWhileViaFold(x => x <= 2).toList == List(1, 2))
  }

  it should "map" in {
    assert(xs.map(x => x * x).toList == List(1, 4, 9))
  }

  it should "filter" in {
    assert(xs.filter(_ >= 2).toList == List(2, 3))
  }

  it should "append" in {
    assert(
      Stream(1, 2, 3).append(Stream(4, 5, 6)).toList == (1 to 6).toList
    )
  }

  it should "flatMap" in {
    assert(xs.flatMap(x => Stream(x * x)).toList == List(1, 4, 9))
  }
}
