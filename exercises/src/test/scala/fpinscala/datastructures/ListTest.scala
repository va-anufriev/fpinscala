package fpinscala.datastructures

import org.scalatest.flatspec.AnyFlatSpec

class ListTest extends AnyFlatSpec {
  it should "tail" in {
    val xs = List(1, 2, 3)

    assert(List.tail(xs) == List(2, 3))
  }

  it should "tail - empty list" in {
    val xs = List()

    assert(List.tail(xs) == Nil)
  }
}
