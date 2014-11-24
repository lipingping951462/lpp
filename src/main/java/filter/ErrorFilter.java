package filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ErrorFilter extends AbstractFilter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try{
			chain.doFilter(request, response);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
