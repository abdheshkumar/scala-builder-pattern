case class Food(ingredients: Seq[String])

class Chef[Pizza <: Chef.Pizza] protected(ingredients: Seq[String]) {

  import Chef.Pizza._

  def addCheese(cheeseType: String): Chef[Pizza with Cheese] = Chef(ingredients :+ cheeseType)

  def addTopping(toppingType: String): Chef[Pizza with Topping] = Chef(ingredients :+ toppingType)

  def addDough: Chef[Pizza with Dough] = Chef(ingredients :+ "dough")

  def build(implicit ev: Pizza =:= FullPizza): Food = Food(ingredients)
}

object Chef {

  sealed trait Pizza

  object Pizza {

    sealed trait EmptyPizza extends Pizza

    sealed trait Cheese extends Pizza

    sealed trait Topping extends Pizza

    sealed trait Dough extends Pizza

    type FullPizza = EmptyPizza with Cheese with Topping with Dough
  }

  def apply[T <: Pizza](ingredients: Seq[String]): Chef[T] = new Chef[T](ingredients)

  def apply(): Chef[Pizza.EmptyPizza] = apply[Pizza.EmptyPizza](Seq())

}

object Main extends App {
  //Chef().addDough.build
  Chef()
    .addCheese("mozzarella")
    .addDough
    .addTopping("olives")
    .build
}