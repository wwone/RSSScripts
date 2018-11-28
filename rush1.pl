#
# reads stdin, looking for markers in the RSS stream
#
# writes a shell script to stdout
#
# output shell script uses "wget" command
#
# when created shell script is executed, it will
# create a file "rushcontent.html" with all of
# the stream content. This stream needs further
# filtering to become a simple HTML file for
# offline reading
#
print "wget -O rushcontent.html ";
while (<>)
{
	if(/\"https.*daily/)
	{
		## interesting line
		@groups = split /\"/;
		print " $groups[3] "; ## URL
		##print $groups[3] . "\n";
	}
}
print "\n";
