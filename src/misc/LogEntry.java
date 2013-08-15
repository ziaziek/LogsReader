/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Przemek
 */
public class LogEntry {
    
    public static String logEntryElementStartMarker = "[";
    
    public static String logEntryElementEndMarker = "]";
    
    public static String logEntryElementStartMarkerRegExp = "\\[";
    
    public static String logEntryElementEndMarkerRegExp = "\\]";
    
    public static String[] Columns = GeneralData.Columns;
    
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
    
    protected String time="", date="", thread="", className="", sourceName="", level="", message="", description="";

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getThread() {
        return thread;
    }

    public String getClassName() {
        return className;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String message) {
        if(message!=null){
           this.description = message; 
        }
    }
    
     public LogEntry(String[] head){
         if(head.length>0){
             try{
           Integer.parseInt(head[mapping.get(0)]);
           time = head[mapping.get(0)];  
             } catch(NumberFormatException ex){
                 time = "";
                 return;
             }
         }
         if(head.length>1){
           date = head[mapping.get(1)];        
         }
         if(head.length>2){
          thread = head[mapping.get(2)];   
         }
         if(head.length>3){
          className = head[mapping.get(3)];   
         }
         if(head.length>4){
          sourceName = head[mapping.get(4)];   
         }
         if(head.length>5){
           level=head[mapping.get(5)];  
         }
         if(head.length>6){
             message=head[mapping.get(6)];
         }
     }

     
}
