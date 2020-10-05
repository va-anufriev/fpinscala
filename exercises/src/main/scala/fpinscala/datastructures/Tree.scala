package fpinscala.datastructures

sealed trait Tree[+A] {
  def map[B](f: A => B): Tree[B] =
    this match {
      case Leaf(value) => Leaf(f(value))
      case Branch(left, right) => Branch(left.map(f), right.map(f))
    }

  def fold[B](f: A => B)(g: (B, B) => B): B =
    this match {
      case Leaf(value) => f(value)
      case Branch(left, right) => g(left.fold(f)(g), right.fold(f)(g))
    }

  def size: Int =
    fold[Int](_ => 1)((x, y) => 1 + x + y)
}

case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]


object Tree {
  def size[A](tree: Tree[A]): Int =
    tree match {
      case Leaf(_) => 1
      case Branch(left, right) => 1 + size(left) + size(right)
    }

  def maximum(tree: Tree[Int]): Int =
    tree match {
      case Leaf(value) => value
      case Branch(left, right) => maximum(left) max maximum(right)
    }

  def maximum[A](tree: Tree[A])(max: (A, A) => A): A =
    tree match {
      case Leaf(value) => value
      case Branch(left, right) => max(maximum(left)(max), maximum(right)(max))
    }

  def depth[A](tree: Tree[A]): Int =
    tree match {
      case Leaf(_) => 1
      case Branch(left, right) => 1 + depth(left) max 1 + depth(right)
    }

  def map[A, B](tree: Tree[A])(f: A => B): Tree[B] =
    tree match {
      case Leaf(value) => Leaf(f(value))
      case Branch(left, right) => Branch(map(left)(f), map(right)(f))
    }
}
