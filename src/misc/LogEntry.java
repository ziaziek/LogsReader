/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Przemek
 */
public class LogEntry {
    
    
    public static String[] Columns = new String[]{"Time from start", "Date", "Thread name", "Class Name", "Source", "Level", "Message"};
    
    /**
     * Map of indices of Columns vs indices of the provided array in the LogEntry constructor. <index in the Columns>, <index in the provided array>
     */
    public static Map<Integer, Integer> mapping = new HashMap(){{put(0,0); 
                                                                 put(1,1);
                                                                 put(2,2);
                                                                 put(3,3);
                                                                 put(4,4);
                                                                 put(5,5);
                                                                 put(6,6);}};
    
    protected String time, date, thread, className, sourceName, level, message;

    public void setMessage(String message) {
        if(message!=null){
           this.message = message; 
        }
    }
    
     public LogEntry(String[] head){
         time = head[mapping.get(0)];
         date = head[mapping.get(1)];
         thread = head[mapping.get(2)];
         className = head[mapping.get(3)];
         sourceName = head[mapping.get(4)];
         level=head[mapping.get(5)];
     }

     
}
