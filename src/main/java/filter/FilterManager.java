package filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

import javax.servlet.DispatcherType;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Component
@Configuration
public class FilterManager {
	@Bean
	public FilterRegistrationBean pageFilter() {

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new PagerFilter());
		List<String> urlls = new ArrayList<String>();
		urlls.add("/user/*");
		registration.setUrlPatterns(urlls);
		registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
		return registration;
	}
	
	@Bean
	public FilterRegistrationBean encodeFilter() {

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new EncodingFilter());
		List<String> urlls = new ArrayList<String>();
		urlls.add("/user/*");
		registration.setUrlPatterns(urlls);
		registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
		return registration;
	}
	
	@Bean
	public FilterRegistrationBean errorFilter() {

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new ErrorFilter());
		List<String> urlls = new ArrayList<String>();
		urlls.add("/");
		registration.setUrlPatterns(urlls);
		registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
		return registration;
	}
}
