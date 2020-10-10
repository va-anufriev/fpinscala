package fpinscala.errorhandling

import org.scalatest.flatspec.AnyFlatSpec
import scala.{Option => _, Either => _, Left => _, Right => _, _}

class EitherSpec extends AnyFlatSpec {
  val left: Either[Exception, Int] = Left(new Exception)

  it should "mean" in {
    assert(Either.mean(IndexedSeq[Double](1, 2, 3)) match {
      case Right(_) => true
      case Left(_) => false
    })
  }

  it should "map for Right" in {
    assert(Right(4).map(x => x * x) == Right(16))
  }

  it should "map for Left" in {
    assert(left.map(x => x * x) == left)
  }

  it should "orElse for Right" in {
    assert(Right(4).orElse(Right(0)) == Right(4))
  }

  it should "orElse for Left" in {
    assert(left.orElse(Right(0)) == Right(0))
  }

  it should "flatMap Right with f => Right" in {
    assert(Right(4).flatMap(x => Right(x * x)) == Right(16))
  }

  it should "flatMap Right with f => Left" in {
    assert(Right(4).flatMap(_ => left) == left)
  }

  it should "flatMap Left" in {
    assert(left.flatMap(_ => Right(0)) == left)
  }

  it should "map2 - Right and Right is Right" in {
    val result = Right(4).map2[Int, Double, Double](Right(3.0))((a, b) => a * b)
    assert(result == Right(12.0))
  }

  it should "sequence - List of Right is Right" in {
    assert(
      Either.sequence(
        List(
          Right(1),
          Right(2),
          Right(3),
        )
      ) == Right(List(1, 2, 3)))
  }

  it should "sequence - List of Right and Left is Left" in {
    assert(
      Either.sequence(
        List(
          Right(1),
          left,
          Right(3),
        )
      ) match {
        case Left(_) => true
        case Right(_) => false
      })
  }

  it should "traverse - success" in {
    assert(
      Either.traverse[Exception, Int, Int](
        List(1, 2, 3)
      )(x => Right(x * x)) == Right(List(1, 4, 9))
    )
  }

  it should "traverse - failure" in {
    assert(
      Either.traverse[Exception, Int, Int](
        List(1, 2, 3)
      )(_ => left) match {
        case Right(_) => false
        case Left(_) => true
      })
  }
}
