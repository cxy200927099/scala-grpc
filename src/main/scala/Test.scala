import java.text.SimpleDateFormat
import java.util.Date

object TestScala {

  def formatDateForBlogPages(date: Long): String = {
    val d = new Date(date * 1000L)
    new SimpleDateFormat("LLLL d, yyyy").format(d)
  }

  def formatDateForSitemap(date: Long): String = {
    val d = new Date(date * 1000L)
    new SimpleDateFormat("yyyy-MM-d").format(d)
  }

  def main(args: Array[String]): Unit = {
    val str1 = formatDateForBlogPages(System.currentTimeMillis())
    val str2 = formatDateForSitemap(System.currentTimeMillis())
    println(str1)
    println(str2)
    println(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()))
    println(new StringBuilder().append("text_").append("cxy-").append(13).append(".jar"))
  }

}
