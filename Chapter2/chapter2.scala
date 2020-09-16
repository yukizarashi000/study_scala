// Any 全ての型のスーパータイプ
// AnyVal 値型のスーパータイプ
//// Byte 1バイト符号付き整数
//// Short 2バイト符号付き整数
//// Char UnicodeのU+0000からU+FFFFまでを表す型
//// Int 4バイト符号付き整数
//// Long 8バイト符号付き整数
//// Float 4バイト浮動小数点数
//// Double 8バイト浮動小数点数
//// Boolean 真偽値
//// Unit 返すものがないことを表す型
// AnyRef 参照型のスーパータイプ
//// StringやList、ユーザーの定義したクラス
//// Null 全ての参照型のサブタイプ
// Nothing 全ての型のサブタイプ

object Chapter2 extends App {
  println("## Int型")
  println("1 + 2 = " + (1 + 2))
  println("------------------------------------------")
  println("## Long型")
  println("1L + 2L = " + (1L + 2L))
  println("------------------------------------------")
  println("## Byte型")
  val a: Byte = -128
  val b: Byte = 127
  val result1 = a + b
  println("a + b = " + result1)
  println("Byte? :" + result1.isInstanceOf[Byte])
  println("Int? :" + result1.isInstanceOf[Int])
  println("------------------------------------------")
  println("## Short型")
  val c: Short = 32767
  val d: Short = -32768
  val result2 = c + d
  println("c + d = " + result2)
  println("Short? :" + result2.isInstanceOf[Short])
  println("Int? :" + result2.isInstanceOf[Int])
  println("------------------------------------------")
  println("## Char型")
  println("\\n: " + '\n')
  println("\\u0022: " + '\u0022')
  println("------------------------------------------")
  println("## Double型")
  println("1.0")
  println("Double? :" + 1.0.isInstanceOf[Double])
  println("Float? :" + 1.0.isInstanceOf[Float])
  println("Int? :" + 1.0.isInstanceOf[Int])
  println("1.0d")
  println("Double? :" + 1.0d.isInstanceOf[Double])
  println("Float? :" + 1.0d.isInstanceOf[Float])
  println("Int? :" + 1.0d.isInstanceOf[Int])
  println("------------------------------------------")
  println("## Float型")
  println("1.0f")
  println("Double? :" + 1.0f.isInstanceOf[Double])
  println("Float? :" + 1.0f.isInstanceOf[Float])
  println("Int? :" + 1.0f.isInstanceOf[Int])
  println("------------------------------------------")
  println("## Boolean型")
  println(true)
  println(false)
  println(true && true)
  println(true || false)
  println("------------------------------------------")
  println("## Unit型")
  def puts(value: String):Unit = {
    println(value)
  }
  puts("Hello World")
  println(())
  println("Unit? :" + ().isInstanceOf[Unit])
  println("------------------------------------------")
  println("## Null型")
  // ScalaでNull型は（あるけど）ほぼ使わない（Javaのnullだけを値にもつ）
  val x: Null = null
  println(x)
  println("------------------------------------------")
  println("## Nothing型")
  // Nothingは値を持たず、例外を投げたりメソッドからreturnして処理を中断したりするのに使う。
  // また、実装していないメソッドに対してとりあえず値を与える時にも使える。
  // ???はNothing型を返し、Nothing型はInt型のサブタイプでもあるので、Int型を返すメソッドにも適合する。
  // （コンパイルは通るが実行時例外は発生する）
  //  def add(x: Int, y: Int):Int = ???
  //  add(1,2)

  // ある条件時に例外を投げる場合、Int型と例外のNothing型は共通の親であるInt型を持つので型が適合する。
  def requirePositive(n: Int):Int = if(n > 0) n else throw new IllegalArgumentException("n must be positive.")
  println("1:" + requirePositive(1))
  //  println("-1:" + requirePositive(-1))

  // ジェネリクスと組み合わせて空のコレクションとして使うこともできる。
  // NilはList[Nothing]を継承している。



}