# Compiler for the FHNW module cpib (Building a compiler) [![Build Status](https://travis-ci.org/swaechter/fhnw-cpib.svg?branch=master)](https://travis-ci.org/swaechter/fhnw-cpib)

## Introduction

This is our IML compiler for the module cpib (Building a compiler) that
is written in Java.

| Module name           | Content and responsibilities                                  |
| ----------------------|-------------------------------------------------------------- |
| fhnw-cpib-compiler    | Standalone application that can compile IML code to byte code |
| fhnw-cpib-jasmin      | Jasmin assembler that will generate the Java byte code        |
| fhnw-cpib-platform    | Platform that provides the whole compiler as a library        |

## Build

Setup your IDE:

    Install the EditorConfig plugin in IntelliJ IDEA

Checkout the repository:

    git clone https://github.com/swaechter/fhnw-cpib
    cd fhnw-cpib

Build the project:

    mvn package

Create an IML program `HiAndBye.iml`:

    program HiAndBye()
    global
        x:int32
    do
        debugin x init;
        debugout x
    endprogram

Execute some IML code:

    java -jar fhnw-cpib-compiler/target/fhnw-cpib-compiler-0.1.0-jar-with-dependencies.jar HiAndBye.iml

The IML program is always executed, but can also be rerun as generated JAR file:

    java -jar HiAndBye.jar

## License

The project is licensed under the GNU LGPL v3 or later.

## Contact

Feel free to contact Simon Wächter (simon.waechter@students.fhnw.ch)
or Tom Ohme (tom.ohme@students.fhnw.ch).
