package regexDictionary;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import regexDictionary.RegexDictionary;
import java.sql.SQLException;

public class testFileMenu{

 private static int shellWidth = 1000;
 private static int shellHeight = 570;
 private static int xPosition = 30;
 private static int yPosition = 30;
 private static int width = 180;
 private static int height = 30;
 private static int scrollWidth = 220;
 private static int scrollHeight = 350;
 private static int buttonWidth = 100;
 private static int buttonHeight = 30;
 private static int tableWidth = 650;
 private static int tableHeight = 350;
 private static int columnWidth = 215;
 
 private static void addWidgetsToShell(Display display, Shell shell) {

  Menu menu = new Menu(shell, SWT.BAR);
  shell.setMenuBar(menu);

  MenuItem fileMenuItem = new MenuItem(menu, SWT.CASCADE);
  fileMenuItem.setText("File");

  MenuItem edit = new MenuItem(menu, SWT.CASCADE);
  edit.setText("Edit");

  MenuItem source = new MenuItem(menu, SWT.CASCADE);
  source.setText("Source");
  
  Table dictionary_entry = new Table(shell, SWT.FULL_SELECTION | SWT.CHECK | SWT.BORDER);
  dictionary_entry.setBounds(300, 70, tableWidth, tableHeight);
  dictionary_entry.setHeaderVisible(true);
  dictionary_entry.setLinesVisible(true);
  
  TableColumn id = new TableColumn(dictionary_entry, SWT.LEFT);
  id.setText("Head Word");
  id.setWidth(columnWidth);

  TableColumn name = new TableColumn(dictionary_entry, SWT.LEFT);
  name.setText("Pronounciation");
  name.setWidth(columnWidth);

  TableColumn age = new TableColumn(dictionary_entry, SWT.LEFT);
  age.setText("Definition");
  age.setWidth(columnWidth);
  
  TableItem item = new TableItem(dictionary_entry, SWT.NONE);
  JDBCMySQLDemo demo = new JDBCMySQLDemo();
  for (int i = 0; i < 3; i++) {
	  try {
		  String[] query= demo.getData();
		  item.setText(0,query[1]);
		  item.setText(1,query[2]);
		  item.setText(2,query[3]);
	  } catch (SQLException e) {
		  System.out.println("Failed to Extract Resultset");
	  }
  }
  Label label1 = new Label(shell, SWT.LEFT);
  Label label2 = new Label(shell, SWT.SEPARATOR | SWT.SHADOW_OUT | SWT.VERTICAL);
  Label label3 = new Label(shell, SWT.CENTER);
  label1.setText("Enter Dictionary entry:");
  label1.setBounds(xPosition, yPosition, width, height);
  label2.setBounds(280,20,5,470);
  label3.setText("Search: ");
  label3.setBounds(300, 30, 100, height);

  yPosition += 40;

  Text text1 = new Text(shell, SWT.BORDER | SWT.LEFT | SWT.SEARCH );
  text1.setBounds(400, 20, scrollWidth, 30);
  
  Text text2 = new Text(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL );
  text2.setBounds(xPosition, yPosition, scrollWidth, scrollHeight);
  
  Button button1 = new Button(shell, SWT.PUSH | SWT.CENTER);
  button1.setText("Submit");
  button1.setLocation(100,450);
  button1.setSize(buttonWidth,buttonHeight);
  
  Button button2 = new Button(shell, SWT.PUSH | SWT.CENTER);
  button2.setText("Submit");
  button2.setLocation(650,20);
  button2.setSize(buttonWidth,buttonHeight);
  
  button1.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			RegexDictionary dr = new RegexDictionary();
			String definition = "";
			String txt = dr.getTxt(text2.getText());
			String headword = dr.Match("([a-zA-Z·]+)",txt); 
			String pronounciation = dr.Match("(\\/.+\\/)",txt);
			definition = definition.concat(dr.Match("(adjective: [A-Za-z0-9;:\\s]+|verb: [A-Za-z0-9;:\\s]+|noun: [A-Za-z0-9;:\\s]+)",txt)+"\n");
			if (dr.checkMatch("([0-9]+\\.)",txt)) {
				definition = definition.concat(dr.Match("([a-zA-Z0-9]+\\.)",txt)+"\n");
				definition = definition.concat(dr.Match("([a-zA-Z ]+\\.)",txt)+"\n");
			}	
			definition = definition.concat(dr.Match("(\"[a-zA-Z ]+\")",txt)+"\n");
			if (dr.checkMatch("(synonyms:\\s[a-zA-z, -_]+)",txt)){
				definition = definition.concat(dr.Match("(synonyms:\\s[a-zA-z, -_]+)",txt)+"\n");
			}
			if (dr.checkMatch("(antonyms:\\s[a-zA-z, -_]+)",txt)){
				definition = definition.concat(dr.Match("(antonyms:\\s[a-zA-z, -_]+)",txt)+"\n");
			}
			System.out.println(headword);
			System.out.println(pronounciation);
			System.out.println(definition);
			
			dr.storeData(headword,pronounciation,definition);
			
		}
	});

 }

 public static void main(String[] args) {

  /* Instantiate Display object, it represents SWT session */
  Display display = new Display();

  /*
   * Define Shell, it represent a window, You can add more than one shell
   * to Display
   */
  Shell shell = new Shell(display);
  shell.setSize(shellWidth, shellHeight);
  shell.setText("SWT Tutorial");

  addWidgetsToShell(display, shell);

  /* Open shell window */
  shell.open();

  /*
   * Run the event dispatching loop until an exit condition occurs, which
   * is typically when the main shell window is closed by the user.
   */

  while (!shell.isDisposed()) {
   if (!display.readAndDispatch())
    display.sleep();
  }

  /* Dispose the display */
  display.dispose();

 }
}