/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gubas.components.TableComponent;
import gubas.forms.BaseForm;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import misc.GeneralData;
import logsreader.LogsReader;
/**
 *
 * @author Przemek
 */
public class MainWindow extends BaseForm{
    
    JPanel msgPanel = null;
    
    TableComponent logTable = null;
    
    JTextArea msgArea = null;
    
    Dimension minStartDim = new Dimension(1064, 728);
    
    public MainWindow(){
        super();
        this.setPreferredSize(minStartDim);
        this.setMinimumSize(minStartDim);
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
        logTable = new TableComponent(LogsReader.getInformationArray(LogsReader.readData(LogsReader.class.getResource("../tests/test.txt").getFile(), 23)), GeneralData.Columns);
        //logTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //logTable.setPreferredSize(tableDim);
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
}
