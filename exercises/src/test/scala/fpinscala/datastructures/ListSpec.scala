package fpinscala.datastructures

import fpinscala.datastructures.List.foldRight
import org.scalatest.flatspec.AnyFlatSpec

class ListSpec extends AnyFlatSpec {
  val xs: List[Int] = List(1, 2, 3)

  it should "tail" in {
    assert(List.tail(xs) == List(2, 3))
  }

  it should "tail with empty list" in {
    val xs = List()

    assert(List.tail(xs) == Nil)
  }

  it should "setHead" in {
    assert(List.setHead(xs, 26) == List(26, 2, 3))
  }

  it should "setHead with empty list" in {
    assert(List.setHead(List(), 26) == List(26))
  }

  it should "drop" in {
    assert(List.drop(xs, 2) == List(3))
  }

  it should "dropWhile" in {
    assert(List.dropWhile(xs)(_ <= 2) == List(3))
  }

  it should "init" in {
    assert(List.init(xs) == List(1, 2))
  }

  it should "length" in {
    assert(List.length(xs) == 3)
  }

  it should "foldLeft, sum, product" in {
    assert(List.foldLeft(xs, "")(_ + _) == "123")
    assert(List.sum3(xs) == 6)

    assert(List.product3(List[Double](1, 2, 3, 4)) == 24)
  }

  it should "reverse" in {
    assert(List.reverse(xs) == List(3, 2, 1))
  }

  it should "append via fold" in {
    assert(List.appendViaFold(xs, List(4, 5)) == List(1, 2, 3, 4, 5))
  }

  it should "concat" in {
    val xs = List(
      List(1, 2),
      List(3, 4),
      List(5, 6)
    )

    assert(List.concat(xs) == List(1, 2, 3, 4, 5, 6))
  }

  it should "plus one" in {
    def mapPlusOne(xs: List[Int]): List[Int] =
      foldRight(xs, List[Int]())((cur, acc) => Cons(cur + 1, acc))

    assert(mapPlusOne(xs) == List(2, 3, 4))
  }

  it should "from double to string" in {
    def mapToString(xs: List[Double]): List[String] =
      foldRight(xs, List.empty[String])((cur, acc) => Cons(cur.toString, acc))

    val xs = List[Double](1.11, 2.22, 3.33)

    assert(mapToString(xs) == List("1.11", "2.22", "3.33"))
  }

  it should "map - x * x" in {
    assert(List.map(xs)(x => x * x) == List(1, 4, 9))
  }

  it should "filter - remove odd numbers" in {
    val xs = List(1, 2, 3, 4)

    assert(List.filter(xs)(x => x % 2 != 0) == List(1, 3))
  }

  it should "flatMap - double values in list" in {
    assert(List.flatMap(xs)(x => List(x, x)) == List(1, 1, 2, 2, 3, 3))
  }

  it should "filter - via flatMap" in {
    val xs = List(1, 2, 3, 4)

    assert(List.filterViaFlatMap(xs)(x => x % 2 != 0) == List(1, 3))
  }
}
