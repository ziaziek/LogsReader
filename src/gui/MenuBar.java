/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import misc.GeneralData;

/**
 *
 * @author Przemek
 */
public class MenuBar extends JMenuBar {
    
    
    MainWindow parentFrame = null;
    JTextField txtLogSize = null;
    JLabel lblLogSize = null;
    
    protected final static String lvlChngCommand = "Level Changed";
    
    protected String curLvl = "";

    public String getCurLvl() {
        return curLvl;
    }
    
    public MenuBar(MainWindow parentFrame){
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        add(createLogSizeTxtField(), BorderLayout.EAST);  
        add(createMenuFile(), BorderLayout.LINE_START);
        add(createEditMenu());
        setPreferredSize(new Dimension(50, 30));
    }
    
    protected JPanel createLogSizeTxtField(){
        JPanel pnl = new JPanel();
        lblLogSize = new JLabel("Log size: ");
        txtLogSize = new JTextField();
        txtLogSize.setPreferredSize(new Dimension(100, 20));
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
                JDialog d = new JDialog();
                d.add(new JLabel("XXX"));
                d.setVisible(true);
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
                throw new UnsupportedOperationException("Not supported yet.");
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
