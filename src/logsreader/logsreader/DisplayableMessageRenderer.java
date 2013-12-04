/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logsreader.logsreader;

import logsreader.misc.LogEntry;

/**
 *
 * @author Przemo
 */
public class DisplayableMessageRenderer {
    
    public String getDisplayableMessage(LogEntry lg) {
        StringBuilder r = new StringBuilder(lg.getDate());
        r.append("\n");
        r.append(lg.getLevel());
        r.append("\n");
        r.append(lg.getMessage());
        r.append("\n");
        if (lg.getDescription() != null && lg.getDescription().length() > 0) {
            r.append(lg.getDescription());
        }
        return r.toString();
    }
}
