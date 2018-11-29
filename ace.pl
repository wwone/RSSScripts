# Perl script will extract message numbers from
# output of "wget" RDF stream
#
# output is a shell script that invokes
# "wget" to combine all postings for Ace of Spades
#
print "wget -O all_ace.html ";
while (<>)
{
	if (/rdf.li.rdf.resource=/)
	{
		@items = split/\"/;
		@url = split /=/, $items[1];
		print " http://minx.cc:1080?post=$url[1] "
	}
}
print "\n";
