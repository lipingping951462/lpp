package filter;

import java.io.IOException;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;



public class EncodingFilter extends AbstractFilter{
	private String encoding = null;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if (encoding != null) {
			request.setCharacterEncoding(encoding);
			response.setCharacterEncoding(encoding);
		}else{
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
		}
		System.out.println("endodefilter------------");

		chain.doFilter(request, response);
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
		encoding = filterConfig.getInitParameter("encoding");
	}


}
