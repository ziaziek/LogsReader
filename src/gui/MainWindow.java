/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gubas.components.GubasChart;
import gubas.components.NonEditableTableModel;
import gubas.components.TabbedPanelComponent;
import gubas.components.TableComponent;
import gubas.forms.BaseForm;
import gubas.images.Images;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.CategoryDataset;
import org.jfree.util.Log;

/**
 *
 * @author Przemek
 */
public class MainWindow extends BaseForm implements MouseListener{
    
    JPanel msgPanel = null;
    
    JPanel graphPanel = null;
    
    TableComponent logTable = null;
    
    JTextArea msgArea = null;
    
    Dimension minStartDim = new Dimension(1064, 728);
    
    LogsReader r;

    ChartPanel chp = null;
    
    TabbedPanelComponent tabPanel = null;
    
    JPanel tablePanel = null;
    
    public LogsReader getR() {
        return r;
    }
    
    String fileLocation = "";
    
    public MainWindow(){
        super();
        this.setPreferredSize(minStartDim);
        this.setMinimumSize(minStartDim);
        r = new LogsReader(fileLocation);//LogsReader.class.getResource("../tests/test.txt").getFile());
        add(new MenuBar(this), BorderLayout.NORTH);
        add(createTabPanel(), BorderLayout.CENTER);
        this.setResizable(false);
    }
    
    public MainWindow(DefaultTableModel dataModel){
        this();
        logTable.getTable().setModel(dataModel);
    }
    
    public void loadData(String fileLocation){
        r = new LogsReader(fileLocation);
        refreshTableData(LogsReader.getInformationArray(r.getMessages()), LogEntry.Columns);
        this.fileLocation = fileLocation;
    }
    
    
    protected TabbedPanelComponent createTabPanel(){
        tabPanel = new TabbedPanelComponent();
        tabPanel.addTab("Log Table", createTablePanel());
        tabPanel.addTab("Graphs", createGraphsPanel());
        return tabPanel;
    }
    
    protected JPanel createGraphsPanel(){
        graphPanel = new JPanel();
        graphPanel.setPreferredSize(new Dimension(1030,350));      
        chp = new ChartPanel(ChartFactory.createBarChart("Graph", "Level", "Frequency", prepareGraphData(), PlotOrientation.HORIZONTAL, false, false, false));
        chp.setPreferredSize(new Dimension(1030,530));
        chp.getChart().getPlot().setBackgroundImage(Images.getImageIcon(Images.BlueGradientBackground).getImage());
        graphPanel.add(chp);
        graphPanel.setOpaque(true);
        return graphPanel;
    }
    
    protected JPanel createTablePanel(){
        tablePanel = new JPanel(new BorderLayout());
        Dimension tableDim = new Dimension(1030, 350);
        JPanel logPanel = new JPanel();
        DefaultTableModel modelT = new NonEditableTableModel(LogsReader.getInformationArray(r.getMessages()), GeneralData.Columns);
        logTable = new TableComponent(modelT);
        logTable.getTable().setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        logTable.getTable().addMouseListener(this);
        logTable.setSize(tableDim);
        logPanel.add(logTable);
        logPanel.setOpaque(false);
        tablePanel.add(logPanel, BorderLayout.CENTER);
        tablePanel.add(createMessagePanel(), BorderLayout.SOUTH);
        return tablePanel;
    }
    
    protected JPanel createMessagePanel(){
        msgPanel = new JPanel();
        msgArea = new JTextArea(15, 94);
        JScrollPane msgAreaScroll = new JScrollPane(msgArea);
        msgPanel.add(msgAreaScroll);
        msgPanel.setOpaque(true);
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
        chp.getChart().getCategoryPlot().setDataset(prepareGraphData());
        chp.repaint();
        chp.getChart().fireChartChanged();
    }
    
    private CategoryDataset prepareGraphData(){
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        try{
            if(r!=null){
                double[] values = r.calculateStatistics();
                for(int i = 0; i<values.length; i++){
                    ds.addValue(values[i], "", GeneralData.Levels[i]);                  
                }
            } 
        } catch(IllegalArgumentException ex){
            System.err.println(ex.getMessage());
        }
        return ds;
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
