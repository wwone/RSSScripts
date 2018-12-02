# RSSScripts
Various Linux/Unix shell scripts used for RSS feeds. 

Yes, there are programs for reading feeds, but these are quick, run from the command line, 
and produce readable feeds that can be viewed offline. 

The reason for this set of scripts was both the complexity of certain types of RSS feeds,
as well as the desire to read the feed content offline. 

Just as an example, I run a script and then move the resulting HTML file
to my phone for reading at leisure.

Each script contains its own simple documentation, and all of them
use readily-available Linux/Unix commands, such as "wget".

In order for the resulting HTML content to be simple, there
are Perl scripts that filter out a lot of junk from each
feed. A new wrinkle is the presence of a Java program to
perform more complex extraction of RSS content.

At this point, we have scripts, and supporting Perl code for:

1) RushLimbaugh/getrush.sh -- extract HTML from Rush Limbaugh RSS feed 

2) AceOfSpades/getace.sh -- extract HTML from Ace of Spades RSS feed


I've added a Java program that walks the tree of DOM
output from some RSS feeds. Following scripts use that,
rather than simplified Perl. (These other feeds are just
too complex for dumbed-down Perl). The "java" folder contains
the source and compilation script. However, each working RSS
folder should contain the "ReadXMLDOM.class" execution file
for the program. Thus, if you don't have a Java compiler, but
do have a Java runtime, just use the "class" file supplied.

3) ChicagoBoyz/getchicago.sh -- extract HTML from ChicagoBoyz RSS feed
