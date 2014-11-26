/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controller;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.DispatcherType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import util.FileProperties;
import filter.PagerFilter;

@SpringBootApplication
//@ImportResource("applicationContext.xml")
public class SampleWebJspApplication extends SpringBootServletInitializer {
//	@SuppressWarnings("serial")
//	@Bean
//	public Servlet dispatcherServlet() {
//		return new GenericServlet() {
//			@Override
//			public void service(ServletRequest req, ServletResponse res)
//					throws ServletException, IOException {
//				res.setContentType("text/plain");
//				res.getWriter().append("Hello World");
//			}
//		};
//	}
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
	  FileProperties fileProperties() {
	   return  new FileProperties();
	  }
	
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(SampleWebJspApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication springApplication=new SpringApplication(SampleWebJspApplication.class);
		springApplication.setWebEnvironment(true);  
		springApplication.setShowBanner(false);  
        Set<Object> set = new HashSet<Object>();  
//        set.add("classpath:application.properties");
        set.add("classpath:applicationContext.xml");  
        springApplication.setSources(set);
        springApplication.run(args);
	}

}
