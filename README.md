# navparser 
[![Build Status](https://travis-ci.org/dinsaw/navparser.svg?branch=master)](https://travis-ci.org/dinsaw/navparser)
[![codecov](https://codecov.io/gh/dinsaw/navparser/branch/master/graph/badge.svg)](https://codecov.io/gh/dinsaw/navparser)

Parser for mutual fund net asset values.

## Supported files
|Source|Parser|
|---|---|
|https://www.amfiindia.com/spages/NAVAll.txt|`com.github.dinsaw.navparser.india.AmfiIndiaNavParser`

## How to use?

### Gradle
``` Groovy
repositories {
  jcenter()
}

dependencies {
  compile 'com.github.dinsaw:navparser:0.5'
}
```


## How to publish to JCenter
- export BINTRAY_USER=<Your Bintray user name>
- export BINTRAY_KEY=<Your Bintray API Key>
- gradle clean build bintrayUpload