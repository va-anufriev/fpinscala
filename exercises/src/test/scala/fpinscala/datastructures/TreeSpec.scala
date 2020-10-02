package fpinscala.datastructures

import org.scalatest.flatspec.AnyFlatSpec

class TreeSpec extends AnyFlatSpec {
  val tree: Branch[Int] = Branch(
    left = Branch(
      left = Leaf(1),
      right = Leaf(2)
    ),
    right = Branch(
      left = Leaf(3),
      right = Leaf(4)
    )
  )

  it should "count size" in {
    assert(Tree.size(tree) == 7)
  }

  it should "find max value in Tree[Int]" in {
    assert(Tree.maximum(tree) == 4)
  }
}
