# reading the HTML output of the "ace" extractor,
# remove only stuff between <div id="ace_comments" and its end
# </div>. Leave the rest of the content. This requires that we
#  keep track of all embedded DIV's!!
#
$state = 0;
$divcount = 0;
while (<>)
{
	if ($state == 0)
	{
		# looking for <div id="ace_comments"
		if (/\<div id=\"ace_comments\"/)
		{
			$divcount++;
			$result = $_;
			$state = 1;
		}
		else
		{
			## else, print it
			print;
		}
	} ## end state 0
	
	else
	{
		if ($state == 1)
		{
## look for </div, but include all embedded <div>s			if (/\<\/content/) # end of tag
##			{
				## if see div, push it in
				if (/\<div/)
				{
					$divcount++;
				}
				## if see /div, pop it up
				if (/\<\/div/)
				{
					$divcount--;
				}
				$result = $_;
				if ($divcount == 0)
				{
					## hit end of comments
					$state = 0; ## start looking
				}
		} ## end state 1
	}
}
