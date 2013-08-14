/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logsreader;

import gui.MainWindow;
import gubas.javaapplication1.FormsCaller;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
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
            Queue d = readData(LogsReader.class.getResource("../tests/test.txt").getFile(), 23);
            //FormsCaller.callNewWindow("LogReader", new MainWindow());
    }
    
    public static Queue readData(String fileName, int capacity){
        Queue<LogEntry> ret = new ArrayBlockingQueue(capacity);
        File f = new File(fileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            //List<String> entry = new ArrayList<>();
            StringBuilder restOfEntry = new StringBuilder();
            LogEntry entry = null;
                while(reader.ready()){
                    
                    if(ret.size()==capacity){
                        ret.poll();
                        restOfEntry = new StringBuilder();
                        entry = null;
                    }
                    String l = reader.readLine();
                    
                    if(l.contains(LogEntry.logEntryElementStartMarker) && l.contains(LogEntry.logEntryElementEndMarker)){ //we assume that the log file has got these signs inside and that the rest does not
                        if(restOfEntry.length()>0 && entry !=null){
                            entry.setDescription(restOfEntry.toString());
                        }
                        restOfEntry = new StringBuilder();
                        l=l.replaceAll(LogEntry.logEntryElementStartMarkerRegExp, "");
                        entry = new LogEntry(l.split(LogEntry.logEntryElementEndMarkerRegExp));
                        ret.offer(entry);
                        
                    } else {
                        if(entry != null){
                          restOfEntry.append(l);  
                        }                     
                    }    
                }
        } catch (IOException ex) {
            Logger.getLogger(LogsReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}
