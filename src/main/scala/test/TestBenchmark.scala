package test

import org.openjdk.jmh.annotations.Benchmark

import scala.io.Source

class TestBenchmark {
  import TestBenchmark._

  def implFold(x: Option[Char]) = x.fold(false)(_.isLower)

  def implPatMat(x: Option[Char]) = x match {
    case Some(c) => c.isLower
    case None    => false
  }

  @Benchmark def foldSome(): Boolean = implFold(someChar)
  @Benchmark def foldNone(): Boolean = implFold(noneChar)

  @Benchmark def patMatSome(): Boolean = implPatMat(someChar)
  @Benchmark def patMatNone(): Boolean = implPatMat(noneChar)
}

object TestBenchmark {
  val someChar: Option[Char] = Some('c')
  val noneChar: Option[Char] = None
}
