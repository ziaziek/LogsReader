/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gubas.forms.BaseForm;
import gubas.forms.DialogForm;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import misc.LogEntry;

/**
 *
 * @author Przemysław
 */
public class PatternsWindow extends DialogForm  {
    
    
    JSpinner[] spinners = null;
    
    public PatternsWindow(gubas.forms.Dialog dialogType, String message){
        super(dialogType, message);
        add(buildForm());
    }
    
    @Override
    protected void passData(){
        try {
            LogEntry.rebuildMapping(getValuesMapping());
        } catch (Exception ex) {
            Logger.getLogger(PatternsWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Integer[] getValuesMapping(){
        List<Integer> ret = new ArrayList<>();
        for(int i = 0; i<LogEntry.Columns.length; i++){
            Integer v = (Integer)spinners[i].getValue();
            if(ret.contains(v) && JOptionPane.showConfirmDialog(this,"There are multiple values of " + v +". Interrupt the change of mapping?." , "",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                break;
            }
            ret.add(v);
        }
        return ret.toArray(new Integer[]{});
    }
    
    protected JPanel buildForm(){
        JPanel p = new JPanel(new GridLayout(LogEntry.Columns.length, 0, 5, 5));
        spinners = new JSpinner[LogEntry.Columns.length];
        for(int i =0; i<LogEntry.Columns.length; i++){
            p.add(new JLabel(LogEntry.Columns[i]));
            JSpinner sp = new JSpinner(new SpinnerNumberModel(0, -1, LogEntry.Columns.length-1, 1));
            p.add(sp);
            spinners[i]=sp;
        }
        
        return p;
    }


}
