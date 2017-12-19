# Compiler for the FHNW module cpib (Building a compiler) [![Build Status](https://travis-ci.org/swaechter/fhnw-cpib.svg?branch=master)](https://travis-ci.org/swaechter/fhnw-cpib)

## Introduction

This is our IML compiler for the module cpib (Building a compiler) that
is written in Java.

| Module name           | Content and responsibilities                                                              |
| ----------------------|------------------------------------------------------------------------------------------ |
| fhnw-cpib-compiler    | Standalone application that can compile IML code to byte code                             |
| fhnw-cpib-interpreter | Standalone application that can interpret byte code and execute it in the virtual machine |
| fhnw-cpib-platform    | Platform that provides the whole compiler as a library                                    |
| fhnw-cpib-vm          | Library that provides the virtual machine from Mr. Edgar Lederer (FHNW)                   |

## Build

Setup your IDE:

    Install the EditorConfig plugin in IntelliJ IDEA

Checkout the repository:

    git clone https://github.com/swaechter/fhnw-cpib
    cd fhnw-cpib

Build the project:

    mvn package

Execute some IML code:

    java -jar fhnw-cpib-compiler/target/fhnw-cpib-compiler-0.1.0-jar-with-dependencies.jar fhnw-cpib-platform/src/test/resources/Team/Program1.iml

Copy the result and run it in the interpreter:

     java -jar fhnw-cpib-interpreter/target/fhnw-cpib-interpreter-0.1.0-jar-with-dependencies.jar

    Enter code:
     <Insert the byte code from the compiler>
 
## License

The project is licensed under the GNU LGPL v3 or later.

## Contact

Feel free to contact Simon Wächter (simon.waechter@students.fhnw.ch)
or Tom Ohme (tom.ohme@students.fhnw.ch).
