/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logsreader;

import gui.MainWindow;
import gubas.javaapplication1.FormsCaller;
import java.io.*;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import misc.GeneralData;
import misc.LogEntry;
import misc.LogEntryFilter;

/**
 *
 * @author Przemek
 */
public class LogsReader {

    protected Queue messages;

    public Queue getMessages() {
        return messages;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    protected int capacity = 100;
    
    
    protected DisplayableMessageRenderer messageTextRenderer = new DisplayableMessageRenderer();

    public DisplayableMessageRenderer getMessageTextRenderer() {
        return messageTextRenderer;
    }

    public void setmRenderer(DisplayableMessageRenderer mRenderer) {
        this.messageTextRenderer = mRenderer;
    }
    
    public LogsReader(String logFileLocation){
        messages = readData(logFileLocation, capacity);
    }
    
    public LogsReader(String logFileLocation, int cap){
        capacity = cap;
        messages =readData(logFileLocation, cap);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            //Queue d = readData(LogsReader.class.getResource("../tests/test.txt").getFile(), 23);
        MainWindow mw = new MainWindow();
            FormsCaller.callNewWindow("LogReader", mw);
    }
    
    
    public static String[][] getInformationArray(Queue<LogEntry> data) {
        //prepare basic array of information
        String[][] infos = new String[1][LogEntry.Columns.length];
        int i = 0;
        if (data != null) {
            infos = new String[data.size()][LogEntry.Columns.length];
            for (LogEntry lg : data) {
                infos[i][GeneralData.ColumnsIndices.ID.ordinal()] = String.valueOf(lg.getId());
                infos[i][GeneralData.ColumnsIndices.TimeFromStart.ordinal()] = lg.getTime();
                infos[i][GeneralData.ColumnsIndices.Date.ordinal()] = lg.getDate();
                infos[i][GeneralData.ColumnsIndices.ThreadName.ordinal()] = lg.getThread();
                infos[i][GeneralData.ColumnsIndices.ClassName.ordinal()] = lg.getClassName();
                infos[i][GeneralData.ColumnsIndices.Level.ordinal()] = lg.getLevel();
                infos[i][GeneralData.ColumnsIndices.Source.ordinal()] = lg.getSourceName();
                infos[i][GeneralData.ColumnsIndices.Message.ordinal()] = lg.getMessage();
                i++;
            }
        }

        return infos;
    }
    
    public Queue readData(String fileName, int capacity){
        Queue<LogEntry> ret = new ArrayBlockingQueue(capacity);
        try {
            File f = new File(fileName);
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
                        if(entry.getId()==LogEntry.getNextID()-1 && entry.getId()>0){
                          ret.offer(entry);  
                        }
                    } else {
                        if(entry != null){
                          restOfEntry.append(l);  
                        }                     
                    }    
                }
        } catch(FileNotFoundException ex){
            Logger.getLogger(LogsReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LogsReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public double[] calculateStatistics(){
        int l = GeneralData.Levels.length;
        double[] d = new double[l];
        if(messages!=null && messages.size()>0){
            for(int i=0; i<l; i++){
                LogEntryFilter f = new LogEntryFilter(GeneralData.Levels[i]);
                d[i]=f.getFilteredQueue(messages).size();
            }
        }
        return d;
    }
}
