// scalaにはコンパイラによって特別扱いされるメソッドがある。


object Chapter2_5 extends App {
  // apply
  // オブジェクトに対する関数呼び出しのようなことを行う際に、自動的にapplyメソッドが呼ばれる。
  println(s"add(1,2):${add(1,2)}")
  println(s"add.apply(1,2):${add.apply(1,2)}")

  // update
  val x = new Array[Int](10)
  // 参照(applyが呼ばれる)
  println("参照:" + x(0))
  // 代入(updateが呼ばれる)
  x(0) = 1
  println("代入1:" + x(0))
  x.update(0, 1)
  println("代入2:" + x(0))

  // unary_
  // +,-,!,~だけ特別扱いしてメソッド化できる。
  // 例えば!trueはunary_!を利用している。
  // また、+1や-2の符号もunary_+,unary_-を利用している。
  val s = new MyString("Taro")
  println(!s)
}

object add {
  def apply(x: Int, y: Int): Int = x + y
}

class MyString(val content: String) {
  def unary_! = "!" + content
}