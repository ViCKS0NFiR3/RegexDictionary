import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
public class RegexDictionary {
	String pattern;
	Pattern p1;
	Matcher m1;
	boolean found1;
	//private String txt = "spite·ful\n/ˈspītfəl/\nLearn to pronounce\nadjective\nadjective: spiteful\nshowing or caused by malice.\n\"the teachers made spiteful little jokes about me\"\nsynonyms:	malicious, mean, nasty, cruel, unkind, unfriendly, snide, hurtful, wounding, barbed, cutting, hateful, ill-natured, bitter, venomous, poisonous, acid, hostile, rancorous, malevolent, evil-intentioned, baleful, vindictive, vengeful, vitriolic, vicious, splenetic, malign, malignant, bilious; More\nantonyms:	benevolent, kind, friendly";
	String txt = "";
        String csvFile = "C:\\Users\\apollo\\Documents\\Alpha and Omega\\JavaBoiz\\dictionary.tsv";
        BufferedReader br = null;
        String line = "";
        //String cvsSplitBy = ",";

        private void readCSV(){
            try{
                br = new BufferedReader(new FileReader(csvFile));
                while ((line = br.readLine()) != null) {
                // use comma as separator
                //String[] word = line.split(cvsSplitBy);
                //System.out.println(line);
                txt = txt.concat(line);
                //System.out.println(word[1]);
                }
            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        private void Match(String pattern){
        	try {
        		//PrintStream out = new PrintStream(System.out, true, "UTF-8");
        		Pattern p = Pattern.compile(pattern);
				Matcher m = p.matcher(txt);
				boolean found = m.find();
				System.out.println(m.group());
        	}
        	catch(Exception e){
        		System.out.println(e);
        	}
		}
		private boolean checkMatch(String pattern){
    		//PrintStream out = new PrintStream(System.out, true, "UTF-8");
    		Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(txt);
			boolean found = m.find();
			return found;
			//System.out.println(m.group());
		}

	public static void main (String[]args){
		RegexDictionary dr = new RegexDictionary();
                dr.readCSV();
                //System.out.println(dr.txt);
		dr.Match("([a-zA-Z·]+)");
		dr.Match("(\\/.+\\/)");
		dr.Match("(\\sLearn to pronounce)");
		dr.Match("(adjective|verb|noun)");
		dr.Match("(adjective: \\w+|verb: \\w+|noun: \\w+)");
		dr.Match("([a-zA-Z -]+\\.)");
		dr.Match("(\"[a-zA-Z ]+\")");
		if (dr.checkMatch("(synonyms:\\s[a-zA-z, -_]+)")){
			dr.Match("(synonyms:\\s[a-zA-z, -_]+)");
		}
		if (dr.checkMatch("(antonyms:\\s[a-zA-z, -_]+)")){
			dr.Match("(antonyms:\\s[a-zA-z, -_]+)");
		}
		//dr.Match("(antonyms:\\s[a-zA-z, -_]+)");
	  /*String pattern1 = ("(\\w+\\·\\w+\n)");
		String pattern2 = ("(\\/.+\\/)");
		String pattern3 = ("(Learn to pronounce\n)");
		String pattern4 = ("(adjective|verb)");
		String pattern5 = ("(adjective: \\w+|verb: \\w+)");
		String pattern6 = ("([a-zA-Z -]+\\.)");
		String pattern7 = ("(\"[a-zA-Z ]+\")");
		String pattern8 = ("(synonyms:\\s[a-zA-z, -_]+)");
		String pattern9 = ("(antonyms:\\s[a-zA-z, -_]+)");
		//System.out.println(pattern);
		String txt = "spite·ful\n/ˈspītfəl/\nLearn to pronounce\nadjective\nadjective: spiteful\nshowing or caused by malice.\n\"the teachers made spiteful little jokes about me\"\nsynonyms:	malicious, mean, nasty, cruel, unkind, unfriendly, snide, hurtful, wounding, barbed, cutting, hateful, ill-natured, bitter, venomous, poisonous, acid, hostile, rancorous, malevolent, evil-intentioned, baleful, vindictive, vengeful, vitriolic, vicious, splenetic, malign, malignant, bilious; More\nantonyms:	benevolent, kind, friendly";
		//System.out.println(txt);
		Pattern p1 = Pattern.compile(pattern1);
		Pattern p2 = Pattern.compile(pattern2);
		Pattern p3 = Pattern.compile(pattern3);
		Pattern p4 = Pattern.compile(pattern4);
		Pattern p5 = Pattern.compile(pattern5);
		Pattern p6 = Pattern.compile(pattern6);
		Pattern p7 = Pattern.compile(pattern7);
		Pattern p8 = Pattern.compile(pattern8);
		Pattern p9 = Pattern.compile(pattern9);
		Matcher m1 = p1.matcher(txt);
		Matcher m2 = p2.matcher(txt);
		Matcher m3 = p3.matcher(txt);
		Matcher m4 = p4.matcher(txt);
		Matcher m5 = p5.matcher(txt);
		Matcher m6 = p6.matcher(txt);
		Matcher m7 = p7.matcher(txt);
		Matcher m8 = p8.matcher(txt);
		Matcher m9 = p9.matcher(txt);
		boolean found1 = m1.find();
		boolean found2 = m2.find();
		boolean found3 = m3.find();
		boolean found4 = m4.find();
		boolean found5 = m5.find();
		boolean found6 = m6.find();
		boolean found7 = m7.find();
		boolean found8 = m8.find();		
		boolean found9 = m9.find();
		System.out.println(found1);
		System.out.println(found2);
		System.out.println(found3);
		System.out.println(found4);
		System.out.println(found5);
		System.out.println(found6);
		System.out.println(found7);
		System.out.println(found8);
		System.out.println(found9);
		System.out.println(m1.group());
		System.out.println(m2.group());
		System.out.println(m3.group());
		System.out.println(m4.group());
		System.out.println(m5.group());
		System.out.println(m6.group());
		System.out.println(m7.group());
		System.out.println(m8.group());
		System.out.println(m9.group());*/
	}
}