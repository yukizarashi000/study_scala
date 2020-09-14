object Chapter1 extends App {

  println("## 変数定義")
  // 文字列
  val name: String = "Scala"
  // 数値
  val id: Int = 1
  println("name: " + name)
  println("id:" + id)
  println("------------------------------------------")

  println("## 遅延評価")
  // 遅延評価
  lazy val lazyDate = new java.util.Date
  println("lazyDate:" + lazyDate)
  println("lazyDate:" + lazyDate)
  lazy val lazyDate2 = {
    println("initializing a lazydate2 value...")
    new java.util.Date
  }
  println(lazyDate2)
  println("------------------------------------------")

  println("## メソッド定義")
  def echo(str: String):Unit = println(str)
  echo("echo")

  def double(n: Int) = n * 2
  println(double(3))

  val printer = new Printer()
  printer.echo2("echo2")
  println("------------------------------------------")

  println("## 関数オブジェクト")
  def isAlphameric(str: String): Boolean = str.matches("[a-zA-Z0-9\\s]+")
  println("Amazon EC2:" + isAlphameric("Amazon EC2"))
  println("日本語:" + isAlphameric("日本語"))

  val isAlphamericF = isAlphameric _
  // こっちの書き方でも良い（というよりこっちの方がクロージャで定義できて使いやすい気がする）。
  val isAlphamericF2 = (str: String) => str.matches("[a-zA-Z0-9\\s]+")
  println("isAlphamericF2:" + isAlphamericF2("isAlphamericF2"))

  // Function1の1は引数が1つだけ、という意味。
  // applyはFunction1〜22が唯一持つメソッドで、関数オブジェクトの呼び出し時に自動的に呼ばれる。
  // Function1[String, Boolean]の最後の引数は返り値の型である。
  val isAlphamericF3 = new Function1[String, Boolean] {
    def apply(str: String) = str.matches("[a-zA-Z0-9\\s]+")
  }
  println("isAlphamericF3:" + isAlphamericF3("isAlphamericF3"))

  // 関数オブジェクトは他の関数の引数として渡すことができる。
  // 下記の場合、Seqオブジェクトのfilter関数はStringを引数に取り、返り値がBooleanとなる関数なので、
  // isAlphamericF3を渡すことができ、callback関数として機能する。
  val words = Seq("Scala", "2.12")
  val alphamericWords = words.filter(isAlphamericF3)
  println("words:" + alphamericWords)

  println("------------------------------------------")
}

class Printer {
  // インスタンスメソッドとして定義する場合。
  def echo2(str: String):Unit = println(str)
}





