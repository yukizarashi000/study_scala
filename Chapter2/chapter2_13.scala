// 新しいメソッド定義を追加するためには
// 新しいクラスの生成
// 既存クラスから新しいクラスへの暗黙の型変換
// の両方を行う必要があった。
// これをまとめるために暗黙クラスが存在する。
// クラス名の前にimplicitを付与することで実現できる。
// 暗黙クラスはトップレベルに描けないので、オブジェクトやパッケージの中に書くようにする。
object Chapter2_13 extends App {
  if(1.isPositive) {
    println("1 is true")
  }
  implicit class RichInt(val self: Int) {
    def isPositive: Boolean = self > 0
  }
}