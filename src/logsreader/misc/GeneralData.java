/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logsreader.misc;

/**
 *
 * @author Przemek
 */
public class GeneralData {
    public static String[] Levels = new String[]{"TRACE", "DEBUG", "INFO", "WARN", "ERROR", "FATAL"};
    
    public static String[] Columns = new String[]{"Id", "Time from start", "Date", "Thread name", "Class Name", "Source", "Level", "Message"};
    
    public static enum ColumnsIndices {
        ID, TimeFromStart, Date, ThreadName, ClassName, Source, Level, Message
    };
}
