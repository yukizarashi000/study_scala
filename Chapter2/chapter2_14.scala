object Chapter2_14 extends App {
  // 暗黙の状態の引き渡し
  def printContext(implicit ctx: Int): Unit = {
    println(ctx)
  }
  // 引数を渡していないが、暗黙的に定義された値を利用して出力を行っている。
//  implicit val context = 1
//  printContext
  def printContext2(implicit ctx: Int): Unit = {
    printContext
  }
  // 同じ状態を共有するために暗黙的に引数を渡せている。（謎この位置限定なのかは不明）
  implicit val context = 2
  printContext2

  // 暗黙のパラメータを使わないリストの合計メソッド例
  // 型の分だけ定義が必要
  def sumint(list:List[Int]): Int = list.foldLeft(0) {
    (x, y) => x + y
  }
  def sumDouble(list:List[Double]): Double = list.foldLeft(0.0) {
    (x, y) => x + y
  }
  // Stringの場合は文字列の合計=文字列の結合と見る
  def sumString(list:List[String]): String = list.foldLeft("") {
    (x, y) => x + y
  }

  // トレイトを使ったコードの共通化
  trait Adder[T] {
    val zero: T
    def plus(x: T, y: T): T
  }
  def sum[T](list: List[T])(adder: Adder[T]): T = {
    list.foldLeft(adder.zero){(x, y) => adder.plus(x,y)}
  }
  object intAdder extends Adder[Int] {
//    override val zero: Int = 0
    val zero: Int = 0
//    override def plus(x: Int, y: Int): Int = x + y
    def plus(x: Int, y: Int): Int = x + y
  }
  println(sum(List(1,2,3))(intAdder))
  // ここまででAdderの定義をすれば共通の処理を利用できるようになったことがわかる。

  // 暗黙のパラメータの導入
  // 共通の状態を参照する引数をimplicitにし、オブジェクトにもimplicitをつける。
  def sum2[T](list: List[T])(implicit adder: Adder[T]): T = {
    list.foldLeft(adder.zero){(x, y) => adder.plus(x, y)}
  }
  implicit object intAdder2 extends Adder[Int] {
    val zero: Int = 0
    def plus(x: Int, y: Int): Int = x + y
  }
  // 引数に渡す記述を明記しなくても暗黙的に呼び出してくれる。
  println(sum2(List(1,2,3)))

  // Stringでも同様
  implicit object stringAdder extends Adder[String] {
    val zero: String = ""
    def plus(x: String, y: String): String = x + y
  }
  println(sum2(List("A","B","C")))
}