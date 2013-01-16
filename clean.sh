#!/bin/bash

## clean script

CLASSDIR=classes/gmbrain
SRCDIR=src/gmbrain

rm $CLASSDIR/*.class
rm $CLASSDIR/../*.ini
rm *.jar
chmod -x $SRCDIR/*.java
