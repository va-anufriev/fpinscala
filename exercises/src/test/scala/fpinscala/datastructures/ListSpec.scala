package fpinscala.datastructures

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
}
