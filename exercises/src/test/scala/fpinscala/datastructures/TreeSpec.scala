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

  it should "count size - via fold" in {
    assert(tree.size == 7)
  }

  it should "find max value in Tree[Int]" in {
    assert(Tree.maximum(tree) == 4)
  }

  it should "find max value in Tree[A]" in {
    Tree.maximum[Int](tree)(_ max _)
  }

  it should "count depth" in {
    val tree =
      Branch(
        left = Leaf(1),
        right = Branch(
          left = Branch(
            left = Leaf(3),
            right = Leaf(4)
          ),
          right = Leaf(5)
        )
      )

    assert(Tree.depth(tree) == 4)
  }

  it should "map" in {
    val expected: Branch[Int] = Branch(
      left = Branch(
        left = Leaf(1),
        right = Leaf(4)
      ),
      right = Branch(
        left = Leaf(9),
        right = Leaf(16)
      )
    )

    assert(Tree.map(tree)(x => x * x) == expected)
    assert(tree.map(x => x * x) == expected)
  }
}
