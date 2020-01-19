// add sbt-protoc plugin,this maybe error with "could not find xxx.pom form marven"
// should add sbt-plugin-repo:
addSbtPlugin("com.thesamet" % "sbt-protoc"  % "0.99.25")

// 
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")

libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.9.0"
//resolvers += "dnvriend" at "https://dl.bintray.com/dnvriend/maven"

//resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
//resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

//addSbtPlugin("com.thesamet" % "sbt-protoc" % "0.99.18")
//
//libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.7.1"