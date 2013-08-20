/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.util.Queue;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author Przemo
 */
public class LogEntryFilter extends Filter<LogEntry> {
    
    private String level;

        public LogEntryFilter(String level){
            this.level = level;
        }
        

    public Queue getFilteredQueue(Queue q) {
        Queue ret = new ArrayBlockingQueue(q.size());
        for (Iterator<LogEntry> iterator =
                this.filter(q.iterator());
                iterator.hasNext();) {
            ret.offer(iterator.next());
        }
        return ret;
    }
    
    @Override
    public boolean passes(LogEntry object) {
        boolean t = object.getLevel().trim().equals(level);
        return t;
    }


}
