package vista;


import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * A class providing basic window event handling.  Typically it is
 * used as follows:
 *
 * <pre>
 *   public class MyApp extends JFrame {
 *     public MyApp() {
 *       ...
 *       addWindowListener(new WindowMonitor());
 *       ...
 *     }
 *     ...
 *   }
 * </pre>
 *
 * This code is adapted from an example given in <i>Java Swing</i>,
 * by Eckstein, Loy &amp; Wood (O'Reilly &amp; Associates, 1998).
 *
 * @author Nick Efford
 * @version 1.0 [1999/01/20]
 * @see java.awt.Window
 * @see java.awt.event.WindowEvent
 * @see java.awt.event.WindowAdapter
 */

public class WindowMonitor extends WindowAdapter {

  /**
   * Specifies behaviour when a window is closed.
   * The window is hidden and disposed of, then the program is terminated.
   * @param event a WindowEvent triggered when a window is closed
   */

  public void windowClosing(WindowEvent event) {
    Window window = event.getWindow();
    window.setVisible(false);
    window.dispose();
    System.exit(0);
  }

}
