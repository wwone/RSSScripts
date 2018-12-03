#
# get RSS stream for the Online Photographer blog
#
# this script needs the "wget" command, as well as the
# "java" runtime
#
# following creates a file "photox"
#
wget -O photox http://theonlinephotographer.typepad.com/the_online_photographer/atom.xml


JAVA=java
echo "- - - - - - -"
echo "- - - - - - -"
echo "- - - - - - -"
echo "- - - - - - -"

CP=$CP:.

OBJ=ReadXMLDOM

#
# following Java program filters the RSS information
#
# it needs the "ReadXMLDOM.class" file to be present in this directory
# and expects there to be a "java" runtime
#
# it writes the "photog.html" filtered output
#

echo "$JAVA" -classpath "$CP" $OBJ FE
"$JAVA" -classpath "$CP" $OBJ  FE <photox  >photog.html
