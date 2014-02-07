dmm4s
=============

This is a "DMM Web API" client library for Scala.
[https://affiliate.dmm.com/api/](https://affiliate.dmm.com/api/)

[![Build Status](https://travis-ci.org/daiksy/dmm4s.png?branch=master)](https://travis-ci.org/daiksy/dmm4s)
[![Coverage Status](https://coveralls.io/repos/daiksy/dmm4s/badge.png?branch=master)](https://coveralls.io/r/daiksy/dmm4s?branch=master)

supported scala version is ```2.10.x```

# Install.

Edit file `project/Build.scala` or `build.sbt`

```
libraryDependencies ++= Seq(
"com.github.daiksy" %% "dmm4s" % "0.1-SNAPSHOT"
),
resolvers ++= Seq(
"snapshot" at "http://oss.sonatype.org/content/repositories/snapshots"
)
```

# License

Apache License, Version 2.0

Copyright 2014 Daisuke Kasuya [@daiksy](https://twitter.com/daiksy)