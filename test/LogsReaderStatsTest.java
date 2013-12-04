/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import logsreader.logsreader.LogsReader;
import logsreader.misc.GeneralData;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Przemo
 */
public class LogsReaderStatsTest {
    
    
    LogsReader r = null;
    
    String file = "C:\\Users\\Przemo\\Documents\\NetBeansProjects\\LogsReader\\src\\tests\\test.txt";
    
    public LogsReaderStatsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        r = new LogsReader(file);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void stats(){
        double[] s = r.calculateStatistics();
        assertTrue(s.length== GeneralData.Levels.length);
    }
}