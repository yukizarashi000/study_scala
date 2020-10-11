import scala.language.implicitConversions

// 暗黙の型変換
// ある型に対して別の方が適合しなかった場合に、型の変換処理をユーザが定義できる機能
// implicit def メソッド名(引数名: 引数の型): 戻り値の型 = 式
object Chapter2_12 extends App {
  implicit def intToBoolean(n: Int): Boolean = n != 0
  // 互換性のない型（if文に数値）が渡された時、
  // 暗黙的にintToBooleanが呼び出される。
  // ※非推奨。
  if(1) {
    println("1 is true")
  }

  class RichInt(val self: Int) {
    def isPositive: Boolean = self > 0
  }
  implicit def enrichInt(self: Int):RichInt = new RichInt(self)
  // 存在しないメソッド（1.isPositiveはIntクラスに定義されていない）が呼び出された時、
  // 暗黙の型変換が定義されていれば適用される。
  // 今はこの使い方が一般的。
  // 「enrich my liberaryパターン」
  println(1.isPositive)
}

