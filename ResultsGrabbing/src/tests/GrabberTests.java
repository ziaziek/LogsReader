package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

import start.Grabber;

import data.Matches;
import data.impl.FileStorage;
import data.interfaces.IData;
import data.interfaces.IDataStorage;

public class GrabberTests {

	private IDataStorage store;
	private IData data;
	static String fn = "e:\\abc.txt";
	static String fnIn = "file:///e:\\dataIn.txt";
	private Grabber g;

	@Before
	public void setUp() throws Exception {
		store = new FileStorage();
		store.setStringConnection(fn);
		store.Connect();
		g = new Grabber();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		Files.deleteIfExists(Paths.get(fn));
	}

	@Test
	@Ignore
	public void downloadTest() {
		try {
			g.download(new URL(fnIn));
			assertNotNull(g.Data);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void grabberWriteTest() {
		try {
			g.download(new URL(fnIn));
			assertNotNull(g.Data.getValues(null));
			g.writeData(store);
			assertTrue(Files.exists(Paths.get(fn)));
			assertTrue(Files.size(Paths.get(fn)) > 0);
			assertEquals(
					((String) g.Data.getValues(null)).split("\r\n")[0],
					Files.newBufferedReader(Paths.get(fn),
							Charset.forName("utf-8")).readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void GrabberProcesDownloadTest() {
		try {
			g.download(new URL(fnIn));
			g.processDownload();
			assertTrue(g.samplesList.size()>0);
			assertNotNull(g.samplesList.get(0));
			Matches m = (Matches)g.samplesList.get(0);
			assertEquals("Spain", m.getCountry());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
