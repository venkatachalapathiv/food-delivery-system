package com.jsp.foodapp.helper;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebIntializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {Confugurationclass.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

}
   