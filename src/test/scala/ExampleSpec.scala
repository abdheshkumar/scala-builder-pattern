/**
  * Created by abdhesh on 12/06/17.
  */

import com.typesafe.config.ConfigFactory

import collection.mutable.Stack
import org.scalatest._

class ExampleSpec extends FlatSpec with Matchers {
  "A Stack" should "pop values in last-in-first-out order" in {

    val a = ConfigFactory.load()
    println(a.getString("name"))

  }

}