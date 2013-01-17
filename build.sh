#!/bin/bash

## build script

CLASSDIR=classes
SRCDIR=src
CLASSPATH=.
LIBDIR=lib

# build classpath
pushd $LIBDIR
for lib in `ls *.jar`; do
  CLASSPATH=${CLASSPATH}:${LIBDIR}/${lib}
done
popd

if [[ "$OSTYPE" == "cygwin" ]]; then
  CLASSPATH=`cygpath -wp $CLASSPATH`
fi

javac $1 -cp $CLASSPATH -d $CLASSDIR $SRCDIR/gmbrain/*.java

