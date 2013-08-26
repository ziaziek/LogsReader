/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gubas.components.NonEditableTableModel;
import gubas.components.TableComponent;
import gubas.forms.BaseForm;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import misc.GeneralData;
import logsreader.LogsReader;
import misc.LogEntry;
import misc.LogEntryFilter;
/**
 *
 * @author Przemek
 */
public class MainWindow extends BaseForm implements MouseListener{
    
    JPanel msgPanel = null;
    
    TableComponent logTable = null;
    
    JTextArea msgArea = null;
    
    Dimension minStartDim = new Dimension(1064, 728);
    
    LogsReader r;
    
    String fileLocation = "C:/test.txt";
    
    public MainWindow(){
        super();
        this.setPreferredSize(minStartDim);
        this.setMinimumSize(minStartDim);
        r = new LogsReader(fileLocation);//LogsReader.class.getResource("../tests/test.txt").getFile());
        add(new MenuBar(this), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createMessagePanel(), BorderLayout.SOUTH);
        
    }
    
    public MainWindow(DefaultTableModel dataModel){
        this();
        logTable.getTable().setModel(dataModel);
    }
    
    protected JPanel createTablePanel(){
        Dimension tableDim = new Dimension(1030, 350);
        JPanel logPanel = new JPanel();
        DefaultTableModel modelT = new NonEditableTableModel(LogsReader.getInformationArray(r.getMessages()), GeneralData.Columns);
        logTable = new TableComponent(modelT);
        logTable.getTable().setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        logTable.getTable().addMouseListener(this);
        logTable.setSize(tableDim);
        logPanel.add(logTable);
        logPanel.setOpaque(false);
        return logPanel;
    }
    
    protected JPanel createMessagePanel(){
        msgPanel = new JPanel();
        msgArea = new JTextArea(15, 94);
        JScrollPane msgAreaScroll = new JScrollPane(msgArea);
        msgPanel.add(msgAreaScroll);
        msgPanel.setOpaque(false);
        return msgPanel;
    }

    public void callFilter(String filterLevel){
        String[][] rr = LogsReader.getInformationArray(new LogEntryFilter(filterLevel).getFilteredQueue(r.getMessages()));
        refreshTableData(rr, LogEntry.Columns);
    }
    
    public void resetLogsQueueLength(int capacity){
        r = new LogsReader(fileLocation, capacity);
        refreshTableData(LogsReader.getInformationArray(r.getMessages()), LogEntry.Columns);
    }
    
    private void refreshTableData(String[][] data, String[] cols){
        TableModel modelT = logTable.getTable().getModel();
        ((DefaultTableModel)modelT).setDataVector(data, cols);
        ((DefaultTableModel)modelT).fireTableDataChanged();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
          if(logTable!=null && r !=null){
            Object v =logTable.getTable().getValueAt(logTable.getTable().rowAtPoint(e.getPoint()), GeneralData.ColumnsIndices.ID.ordinal());
            if(v instanceof String){
                Iterator ir = r.getMessages().iterator();
                LogEntry currEntry = null;
                int i = 0;
                while(ir.hasNext() && (currEntry =(LogEntry)ir.next()).getId()!=Integer.parseInt((String) v)){
                  // carry on
                    i++;
                }
                if(i<r.getMessages().size()){ //message of a given id found
                    msgArea.setText(r.getMessageTextRenderer().getDisplayableMessage(currEntry));
                }
            }
        }  
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
