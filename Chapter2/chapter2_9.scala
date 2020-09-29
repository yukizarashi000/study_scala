// 型をパラメータとして与えることで、コンテナの要素型を抽象化する機能をジェネリクスという。
// class クラス名[型パラメータ1, 型パラメータ2, ...](クラスパラメータ){ }

object Chapter2_9 extends App {
  println("==================================")
  val cell = new Cell[String]("Hello")
  println(cell.get)
  cell.put("World")
  println(cell.get)
  println("==================================")

//  val cellInt = new Cell[Int]("Hello") // エラー
  val cellInt = new Cell[Int](1)
  println(cellInt.get)
  cellInt.put(2)
  println(cellInt.get)
  println("==================================")

}

// 型パラメータのどちらかがサブタイプである時（Cell[Any]とCell[Int]）でも、サブタイプの関係にはならない。
// ただし、変位指定でどちらかをどちらかのサブタイプと扱うこともできる。
class Cell[A](var value: A) {
  def put(newValue: A): Unit = {
    value = newValue
  }
  def get: A = value
}