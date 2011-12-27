import java.io._

def allDirectoriesOf(dir: File): Stream[File] = 
  dir #:: dir.listFiles().toStream.filter{_.isDirectory()}.flatMap(allDirectoriesOf)
  
def updateIndex(dir: File) = {
  val html = <html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>akihiro4chawon.github.com</title>
  </head>
  <body>
  <h1>Repository Contents</h1>
    <ul>{ for {
      file <- dir.listFiles()
      fileName = file.getName() if fileName != "index.html"
      link = <a href={fileName}>{fileName}</a>
    } yield <li>{link}</li>}</ul>
  </body>
  </html>
  
  println("processing directory: "+dir)
  // Should I close FileOutputStream? :-p
  new FileOutputStream(new File(dir, "index.html")).write(html.toString.getBytes)
}

allDirectoriesOf(new File("maven-repo")) foreach updateIndex

