package mcmquery

case class Command(word: String, action : () => String ) { def execute() = action() }

object Command {

  val all = new scala.collection.mutable.MutableList[Command]()

  def apply(word:String, action: () => String) = {
    val c = new Command(word, action)
    all += c
    c
  }

}

object Main {

  def get_cmd() : Array[String] = scala.io.StdIn.readLine("> ").toLowerCase.trim.split("""\s+""").map(_.trim)

  Command("help",    () => "Help text" )
  Command("list",    () => Card.all.mkString("\n") )
  Command("quit",    () => "Bye" )
  Command("unknown", () => "I do not understand" )



  def run_shell() : Unit = {
    while (true) {
      val line = get_cmd()
      Command.all.find(c => c.word == line(0)) match {
        case None => println("I do not understand.")
        case Some(c@Command("quit", _)) => { println(c.execute()); return }
        case Some(c) => { println(c.execute()) }
      }
    }
  }

  def main(args: Array[String]): Unit = {
    run_shell()
  }

}
