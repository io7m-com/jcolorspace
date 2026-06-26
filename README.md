jcolorspace
===

[![Maven Central](https://img.shields.io/maven-central/v/com.io7m.jcolorspace/com.io7m.jcolorspace.svg?style=flat-square)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.io7m.jcolorspace%22)
[![Maven Central (snapshot)](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fcentral.sonatype.com%2Frepository%2Fmaven-snapshots%2Fcom%2Fio7m%2Fjcolorspace%2Fcom.io7m.jcolorspace%2Fmaven-metadata.xml&style=flat-square)](https://central.sonatype.com/repository/maven-snapshots/com/io7m/jcolorspace/)
[![Codecov](https://img.shields.io/codecov/c/github/io7m-com/jcolorspace.svg?style=flat-square)](https://codecov.io/gh/io7m-com/jcolorspace)
![Java Version](https://img.shields.io/badge/21-java?label=java&color=e6c35c)

![com.io7m.jcolorspace](./src/site/resources/jcolorspace.jpg?raw=true)

| JVM | Platform | Status |
|-----|----------|--------|
| OpenJDK (Temurin) Current | Linux | [![Build (OpenJDK (Temurin) Current, Linux)](https://img.shields.io/github/actions/workflow/status/io7m-com/jcolorspace/main.linux.temurin.current.yml)](https://www.github.com/io7m-com/jcolorspace/actions?query=workflow%3Amain.linux.temurin.current)|
| OpenJDK (Temurin) LTS | Linux | [![Build (OpenJDK (Temurin) LTS, Linux)](https://img.shields.io/github/actions/workflow/status/io7m-com/jcolorspace/main.linux.temurin.lts.yml)](https://www.github.com/io7m-com/jcolorspace/actions?query=workflow%3Amain.linux.temurin.lts)|
| OpenJDK (Temurin) Current | Windows | [![Build (OpenJDK (Temurin) Current, Windows)](https://img.shields.io/github/actions/workflow/status/io7m-com/jcolorspace/main.windows.temurin.current.yml)](https://www.github.com/io7m-com/jcolorspace/actions?query=workflow%3Amain.windows.temurin.current)|
| OpenJDK (Temurin) LTS | Windows | [![Build (OpenJDK (Temurin) LTS, Windows)](https://img.shields.io/github/actions/workflow/status/io7m-com/jcolorspace/main.windows.temurin.lts.yml)](https://www.github.com/io7m-com/jcolorspace/actions?query=workflow%3Amain.windows.temurin.lts)|

## Repository Relocation

Development of this project has moved to an
[open-source but not open-contribution](https://sqlite.org/copyright.html#notopencontrib)
model.

Source code and commits will remain publicly available perpetually, but issues
and/or pull requests will be rejected and/or ignored. Additionally, this project
will now only be available via a read-only mirror at:

  https://codeberg.org/io7m-com/jcolorspace


## jcolorspace

The `jcolorspace` package provides a set of functions for converting between
color spaces.

## Features

* HSV/RGB conversion functions.
* Phantom-typed color vectors for static safety.
* High coverage test suite.
* [OSGi-ready](https://www.osgi.org/)
* [JPMS-ready](https://en.wikipedia.org/wiki/Java_Platform_Module_System)
* ISC license.

## Usage

Use the `HSV` and `RGB` classes to convert between color vectors in different
color spaces.

A [demo application](com.io7m.jcolorspace.demo/src/main/java/com/io7m/jcolorspace/demo/ColorDemoMain.java)
is included that demonstrates the results of color conversions.

![ColorDemo](./src/site/resources/colorDemo.png?raw=true)

