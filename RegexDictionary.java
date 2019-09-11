package regexDictionary;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;  

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
        
        public String getTxt(String txt) {
        	return txt;
        }
        
        public String Match(String pattern, String txt){
        	String result = "";
        	try {
        		//PrintStream out = new PrintStream(System.out, true, "UTF-8");
        		Pattern p = Pattern.compile(pattern);
				Matcher m = p.matcher(txt);
				boolean found = m.find();
				result = m.group().toString();
				return result;
        	}
        	catch(Exception e){
        		System.out.println(e);
        	}
			return result;
		}
		public boolean checkMatch(String pattern,String txt){
    		//PrintStream out = new PrintStream(System.out, true, "UTF-8");
    		Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(txt);
			boolean found = m.find();
			return found;
			//System.out.println(m.group());
		}
		
		public void storeData(String head_word, String pronounciation, String definition) {
			try {
				JDBCMySQLDemo demo = new JDBCMySQLDemo();
				Connection connection = DriverManager.getConnection(demo.url,demo.user,demo.password);
				Statement statement = connection.createStatement();
				String add = "insert into dictionary_results "
						+ " (head_word,pronounciation,definition)"
					 	+ " value (?,?,?)";
				PreparedStatement queryVar = connection.prepareStatement(add);
				queryVar.setString(1, head_word);
				queryVar.setString(2, pronounciation);
				queryVar.setString(3, definition);
				queryVar.execute();
				
				//statement.executeUpdate(add);
				System.out.println("Insert Completed");
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	/*public static void main (String[]args){
		String url = "jdbc:mysql://localhost:3306/regexDictionary";
		String user = "root";
		String password = "root";
		String DRIVER_CLASS = "com.mysql.jdbc.Driver";
		
		String definition = "";
		RegexDictionary dr = new RegexDictionary();
        dr.readCSV();
        //System.out.println(dr.txt);
		String head_word = dr.Match("([a-zA-Z·]+)");
		String pronounciation = dr.Match("(\\/.+\\/)");
		//if (dr.checkMatch("(\\s?Learn to pronounce)")) {
		//	dr.Match("(\\s?Learn to pronounce)");
		//}
		//dr.Match("(adjective|verb|noun)");
		definition = definition.concat(dr.Match("(adjective: [A-Za-z0-9;:\\s]+|verb: [A-Za-z0-9;:\\s]+|noun: [A-Za-z0-9;:\\s]+)")+"\n");
		if (dr.checkMatch("([0-9]+\\.)")) {
			definition = definition.concat(dr.Match("([a-zA-Z0-9]+\\.)")+"\n");
			definition = definition.concat(dr.Match("([a-zA-Z ]+\\.)")+"\n");
		}	
		definition = definition.concat(dr.Match("(\"[a-zA-Z ]+\")")+"\n");
		if (dr.checkMatch("(synonyms:\\s[a-zA-z, -_]+)")){
			definition = definition.concat(dr.Match("(synonyms:\\s[a-zA-z, -_]+)")+"\n");
		}
		if (dr.checkMatch("(antonyms:\\s[a-zA-z, -_]+)")){
			definition = definition.concat(dr.Match("(antonyms:\\s[a-zA-z, -_]+)")+"\n");
		}
		System.out.println(head_word);
	System.out.println(pronounciation);
		System.out.println(definition);
		
		try {
			//JDBCMySQLDemo demo = new JDBCMySQLDemo();
			Connection connection = DriverManager.getConnection(url,user,password);
			Statement statement = connection.createStatement();
			String add = "insert into dictionary_results "
					+ " (head_word,pronounciation,definition)"
				 	+ " value (?,?,?)";
			PreparedStatement queryVar = connection.prepareStatement(add);
			queryVar.setString(1, head_word);
			queryVar.setString(2, pronounciation);
			queryVar.setString(3, definition);
			queryVar.execute();
			
			//statement.executeUpdate(add);
			System.out.println("Insert Completed");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} */
}