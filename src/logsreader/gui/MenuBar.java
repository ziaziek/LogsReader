/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logsreader.gui;

import gubas.forms.Dialog;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import logsreader.misc.GeneralData;

/**
 *
 * @author Przemek
 */
public class MenuBar extends JPanel {
    
    JMenuBar menuBar = null;
    LogsReaderWindow parentFrame = null;
    JTextField txtLogSize = null;
    JLabel lblLogSize = null;
    JPanel spacer = new JPanel();
    
    protected final static String lvlChngCommand = "Level Changed";
    
    protected String curLvl = "";

    public String getCurLvl() {
        return curLvl;
    }
    
    public MenuBar(LogsReaderWindow parentFrame){
        this.parentFrame = parentFrame;
        menuBar = new JMenuBar();
        setLayout(new BorderLayout());
        menuBar.add(createMenuFile());
        menuBar.add(createEditMenu());
        add(menuBar, BorderLayout.LINE_START);
        add(createLogSizeTxtField(), BorderLayout.EAST);  
        
        setPreferredSize(new Dimension(parentFrame.getWidth(), 30));
        
    }
    
    
    @Override
    public void setPreferredSize(Dimension d){
        super.setPreferredSize(d);
        int w=0;
        for(Component c :this.getComponents()){
            if (c !=spacer)
                w+=c.getWidth();
        }
        if(parentFrame!=null){
            spacer.setPreferredSize(new Dimension(parentFrame.getWidth()-3*w,
                    (int) d.getHeight()));
        }
    }
    
    protected JPanel createLogSizeTxtField(){
        JPanel pnl = new JPanel();
        lblLogSize = new JLabel("Log size: ");
        txtLogSize = new JTextField();
        txtLogSize.setPreferredSize(new Dimension(100, 20));
        txtLogSize.addActionListener(new TextFieldActionListener());
        pnl.add(lblLogSize);
        pnl.add(txtLogSize);
        return pnl;
    }
     
    protected JMenu createMenuFile(){
        JMenu mf = new JMenu("File");
        mf.setMnemonic(KeyEvent.VK_F);
        JMenuItem fileOpen = new JMenuItem("Open");
        fileOpen.setMnemonic(KeyEvent.VK_O);
        fileOpen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: implement opening a file
                JFileChooser fc = new JFileChooser();
                if(fc.showOpenDialog(menuBar) == JFileChooser.APPROVE_OPTION){
                  parentFrame.loadData(fc.getSelectedFile().getAbsolutePath());  
                }
            }
        });
        
        mf.add(fileOpen);
        JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic(KeyEvent.VK_X);
        exit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(parentFrame!=null){
                    parentFrame.dispose();
                }
            }
        });
        mf.add(exit);
        return mf;
    }
    
    protected JMenu createEditMenu(){
        JMenu me = new JMenu("Edit");
        me.setMnemonic(KeyEvent.VK_E);
        JMenuItem patternEdit = new JMenuItem("Edit pattern");
        patternEdit.setMnemonic(KeyEvent.VK_D);
        patternEdit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Open a new window to select items from the specified file to match the ones available in here
                PatternsWindow pat = new PatternsWindow(Dialog.OK, "Set the pattern for the log files.");
                pat.setCaller(parentFrame);
                pat.setVisible(true);
            }
        });
        me.add(patternEdit);
        JMenu logLvl = new JMenu("Set Log Level");
        ButtonGroup levelsGroup = new ButtonGroup();
        RadioButtonLevelActionListener rbAction = new RadioButtonLevelActionListener();
        for(String s: GeneralData.Levels){
            JRadioButtonMenuItem it = new JRadioButtonMenuItem(s);
            it.setActionCommand(lvlChngCommand);
            it.addActionListener(rbAction);
            levelsGroup.add(it);
            logLvl.add(it);
        }
        me.add(logLvl);
        return me;
    }
    
    protected class TextFieldActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource()==txtLogSize){
                parentFrame.resetLogsQueueLength(Integer.parseInt(txtLogSize.getText()));
            }
        }
        
    }
    
    protected class RadioButtonLevelActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() instanceof JRadioButtonMenuItem && e.getActionCommand().equals(MenuBar.lvlChngCommand)){
              curLvl=((JRadioButtonMenuItem)e.getSource()).getText();
              parentFrame.callFilter(curLvl);
            }
             
        }
    
    }
}
