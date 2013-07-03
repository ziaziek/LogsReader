/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gubas.forms.BaseForm;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import misc.GeneralData;

/**
 *
 * @author Przemek
 */
public class MainWindow extends BaseForm{
    
    JPanel msgPanel = null;
    
    JTable logTable = null;
    
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
    
    protected JPanel createTablePanel(){
        Dimension tableDim = new Dimension(1030, 350);
        JPanel logPanel = new JPanel();
        logTable = new JTable(new Object[][]{{0,0,0,0,0,0,0}}, GeneralData.Columns);
        logTable.setFillsViewportHeight(true);
        //logTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        logTable.setPreferredSize(tableDim);
        JScrollPane logPane = new JScrollPane(logTable);
        logPane.setPreferredSize(tableDim);
        logPanel.add(logPane);
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
