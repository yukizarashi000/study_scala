// 式: 評価して値になるもの
// ex) 1 + 1, 1.0 + 2.0

// 文: 評価しても値にならないもの
// ex) val a = 1

object Chapter2_7 extends App {
  val ab = if(3 < 4) "a" else "b"
  println(ab)
  // Scalaでは式の評価結果が戻り値になるのでreturnがいらない。
  println("---------------------------------------")

  // ブロック式
  // {式1; 式2; ... 式N}
  // 式Nを評価した結果を返す。
  println({
    val a = 1 + 1
    a + 2
  })
  println("---------------------------------------")

  // if文も何かしらの値を返す。
  // elseを省略した場合はUnit型の値を返す。
  // 返す値がない時もUnit型の値を返すらしい。
  {
    val a = 1
    if(a > 0) {
      println(a)
    } else {
      println(0)
    }
  }
  println("---------------------------------------")

  // while式も値を返すが、返すべき値がないのでUnit型の値を返している。
  {
    var i = 0
    while(i <= 10) {
      println("i = " + i)
      i = i + 1
    }
  }
  println("---------------------------------------")

  // for式
  // for {ジェネレータ1; ジェネレータ2; ... ジェネレータN} 本体式
  // ジェネレータは　変数名 <- 式　で定義される。
  // 式1から式Nには範囲を表す式が使える。
  // 右に行くほど内部ループになる。
  for (x <- 1 to 3; y <- 1 until 3) {
    println("x = " + x + " y = " + y)
  }
  println("=======================================")

  // 絞り込みもできる。
  for (x <- 1 to 3; y <- 1 until 3 if x != y) {
    println("x = " + x + " y = " + y)
  }
  println("=======================================")

  // ジェネレータにコレクションも指定できる。
  for (e <- List(1,2,3)) println(e)
  println("=======================================")

  // 要素を加工してコレクションを新たに生成もできる。
  println(for (e <- List(1,2,3)) yield {
    e + 1
  })
  println("---------------------------------------")

  // パターンマッチ
  // ①値比較による分岐
  println({
    val i = 5
    i match {
      case 0 => "A"
      case 1 => "B"
      case 2 => "C"
      case _ => "?"
    }
  })
  println("=======================================")

  // ②データの分解と取り出し
  // 上から順にマッチし、最初にマッチしたものが選ばれる。
  {
    val list = List(1,2,3,4,5)
    list match {
      case List(1,2,3,5,4) => println("1,2,3,5,4")
      case List(a,b,c,d,e) => println(a,b,c,d,e)
      case _ => println("?")
    }
  }
  println("=======================================")

  // ③再帰関数との組み合わせ
  // x::xs => x:head（先頭要素）とxs:tail（それ以外の要素群）に分ける。
  // コレクションなら必ずマッチする。
  def reverse[A](list: List[A], result: List[A]):List[A] = list match {
    case x::xs => reverse(xs, x::result)
    case Nil => result
  }
  println(reverse(List(1,2,3,4,5), Nil))
  println("=======================================")

  // ④ガード式
  // caseの条件 + 追加の条件を満たす場合に式に移行するようにできる。
  println({
    val list = List("A", "B", "C", "D", "E")
    list match {
      case List("A", b, c, d, e) if b != "B" =>
        println("b = " + b)
        println("c = " + c)
        println("d = " + d)
        println("e = " + e)
      case _ => println("nothing")
    }
  })
  println("=======================================")

  // ⑤パターンのネスト
  def last2[A](list: List[A]):A = list match {
    case x::_::Nil => x
    case x::xs => last2(xs)
    case _ => sys.error("list should have 2 elements at least")
  }
  println(last2(List(1,2,3)))
//  last2(List(1)) // error
  println("=======================================")

  // パターンには下記を指定できる。
  // i. リテラル => 定数
  // ii. 変数 => 小文字で始まるパターンは全てのケースにマッチする。
  // iii. コンストラクタ => ::(1 :: (2 :: Nil))や::(a,b)後者は変数パターンも内在している。
  // iV. イクストラクタ => コンストラクタパターンと同じだが、パターンの名前部分がケースクラス以外を指している場合。List(a,b,c,d,e)など。
  // V. 中置 => x::xsやx::y::xsなど、パターンを識別する記号が真ん中に来ている場合。
  // vi. パターン選択 => 1 | 2など複数のパターンにマッチする場合。
  // vii. 型 => x: Intなど型の一致を確認する場合。

  // throw式
  // JavaのThrowableクラスを継承しているインスタンスなら何でも投げられる。

  // try式
  try {
    throw new RuntimeException("実行時例外")
  } catch {
    case e: Exception => println(e.getMessage)
  }
  println("=======================================")

  // ローカルメソッド
  // オブジェクト直下に定義されているのではないメソッドをローカルメソッドという。
  def factorial(n: Int): Int = {
    def f(m: Int, x: Int): Int = if(m == 0) {
      x
    } else {
      f(m - 1, x * m)
    }
    f(n, 1)
  }
  println(factorial(2))
  println("=======================================")





}