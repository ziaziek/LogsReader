/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logsreader;

import gui.MainWindow;
import javaapplication1.FormsCaller;

/**
 *
 * @author Przemek
 */
public class LogsReader {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FormsCaller.callNewWindow("LogReader", new MainWindow());
    }
}
