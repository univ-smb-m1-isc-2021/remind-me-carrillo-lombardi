package com.example.remindme.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration 
public class Config implements WebMvcConfigurer {
	 
    //save as cookie the language
	@Bean(name = "localeResolver")
	public LocaleResolver getLocaleResolver()  {
		CookieLocaleResolver resolver= new CookieLocaleResolver();
		resolver.setCookieDomain("myAppLocaleCookie");
		// 60 minutes 
		resolver.setCookieMaxAge(60*60); 
		return resolver;
	} 
	
    //configre messages
	@Bean(name = "messageSource")
	public MessageSource getMessageResource()  {
		ReloadableResourceBundleMessageSource messageResource= new ReloadableResourceBundleMessageSource();
		
		messageResource.setBasename("classpath:translations/messages");
		messageResource.setDefaultEncoding("UTF-8");
		return messageResource;
	}

    //if we add a "lang" parameter in the url
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		localeInterceptor.setParamName("lang");
		registry.addInterceptor(localeInterceptor);	//.addPathPatterns("/*"); //si on veut traduire que certaine pages
	}
}
