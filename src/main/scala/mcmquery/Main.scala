package mcmquery

abstract case class Command(word : String) {

  def done() : Boolean = false

  def execute() : Unit

  def register() : Unit = Command.doRegister(this)

}

object Command {

  import scala.collection.mutable.ArrayBuffer

  val all : ArrayBuffer[Command] = ArrayBuffer[Command]()

  def doRegister(self: Command) : Unit = {
    all += self
  }

}


object Main {

  def get_cmd() : Array[String] = scala.io.StdIn.readLine("> ").toLowerCase.trim.split("""\s+""").map(_.trim)

  new Command("help") { def execute() { println("execute help ") } }.register()
  new Command("list") { def execute() { println(Card.all.mkString("\n")) } }.register()
  new Command("quit") { def execute() { println("Bye") }; override def done() = true }.register()

  def run_shell() : Unit = {
    var done = false
    while (!done) {
      val line = get_cmd()
      Command.all.find(c => c.word == line(0)) match {
        case None    => println("I do not understand.")
        case Some(c) => c.execute(); done = c.done()
      }
    }
  }

  def main(args: Array[String]): Unit = {
    run_shell()
  }

}
