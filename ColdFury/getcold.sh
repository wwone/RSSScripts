#
# using "wget" get XML from cold fury feed
# assume that "wget" is available
#
# creates file "tempcold.xml"
#
wget -O tempcold.xml http://coldfury.com/index.php/?feed=rss2

JAVA=java
echo "- - - - - - -"
echo "- - - - - - -"
echo "- - - - - - -"
echo "- - - - - - -"


CP=$CP:.

OBJ=ReadXMLDOM

#
# Java program filters the XML and makes a readable
# HTML file
#
# depends on the presence in this directory of the
# "ReadXMLDOM.class" file, and the "java" runtime.
#
# the class file mentioned is available in the
# "ChicagoBoyz" folder, or you can compile the
# Java source from the "java" folder
#
# creates the file "cold.html" as offline HTML
#


echo "$JAVA" -classpath "$CP" $OBJ CI
"$JAVA" -classpath "$CP" $OBJ  CI <tempcold.xml >cold.html

