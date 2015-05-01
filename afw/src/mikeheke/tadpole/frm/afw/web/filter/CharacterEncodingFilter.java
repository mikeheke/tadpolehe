package mikeheke.tadpole.frm.afw.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class CharacterEncodingFilter implements Filter{

	private static String ENCODING= "UTF-8";
	

	public CharacterEncodingFilter() {
		ENCODING = "UTF-8";
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
			request.setCharacterEncoding(ENCODING);
			response.setCharacterEncoding(ENCODING);
		
			HttpServletRequest  httpRequest = (HttpServletRequest) request;
			if(XSSSecurityFilter.neetFilterXSS(httpRequest)){
				request.setCharacterEncoding(ENCODING);
				chain.doFilter(request, response);
				response.setCharacterEncoding(ENCODING);
			}else{
				chain.doFilter(request, response);
			}
			
	}

	public void destroy() {
	}

}
