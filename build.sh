#!/bin/bash

## build script

CLASSDIR=classes
SRCDIR=src

javac $1 -d $CLASSDIR $SRCDIR/gmbrain/*.java

