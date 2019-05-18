package w11w12;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.Normalizer;
import java.text.Normalizer.Form;

public class XSSFixed {

	public static void main(String args[]) {

			// Find an input 'skipped' which was not 'XSS' here
            String s = "<scri¥pt>";
			// assume "s" is the input that may be susceptible to XSS attacks
			//String s = "<script>";
			//String s = "<script> alert('hey there, you have been hacked') </script> ";
//			String s = "\uFE64" + "script" + "\uFE65";

            // Deletes all non-valid characters
            s = s.replaceAll("[^\\p{ASCII}]", "");

			s = Normalizer.normalize(s, Form.NFKC);
			Pattern pattern = Pattern.compile("<script>");
			Matcher matcher = pattern.matcher(s);
			if (matcher.find()) {
				System.out.println("blacklisted tag");
			} else {
				// . . .
                System.out.println("passed: " + s);
			}

            // Find an input 'skipped' which was 'XSS' here
            System.out.println("final: " + s);
	}
}
