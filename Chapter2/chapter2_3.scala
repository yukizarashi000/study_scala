import scala.collection.mutable.Buffer

// クラスからnewする機能を省いたものをトレイトという。
// インターフェース的な機能かつメソッドの中身も定義できる。
// クラスがトレイトを継承することをミックスインという。

object Chapter2_3 extends App {
  val taro = new Employee("太郎")
  taro.display()
}

trait Namable {
  val name: String
  def display(): Unit = println(name)
}

trait Enumerable[A] {
  def foreach[B](fun: A => B): Unit
  final def map[B](f:A => B): List[B] = {
    var members = Buffer.empty[B]
    // foreachに関数を渡す
    foreach { m => members += f(m) }
    members.toList
  }
  final def filter(p: A => Boolean): List[A] = {
    val members = Buffer.empty[A]
    foreach { m => if(p(m)) members += m }
    members.toList
  }
  final def toList: List[A] = {
    val members = Buffer.empty[A]
    foreach{ m =>
      members += m
    }
    members.toList
  }
}

// この辺うまく実装できないので後回し
//class Employee(val name: String) extends AnyRef with Namable
//class Staff(val name: String, val age: Int)
//class Shop(val name: String) extends AnyRef with Enumerable with Namable {
//  private[this] staffs: List[Staff] = List("太郎", "花子")
//  override def foreach[B](f: A => B): Unit = staffs.foreach(f)
//}

// トレイトの実装
//trait トレイト名 extends スーパートレイト1 with スーパートレイト2 {
//
//}

// トレイトをミックスインするクラスの実装
//class クラス名 extends スーパークラス with スーパートレイト... {
//
//}