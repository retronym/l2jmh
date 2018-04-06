package test

import org.openjdk.jmh.annotations.Benchmark

class TestBenchmark {
  import TestBenchmark._

  def implFold(x: Option[Char]) = {
    x.fold(false)(Character.isLowerCase)
    x.fold(true)(Character.isUpperCase)
    x.fold(hashCode() > 0)(_ => true)
    x.fold(hashCode() < 0)(_ => false)
  }

  def implPatMat(x: Option[Char]) = {
    x match {
      case Some(c) => Character.isLowerCase(c)
      case None => false
    }
    x match {
      case Some(c) => Character.isUpperCase(c)
      case None => false
    }
    x match {
      case Some(c) => true
      case None => hashCode() > 0
    }
    x match {
      case Some(c) => false
      case None => hashCode() < 0
    }
  }
  def implPatMat2(x: Option[Char]) = x match {
    case None => false
    case Some(c) => Character.isLowerCase(c)
  }

  @Benchmark def foldSome(): Boolean = implFold(someChar)
  @Benchmark def foldNone(): Boolean = implFold(noneChar)

  @Benchmark def patMatSome(): Boolean = implPatMat(someChar)
  @Benchmark def patMatNone(): Boolean = implPatMat(noneChar)

  @Benchmark def patMatSome2(): Boolean = implPatMat2(someChar)
  @Benchmark def patMatNone2(): Boolean = implPatMat2(noneChar)
}

object TestBenchmark {
  val someChar: Option[Char] = Some('c')
  val noneChar: Option[Char] = None
}
