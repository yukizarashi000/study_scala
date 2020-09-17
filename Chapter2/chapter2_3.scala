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
    foreach { m =>
      members += f(m)
    }
    members.toList
  }
}

class Employee(val name: String) extends AnyRef with Namable
