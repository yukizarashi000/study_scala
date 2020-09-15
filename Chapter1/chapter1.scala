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

  println("## if/else")
  val weight = 120
  val message = if(weight <= 100) {
    "OK"
  } else {
    "NG"
  }
  println(message)
  // 三項演算子の代わりにif/elseで代入前に判定が可能
  val message2 = if(weight <= 100) "OK" else "NG"
  println("三項演算子:" + message2)
  println("------------------------------------------")

  println("## match")
  // switch文のような使い方
  val n = 5
  n match {
    case 1 => println("Gold")
    case 2 => println("Silver")
    case 3 => println("Bronze")
    case other => println("You don't get a prize")
  }
  // Option型の判定
  // Some型の場合
  val maybeNum: Option[Int] = Some(123)
  val num: Int = maybeNum match {
    case Some(num) => num
    case None => 0
  }
  println(num)
  // None型の場合
  val maybeNum2: Option[Int] = None
  val num2: Int = maybeNum2 match {
    case Some(num2) => num2
    case None => 0
  }
  println(num2)
  // 値がマイナスの時0にしたい
  val maybeNum3: Option[Int] = Some(-1)
  val num3 = maybeNum3 match {
    case Some(num3) if num3 < 0 => 0
    case Some(num3) => num3
    case None => 0
  }
  println(num3)
  // case条件を複数にする
  val num4 = 3
  num4 match {
    case 1 | 2 | 3 => println("Less than 4")
    case 4 => println("Equal to 4")
    case other => println("Greater than 4")
  }
  println("------------------------------------------")

  println("## ループ")
  // Scalaではwhileは基本使わない.
  // filter(抽出)
  // 素直に書いた場合
  val filtered1 = Seq(1,2,3).filter(i => i > 1)
  println("filtered1: " + filtered1)

  // 省略記法(i => iなど引数と関数に利用する値が同じ時)
  val filtered2 = Seq(1,2,3).filter(_ > 1)
  println("filtered2: " + filtered2)

  // map(変換)
  // 素直に書いた場合。
  val mapped1 = Seq(1,2,3).map(i => i * 2)
  println("mapped1: " + mapped1)

  // 省略記法
  val mapped2 = Seq(1,2,3).map(_ * 2)
  println("mapped2: " + mapped2)

  // コレクションメソッドの内容の複数行記述
  val mapped3 = Seq(1,2,3).map { i =>
    println(i)
    i * 2
  }

  // flatMap(コレクション内コレクション処理後、全体結合）
  val flatMapped = Seq(Seq(1,2), Seq(3,4)).flatMap {s => s.filter(_ > 1)}
  println(flatMapped)
  val flatMapped2 = (1 to 3).flatMap(i => (2 to 4).map(j => i * j).filter(_ % 2 == 0))
  println(flatMapped2)

  // flatMapでコレクションを操作・結合する場合。
  val results: Seq[Int] = (1 to 3).flatMap { i =>
    (2 to 4).flatMap { j =>
      (3 to 5).map(k => i * j * k).filter(_ % 3 == 0)
    }
  }
  println(results)

  // forでコレクションを操作・結合する場合（仕組みは後々）。
  val results2: Seq[Int] = for {
    i <- (1 to 3)
    j <- (2 to 4)
    k <- (3 to 5)
    result = (i * j * k) if result % 3 == 0
  } yield result
  println(results2)
  println("------------------------------------------")

  // memo: scalacはJavaで作られているScalaのコンパイラを行うプログラムを実行するコマンド。
  // sbt, Gradle, Mavenでもコンパイルできる仕組みはある。


}

class Printer {
  // インスタンスメソッドとして定義する場合。
  def echo2(str: String):Unit = println(str)
}





