package fpinscala.laziness

import org.scalatest.flatspec.AnyFlatSpec

class StreamSpec extends AnyFlatSpec {
  val xs: Stream[Int] = Stream(1, 2, 3)

  private implicit def streamToList[A](xs: Stream[A]): List[A] =
    xs.toList

  private def eq[F[_], A](actual: F[A], expected: F[A]): Boolean =
    actual == expected

  it should "headOption" in {
    assert(xs.headOption.contains(1))
  }

  it should "toList" in {
    assert(xs.toList == List(1, 2, 3))
  }

  it should "drop" in {
    assert(eq[List, Int](
      actual = xs.drop(2),
      expected = List(3)
    ))
  }

  it should "take" in {
    assert(eq[List, Int](
      actual = xs.take(2),
      expected = List(1, 2)
    ))
  }

  it should "takeWhile" in {
    assert(eq[List, Int](
      actual = xs.takeWhile(x => x <= 2),
      expected = List(1, 2)
    ))
  }

  it should "forAll" in {
    assert(!xs.forAll(_ != 1))
  }

  it should "takeWhileViaFold" in {
    assert(eq[List, Int](
      actual = xs.takeWhileViaFold(x => x <= 2),
      expected = List(1, 2)
    ))
  }

  it should "map" in {
    assert(eq[List, Int](
      actual = xs.map(x => x * x),
      expected = List(1, 4, 9)
    ))
  }

  it should "filter" in {
    assert(eq[List, Int](
      actual = xs.filter(_ >= 2),
      expected = List(2, 3)
    ))
  }

  it should "append" in {
    assert(eq[List, Int](
      actual = Stream(1, 2, 3).append(Stream(4, 5, 6)),
      expected = (1 to 6).toList
    ))
  }

  it should "flatMap" in {
    assert(eq[List, Int](
      actual = xs.flatMap(x => Stream(x * x)),
      expected = List(1, 4, 9)
    ))
  }

  it should "constant" in {
    assert(eq[List, String](
      actual = Stream.constant("pew").take(5),
      expected = List.fill(5)("pew")
    ))
  }

  it should "from" in {
    assert(eq[List, Int](
      actual = Stream.from(4).take(10),
      expected = (4 to 13).toList
    ))
  }

  it should "fibs" in {
    assert(eq[List, Int](
      actual = Stream.fibs.take(5),
      expected = List(0, 1, 1, 2, 3)
    ))
  }
}
