package ru.heimdall.eye.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import ru.heimdall.eye.services.ApplicationCacheService;

import freemarker.log.Logger;

public abstract class AbstractController {

	protected Logger log = Logger.getLogger(this.getClass().getName());
	
	@Value( "#{wiringProperties['format.date']}" )
	protected String dateFormat;
	@Value( "#{wiringProperties['buildEnv']}" )
	protected String buildEnv;

	@Autowired
	protected ApplicationCacheService cache;

	// copied from http://digitaljoel.nerd-herders.com/2010/11/15/spring-mvc-binding-to-strings-and-dates/
	@InitBinder
	public void allowEmptyDateBinding( WebDataBinder binder ) {
	    // Allow for null values in date fields.
	    binder.registerCustomEditor( Date.class, new CustomDateEditor( new SimpleDateFormat( dateFormat), true ));
	    // tell spring to set empty values as null instead of empty string.
	    binder.registerCustomEditor( String.class, new StringTrimmerEditor( true ));
	}
	
	@ModelAttribute("buildEnv")
	public String getBuildEnv() {
		if ( buildEnv == null ) {
			buildEnv = "Unknown";
		}
		return buildEnv;
	}
	@ModelAttribute("username")
	public String setUsername() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if ( username.equals("anonymousUser")) {
			return null;
		} else {
			return username;
		}
	}
}
