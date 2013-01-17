#!/bin/bash

## run script

CLASSDIR=classes
MAINCLASS=gmbrain.GMBrain
LIBDIR=lib
CLASSPATH=.

# build classpath
pushd $LIBDIR
for lib in `ls *.jar`; do
  CLASSPATH=${CLASSPATH}:../${LIBDIR}/${lib}
done
popd

if [[ "$OSTYPE" == "cygwin" ]]; then
  CLASSPATH=`cygpath -wp $CLASSPATH`
fi

pushd $CLASSDIR
java -cp $CLASSPATH $MAINCLASS
popd

