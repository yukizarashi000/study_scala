object Chapter2_11 extends App {
  // 既存クラスを拡張したクラスを一時的に利用したい場合、無名クラスとして定義できる。
  new Thread {
    override def run(): Unit = {
      for(i <- 1 to 10) println(i)
    }
  }.start()
}