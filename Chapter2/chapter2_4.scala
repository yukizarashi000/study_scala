// staticは対象となるPBウジェクトが存在しないが何らかの手続きを定義したい場合に
// それをクラスに所属するメソッドとして定義することをいう。
// scalaにstaticは存在しないのでobjectとして定義する。
object Foo {
  // このオブジェクト内でしか使えず、シングルトン内にあるので変更もできない？
  def foo(): Unit = {
    println("foo")
  }
}