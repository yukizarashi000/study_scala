object Chapter2_6 extends App {
  // Mapのキーにできている。
  val map = Map(Point(10,10) -> 1, Point(20,20) -> 2)
  println(s"map(Point(10,10)): ${map(Point(10,10))}")
  println(s"map(Point(10,10)): ${map(Point(20,20))}")

  // match式で扱うこともできるようになる。
  val p = Point(1,2)
  p match {
    case Point(x, y) =>
      println(x)
      println(y)
  }
  // ケースクラスはメソッドを持たず値を持ち運ぶだけのオブジェクトを精製する時に使われる(Value Object)。
}

// 通常のクラス定義では下記の問題が出る。
// ①MapのキーにするためにhashCodeメソッドとequalsメソッドの定義が必要になる。
// ②toStringメソッドの結果が見にくい。
// ③newでインスタンスかする必要がある。

// これを定義した場合は下記のようになる。
//class Point(val x:Int, val y: Int) {
//  override def hashCode(): Int = x + y
//  override def equals(that: Any): Boolean = that match {
//    case that: Point => x == that.x && y == that.y
//    case _ => false
//  }
//  override def toString(): String = "Point(" + x + "," + y + ")"
//}
//
//object Point {
//  def apply(x: Int, y: Int): Point = new Point(x,y)
//}

// 上記と同じ内容を下記のように記載することができる。
case class Point(x: Int, y: Int)