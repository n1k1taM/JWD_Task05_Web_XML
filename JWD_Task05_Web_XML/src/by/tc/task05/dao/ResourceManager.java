package by.tc.task05.dao;

import java.util.ResourceBundle;

public class ResourceManager {
	static ResourceBundle resourseBundle = ResourceBundle.getBundle("resources/fileConfiguration");
	private ResourceManager() {
		super();
	}

	public static String getFilePath() {
		
		String filePath = resourseBundle.getString("filePath");
		return filePath;
	}
}
