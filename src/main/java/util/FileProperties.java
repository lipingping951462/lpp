package util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Configuration
@ConfigurationProperties(locations="classpath:file.properties", ignoreUnknownFields = false,prefix="file")
public class FileProperties {

	public static String addFIleSuffix;
	public static String addFIlePath;
	public static String fileContent;
	public static String getAddFIleSuffix() {
		return addFIleSuffix;
	}
	public static void setAddFIleSuffix(String addFIleSuffix) {
		FileProperties.addFIleSuffix = addFIleSuffix;
	}
	public static String getAddFIlePath() {
		return addFIlePath;
	}
	public static void setAddFIlePath(String addFIlePath) {
		FileProperties.addFIlePath = addFIlePath;
	}
	public static String getFileContent() {
		return fileContent;
	}
	public static void setFileContent(String fileContent) {
		FileProperties.fileContent = fileContent;
	}
	

	
	
}
