package mcmquery

import mcmquery.Types._

case class Card(id: Id, name: Name, cost: Cost, decription: Desciption = "" ) {

  override def toString() : String = {
    name  + ( if (0 == cost.length) "" else s"($cost)" )
  }

}

object Card {

  val all = List(
    Card(1, "Island", ""),
    Card(2, "Merfolk of the Pearl Trident", "U")
  )
}