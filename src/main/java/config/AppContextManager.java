package config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@Configuration
public class AppContextManager {

	public static ApplicationContext createAppContext() {
		ApplicationContext act = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		return act;
	}
}
