/**
  * Created by adarr on 2/8/2017.
  */


import scala.io.Source
import scala.languageFeature.postfixOps
import java.nio.file.{Files, Path, Paths}
import java.nio.charset.StandardCharsets

val fName = "feats_common"

val path = """C:\Users\adarr\git\ddo-calc\ddo-core\src\test\resources\org\aos\ddo\model\feat\feats_common.txt"""
val outPath = path.replace(fName, s"${fName}_sorted")
def data: Iterator[String] = Source.fromFile(path).getLines() map { l => s"| $l |\n" }

val sortedData = data.toList.sorted


val paths: Path = Paths.get(outPath)
Files.write(paths, sortedData.mkString.getBytes(StandardCharsets.UTF_8))