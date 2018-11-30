#
# compile the Java code for converting RSS to HTML
# this is a Linux/Unix shell script. We ASSUME
# that you have a working Java compiler and runtime
# recent enough for DOM handling
#

JAVAC=javac
echo "- - - - - - -"
echo "- - - - - - -"
echo "- - - - - - -"
echo "- - - - - - -"


CP=$CP:.

OBJ=ReadXMLDOM


echo "$JAVAC" -classpath "$CP" $OBJ.java
"$JAVAC" -classpath "$CP" $OBJ.java

