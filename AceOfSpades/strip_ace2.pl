#
# reading the already filtered HTML output of the "ace" extractor
# this is the 2nd stage after the comments extractor
#
# ace stripper (more)
#
# ONLY retain items between certain markers
#
$state = 0;
while (<>)
{
	if ($state == 0)
	{
		# looking for starter
		if (/<.td.*div.*blog/)
		{
			$state = 1;
			# ignore content
		}
	}
	else
	{
		if ($state == 1)
		{
			if (/div.*posted/)
			{
				$state = 0;
				# ignore content
			}
			else
			{
				print; ## pass through
			}
		} # end state 1
		else
		{
			# not state 1 or 2 wha?
		}
	} # end not state 0
}
