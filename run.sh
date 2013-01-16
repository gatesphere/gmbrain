#!/bin/bash

## run script

CLASSDIR=classes
MAINCLASS=gmbrain.GMBrain

pushd $CLASSDIR
java $MAINCLASS
popd

