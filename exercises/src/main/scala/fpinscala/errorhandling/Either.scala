package fpinscala.errorhandling


import scala.{Option => _, Either => _, Left => _, Right => _, _} // hide std library `Option` and `Either`, since we are writing our own in this chapter

sealed trait Either[+E,+A] {
 def map[B](f: A => B): Either[E, B] =
   this match {
     case Right(value) => Right(f(value))
     case Left(value) => Left(value)
   }

 def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] =
   this match {
     case Right(get) => f(get)
     case Left(get) => Left(get)
   }

 def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] =
   this match {
     case Right(get) => Right(get)
     case Left(_) => b
   }

 def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] =
    flatMap (aa => b map (bb => f(aa, bb)))
}

case class Left[+E](get: E) extends Either[E,Nothing]
case class Right[+A](get: A) extends Either[Nothing,A]

object Either {
  def traverse[E,A,B](es: List[A])(f: A => Either[E, B]): Either[E, List[B]] =
    sequence(es map f)

  def sequence[E,A](es: List[Either[E,A]]): Either[E,List[A]] =
    es match {
      case ::(head, next) => head flatMap (hh => sequence(next) map (hh :: _))
      case Nil => Right(Nil)
    }

  def mean(xs: IndexedSeq[Double]): Either[String, Double] = 
    if (xs.isEmpty) 
      Left("mean of empty list!")
    else 
      Right(xs.sum / xs.length)

  def safeDiv(x: Int, y: Int): Either[Exception, Int] = 
    try Right(x / y)
    catch { case e: Exception => Left(e) }

  def Try[A](a: => A): Either[Exception, A] =
    try Right(a)
    catch { case e: Exception => Left(e) }

}