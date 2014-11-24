package interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EncodeInterceptor {

	public void aroundMethod(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		chain.doFilter(request, response);

	}
}
