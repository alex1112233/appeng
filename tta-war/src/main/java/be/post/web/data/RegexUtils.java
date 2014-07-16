package be.post.web.data;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

	public static String getFirstMatch(String strInput, String strPattern, int groupNum) {
		
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strInput);
		
		if (m.find()) {			
			System.out.println("Found a " + m.group(groupNum) + ".");
			return m.group(groupNum);
		}
		return null;
	}
}
