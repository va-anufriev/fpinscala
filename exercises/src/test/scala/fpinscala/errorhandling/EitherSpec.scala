package fpinscala.errorhandling

import org.scalatest.flatspec.AnyFlatSpec
import scala.{Option => _, Either => _, Left => _, Right => _, _}

class EitherSpec extends AnyFlatSpec {
  it should "mean" in {
    assert(Either.mean(IndexedSeq[Double](1, 2, 3)) match {
      case Right(_) => true
      case Left(_) => false
    })
  }
}
