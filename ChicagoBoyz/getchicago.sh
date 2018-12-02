#
# first, get XML from chicagoboyz feed
#
wget -O tempc.xml http://chicagoboyz.net/feed

JAVA=java
echo "- - - - - - -"
echo "- - - - - - -"
echo "- - - - - - -"
echo "- - - - - - -"

CP=$CP:.

OBJ=ReadXMLDOM

## input is stdin which is the output of
##  wget above: "tempc.xml"
##
## output is "chitest.html"

echo "$JAVA" -classpath "$CP" $OBJ CI
"$JAVA" -classpath "$CP" $OBJ  CI <tempc.xml >chitest.html

