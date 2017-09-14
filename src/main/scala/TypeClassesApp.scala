object TypeClassesApp extends App {

  sealed trait Eyes

  case class YellowEyes() extends Eyes

  case class EmptyPits() extends Eyes

  trait Gaze[A] {
    def color(a: A): String
  }

  implicit val yellowGaze = new Gaze[YellowEyes] {
    def color(a: YellowEyes) = "Yellow"
  }
  implicit val emptyGaze = new Gaze[EmptyPits] {
    def color(a: EmptyPits) = "Black"
  }

  sealed trait Animal

  case class Shark[E <: Eyes](eyes: E) extends Animal

  case class BlindMoleRat() extends Animal

  implicit def likeDollsEyes[E <: Eyes : Gaze] = new Gaze[Shark[E]] {
    def color(a: Shark[E]) = implicitly[Gaze[E]].color(a.eyes)
  }

  def printEyeColor[A: Gaze](a: A) = println(implicitly[Gaze[A]].color(a))

  printEyeColor(YellowEyes())
  printEyeColor(Shark(EmptyPits()))
}
