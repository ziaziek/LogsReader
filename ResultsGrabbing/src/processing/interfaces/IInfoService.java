package processing.interfaces;

import java.io.File;
import java.util.List;

import processing.impl.InfoServiceDirectoryException;

public interface IInfoService {

	public List getInfoList() throws InfoServiceDirectoryException;
	
	void setDirectory(File dir);
}
