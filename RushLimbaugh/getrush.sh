#
# uses "wget" and "perl" to get and extract
# RSS feed from Rush Limbaugh website
#
# needs "rush1.pl" Perl script
# needs Internet connection, to use "wget" command
#
# creates temp file "rushx"
# creates temp file "rushcontent.html"
# creates final output file "rushfinal.html"
#
# output is VERY SIMPLE HTML for offline viewing
#
wget -O rushx https://www.rushlimbaugh.com/feed/
perl rush1.pl <rushx|sh
# rushcontent.html is the conglomeration of ALL the HTML
#### THEN, use grep to extract every line
#### with <p>. You have quite a readable product
grep '<p>' rushcontent.html >rushfinal.html
