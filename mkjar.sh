#!/bin/bash

## jar script

CLASSDIR=classes
MANIFEST=META-INF/MANIFEST.MF

pushd $CLASSDIR
jar -cvfm ../GMBrain.jar $MANIFEST ./*
popd

