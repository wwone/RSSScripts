#
# get RSS feed for the ZMan
#
# Need the "wget" command, and runtime for Java
#
# use "wget" to receive feed. The file "zman" will be created.

wget -O zman http://thezman.com/wordpress/?feed=rss2

JAVA=java
echo "- - - - - - -"
echo "- - - - - - -"
echo "- - - - - - -"
echo "- - - - - - -"


CP=$CP:.

OBJ=ReadXMLDOM

#
# java program will read the "zman" file and create
# "zman.html"
#
# the runtime file "ReadXMLDOM.class" must be present in this
# directory
#

echo "$JAVA" -classpath "$CP" $OBJ CI
"$JAVA" -classpath "$CP" $OBJ CI  <zman  >zman.html

