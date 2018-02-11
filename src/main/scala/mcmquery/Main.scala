package mcmquery

abstract case class Command(word : String) {

  def execute() : Boolean

  def register() = Command.doRegister(this)

}

object Command {

  val all = scala.collection.mutable.ArrayBuffer[Command]()

  def doRegister(self: Command) : Unit = {
    all += self
  }

}


object Main {

  def get_cmd() : Array[String] = scala.io.StdIn.readLine("> ").toLowerCase.trim.split("""\s+""").map(_.trim)

  new Command("help") { def execute() = { println("execute help "); false } }.register()
  new Command("list") { def execute() = { println(Card.all.mkString("\n")); false} }.register()
  new Command("quit") { def execute() = { println("Bye"); true } }.register()

  def run_shell() : Unit = {
    var done = false
    while (!done) {
      val line = get_cmd()
      Command.all.find(c => c.word == line(0)) match {
        case None    => println("I do not understand.")
        case Some(c) => done = c.execute()
      }
    }
  }

  def main(args: Array[String]): Unit = {
    run_shell()
  }

}
