package filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.SystemContext;

public class PagerFilter extends AbstractFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		try {
			SystemContext.setPagenoLocal(this.getOffset(req));
			SystemContext.setPagesizeLocal(this.getPagesize(req));
			System.out.println("PagerFilter-------------");
			
			chain.doFilter(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SystemContext.removePageNo();
			SystemContext.removePageSize();
		}

	}

	private int getOffset(HttpServletRequest request) {
		int offset = 0;

		try {
			offset = Integer.parseInt(request.getParameter("pager.offset"));
		} catch (Exception ignore) {
		}
		return offset;
	}

	private int getPagesize(HttpServletRequest request) {
		int pagesize = 0;

		try {
			pagesize = Integer.parseInt(request.getParameter("pagesize"));
		} catch (Exception ignore) {
		}

		if (pagesize == 0) {

			pagesize = 10;

		}
		// System.out.println(pagesize);
		return pagesize;
	}
}
