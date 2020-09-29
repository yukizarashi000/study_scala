object Chapter2_8 extends App {
  (new PublicPrinter(1)).print()
  println("===================================")
  //  (new PrivatePrinter(1)).print() // これはエラー

  val privatePrinter = new PrivatePrinter()
  // これはエラー
//  privatePrinter.print()
  // これはOK
  privatePrinter.limitedPrint()
  println("===================================")

  val privateThisPrinter = new PrivateThisPrinter()
  println("===================================")

  val Super = new Super()
  val Sub = new Sub()
  println("===================================")

  val c = new Circle(0, 0, 5)
  println(c.area)
  println(c.area)
  println("===================================")

  (new Concrete).foo
}

class PublicPrinter(x: Int) {
  // アクセス修飾子に特に何も指定しない場合publicになる。
  def print(): Unit = println(x)
}

class PrivatePrinter {
  // privateを指定した場合はそのクラスおよびトレイトないからのみアクセスできる。
  private def print(): Unit = println("private")
  def limitedPrint(): Unit = {
    println("limited print")
    print()
  }
  class Inner {
    private def print(): Unit = println("inner")
    class InnerMost {
      print()
    }
    new InnerMost
  }
  print() // 定義クラス内ではアクセス可能
//  (new Inner).print // インナークラスのprivateメソッドはアクセス不可
  new Inner // インナークラス内のインナークラスからは上位のprivateメソッドはアクセス可能
}

class PrivateThisPrinter {
  class Inner {
    def privatePrint(): Unit = println("private")
    private[this] def privateThisPrint(): Unit = println("private this")
    privateThisPrint() // これはOK
  }
  (new Inner).privatePrint()
//  (new Inner).privateThisPrint() // インナークラスのみアクセス可能なためエラー
}

class Super {
  // クラスそれ自体と継承先からのみアクセス可能
  protected def puts(message: String): Unit = {
    println(message)
  }
  println("親クラス")
  puts("super")
}

class Sub extends Super {
  def sub(): Unit = {
    puts("sub")
  }
  println("子クラス")
  sub()
}

class Circle(x: Int, y: Int, radius: Int) {
  // これだとインスタンス生成の度に計算が行われてしまう。
  //  val area: Double = radius * radius * math.Pi

  // 呼び出されたタイミングで初めて計算が行われるようになる。
  // また、2回目以降アクセス時は計算結果だけ呼ばれるようになる。
  lazy val area: Double = {
    println("面積を計算します")
    radius * radius * math.Pi
  }
}

class Final {
  // オーバーライドされたくない場合はfinalをつける。
  final def foo: Unit = println("final")
}

class FInalSub {
//  override final def foo: Unit = println("final sub") // エラー
}

abstract class Abstract {
  def foo: Unit
}

class Concrete extends Abstract {
  override def foo: Unit = println("concrete")
}