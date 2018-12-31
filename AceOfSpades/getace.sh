#
# get RDF file from ace stream
#   (temp file "acer" is created)
# use Perl to extract message numbers
# then use wget to extract each message as HTML
# content is combined onto the file "all_ace.html"
# this file is huge and contains vast numbers of
# comments. If you like Ace's comment public, enjoy
# the last line uses another Perl script to filter
# out the comments
# final output is "ace_stripped.html"
# 
# you need the "wget" command
# you need the "perl" command
#
wget -O acer http://ace.mu.nu/index.rdf
perl ace.pl <acer |sh
# all_ace.html has everything, strip comments
perl strip_ace_comments.pl <all_ace.html >ace_stripped.html
perl strip_ace2.pl <ace_stripped.html >ace_final.html
