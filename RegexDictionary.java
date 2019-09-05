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
        String csvFile = "D:\\Project\\RegexDictionary-master\\dictionary.tsv";
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
		if (dr.checkMatch("(\\s?Learn to pronounce)")) {
			dr.Match("(\\s?Learn to pronounce)");
		}
		dr.Match("(adjective|verb|noun)");
		dr.Match("(adjective: [A-Za-z0-9;:\\s]+|verb: [A-Za-z0-9;:\\s]+|noun: [A-Za-z0-9;:\\s]+)");
		if (dr.checkMatch("([0-9]+\\.)")) {
			dr.Match("([a-zA-Z0-9]+\\.)");
			dr.Match("([a-zA-Z ]+\\.)");
		}	
		dr.Match("(\"[a-zA-Z ]+\")");
		if (dr.checkMatch("(synonyms:\\s[a-zA-z, -_]+)")){
			dr.Match("(synonyms:\\s[a-zA-z, -_]+)");
		}
		if (dr.checkMatch("(antonyms:\\s[a-zA-z, -_]+)")){
			dr.Match("(antonyms:\\s[a-zA-z, -_]+)");
		}
	}
}