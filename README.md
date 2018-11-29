# RSSScripts
Various Linux/Unix shell scripts used for RSS feeds. 

Yes, there are programs for reading feeds, but these are quick, run from the command line, 
and produce readable feeds that can be viewed offline. 

The reason for this set of scripts was both the complexity of certain types of RSS feeds,
as well as the desire to read offline. 

Just as an example, I run a script and then move the resulting HTML file
to my phone for reading at leisure.

Each script contains its own simple documentation, and all of them
use readily-available Linux/Unix commands, such as "wget".

In order for the resulting HTML content to be simple, there
are Perl scripts that filter out a lot of junk from each
feed.

At this point, we have scripts, and supporting Perl code for:

1) getrush.sh -- extract HTML from Rush Limbaugh RSS feed 

2) getace.sh -- extract HTML from Ace of Spades RSS feed
