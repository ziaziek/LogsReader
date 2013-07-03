/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logsreader;

import gui.MainWindow;
import gubas.javaapplication1.FormsCaller;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import misc.LogEntry;

/**
 *
 * @author Przemek
 */
public class LogsReader {

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Queue d = readData("E:/test", 50);
        //FormsCaller.callNewWindow("LogReader", new MainWindow());
    }
    
    public static Queue readData(String fileName, int capacity){
        Queue<LogEntry> ret = new ArrayBlockingQueue(capacity);
        File f = new File(fileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            //List<String> entry = new ArrayList<>();
                while(reader.ready()){
                    LogEntry entry;
                    if(ret.size()==capacity){
                        ret.remove();
                    }
                    String l = reader.readLine();
                    String restOfEntry;
                    if(l.contains("[") && l.contains("]")){
                        l=l.replaceAll("\\]", "");
                        entry = new LogEntry(new String[]{});
                        //entry.addAll(Arrays.asList(l.split("\\[")));
                        
                    } else {
                        
                    }
                    
                }
        } catch (IOException ex) {
            Logger.getLogger(LogsReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ret;
    }
}
