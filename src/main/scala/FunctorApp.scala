object FunctorApp extends App {

  trait MyOption[+A] { // we have + otherwise how would you be able to return the subtype MyOption[Nothing] it's nto MyNone[A]! + allows for children!
    def isBoxEmpty: Boolean

    def map[B](f: A => B): MyOption[B] = if (isBoxEmpty) MyNone else MySome(f(this.get))

    def get: A
  }

  object MyNone extends MyOption[Nothing] { // it's nothing so it matches any type! (Nothing matches any type!)
    def isBoxEmpty = true

    def get = throw new NoSuchElementException("Hey i'm MyNone i have nothing!")
  }

  case class MySome[+A](x: A) extends MyOption[A] {
    def isBoxEmpty: Boolean = false

    def get: A = x
  }

  println(MySome("dude ive just been Somed!"))
  println(MySome("dude ive just been Somed! - Here you are out of the box!").get)
  println("What would get printed for nothing? " + MyNone.map(_ => println))
  println("Lets do some mapping on some something: " + MySome("something..").map(_ * 4))
}
