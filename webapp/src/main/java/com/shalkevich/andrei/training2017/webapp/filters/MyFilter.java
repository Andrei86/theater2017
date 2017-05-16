package com.shalkevich.andrei.training2017.webapp.filters;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.services.IBookingService;
import com.shalkevich.andrei.training2017.services.ICustomerService;
import com.shalkevich.andrei.training2017.services.IGenreService;
import com.shalkevich.andrei.training2017.services.IMovieService;

public class MyFilter implements Filter{
	
	private ICustomerService customerService;
	
	private ApplicationContext appContext; // доступ к компонентам приложения
	
	@Override
	public void init(FilterConfig config) throws ServletException {

		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		
		customerService = context.getBean(ICustomerService.class);
		appContext = context;
	}

	@Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws java.io.IOException, ServletException {
	        HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse res = (HttpServletResponse) response;
	        if (!isAuthRequired(req)) {
	            chain.doFilter(request, response);
	            return;							// если авторизация не требуется - то пропускаем
	        }
	        
	        String[] credentials = resolveCredentials(req); // это то что ввел юзер

	        boolean isCredentialsResolved = credentials != null && credentials.length == 2;
	        if (!isCredentialsResolved) {
	            res.sendError(401);
	            return;
	        }

	        String username = credentials[0]; // получили имя и пароль из хидера
	        String password = credentials[1];
	        
	        Customer userFromDB = null;
	        
	        Integer userId = (customerService.getByLogin(username)).getId();        
	        
	        if (validateUserPassword(username, password)) { // если совпадает - пропускаем через цепочку

	            //userDataStorage.setId(userId); // хранилище данных юзера
	            //chain.doFilter(request, response); // вызывал 2 раз метод
	        	
	        	// проверяем роли
	        	if(getUserRole(username).equals("admin"))
	        		chain.doFilter(request, response);
	        	else if(getUserRole(username).equals("user"))
	        	{
	        		/*if(req.getMethod().toUpperCase().equals("GET")) // неправильно
	        		{
	        			if(req.getRequestURI().equals("/bookings") || !req.getRequestURI().matches("customer=" + userId))
	        			res.sendError(403);
	        			else if(req.getRequestURI().equals("/customers") || !req.getRequestURI().equals("/customers/" + userId))
	        			res.sendError(403);
	        			else
	        				chain.doFilter(request, response);		
	        		}*/
	        		if(req.getMethod().toUpperCase().equals("POST") && req.getRequestURI().equals("/bookings"))
	        		chain.doFilter(request, response);
	        		if(req.getMethod().toUpperCase().equals("DELETE") && req.getRequestURI().matches("/bookings"))
	        		chain.doFilter(request, response);
	        		/*if((req.getMethod().toUpperCase().equals("PUT") && req.getRequestURI().equals("/customers/" + userId)))
	        		chain.doFilter(request, response);*/
	        	}
	        	else
	        		res.sendError(403);
	        		        	
	        } else {
	            res.sendError(401);
	        }

	    }
	
	private String getUserRole(String username)
	{
		String userRole = customerService.getByLogin(username).getRole().name();
		return userRole;
	}
	
	private boolean isAuthRequired(HttpServletRequest req) {
		
		if(req.getMethod().toUpperCase().equals("POST") && req.getRequestURI().matches("/customers"))
			return false;
		
		if (req.getMethod().toUpperCase().equals("GET")) {
			if(req.getRequestURI().matches("/bookings")) // || req.getRequestURI().matches("/customers"))
				return true;
			else
			return false;
		}

		return true;
	}
	       /* UserAuthStorage userDataStorage = appContext.getBean(UserAuthStorage.class);*/
	
	private String[] resolveCredentials(HttpServletRequest req) {
        try {
            Enumeration<String> headers = req.getHeaders("Authorization");
            String nextElement = headers.nextElement();
            String base64Credentials = nextElement.substring("Basic".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
            return credentials.split(":", 2);
        } catch (Exception e) {
            return null;
        }
    }
	
	 private boolean validateUserPassword(String username, String password) {
	        // TODO get user from DB by username and check password
		 
		 Customer userFromDB = customerService.getByLogin(username);
		 
		 if (userFromDB == null) {
	            return false;
	        }
	        String userLoginFromDB = userFromDB.getLogin();

	        return userFromDB.getPassword().equals(password);
	    }

    @Override
    public void destroy() {
    }

}
