// Scalaでは自分で型を定義する場合は以下の2通りが挙げられる。
// クラス
// トレイト

// クラスは以下の要素を持つ必要がある。
// フィールド
// メソッド
// コンストラクタ

object Chapter2_2 extends App {
  val p = new Point(10,20)
  println(p.x)
  println(p.y)
  val shape1 = new Triangle()
  shape1.draw()
  val shape2 = new Rectangle()
  shape2.draw()
  val shape3 = new UnknownShape()
  shape3.draw()
}

// クラスPointの定義
// フィールドはx,yを最低限持つ
// コンストラクタの引数はx,y
class Point(val x: Int, val y: Int) {

}

// メソッドはクラス内関数
// クラスパラメータからvalを取り除くとフィールドは宣言されなくなる。
// var では初期値の式に_を書くことで型独自のデフォルト値を設定できる。

abstract class Shape {
  def draw(): Unit = {
    println("不定形")
  }
}

class Triangle extends Shape {
  override def draw(): Unit = {
    println("三角形")
  }
}

class Rectangle extends Shape {
  override def draw(): Unit = {
    println("四角形")
  }
}

class UnknownShape extends Shape