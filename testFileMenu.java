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


public class testFileMenu{

 private static int shellWidth = 300;
 private static int shellHeight = 570;
 private static int xPosition = 30;
 private static int yPosition = 30;
 private static int width = 180;
 private static int height = 30;
 private static int scrollWidth = 220;
 private static int scrollHeight = 350;
 
 private static int buttonWidth = 100;
 private static int buttonHeight = 30;
  
 
 private static void addWidgetsToShell(Display display, Shell shell) {

  Menu menu = new Menu(shell, SWT.BAR);
  shell.setMenuBar(menu);

  MenuItem fileMenuItem = new MenuItem(menu, SWT.CASCADE);
  fileMenuItem.setText("File");

  MenuItem edit = new MenuItem(menu, SWT.CASCADE);
  edit.setText("Edit");

  MenuItem source = new MenuItem(menu, SWT.CASCADE);
  source.setText("Source");
  
  Label label1 = new Label(shell, SWT.LEFT);
  label1.setText("Enter Dictionary entry:");
  label1.setBounds(xPosition, yPosition, width, height);

  yPosition += 40;

  Text text2 = new Text(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL );
  text2.setText("Bordered scrolled text box");
  text2.setBounds(xPosition, yPosition, scrollWidth, scrollHeight);
  
  Button button1 = new Button(shell, SWT.PUSH | SWT.CENTER);
  button1.setText("Submit");
  button1.setLocation(100,450);
  button1.setSize(buttonWidth,buttonHeight);
  
  button1.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			System.out.println("Submit button was clicked");
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