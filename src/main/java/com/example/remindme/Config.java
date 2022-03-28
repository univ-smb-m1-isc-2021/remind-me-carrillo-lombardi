package com.example.remindme;

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

	 
	@Bean(name = "localeResolver")
	public LocaleResolver getLocaleResolver()  {
		CookieLocaleResolver resolver= new CookieLocaleResolver();
		resolver.setCookieDomain("myAppLocaleCookie");
		// 60 minutes 
		resolver.setCookieMaxAge(60*60); 
		return resolver;
	} 
	
	@Bean(name = "messageSource")
	public MessageSource getMessageResource()  {
		ReloadableResourceBundleMessageSource messageResource= new ReloadableResourceBundleMessageSource();
		
		messageResource.setBasename("classpath:translations/messages");
		messageResource.setDefaultEncoding("UTF-8");
		return messageResource;
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		localeInterceptor.setParamName("lang");
		
		
		registry.addInterceptor(localeInterceptor).addPathPatterns("/*");
	}
	
}

// import org.springframework.context.MessageSource;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.support.ResourceBundleMessageSource;
// import org.springframework.web.servlet.LocaleResolver;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// import org.springframework.web.servlet.i18n.CookieLocaleResolver;

// @Configuration
// public class Config implements WebMvcConfigurer {

//     @Bean("messageSource")
//     public MessageSource messageSource() {
//         ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//         messageSource.setBasenames("resources/translations/messages");
//         messageSource.setDefaultEncoding("UTF-8");
//         return messageSource;
//     }

//     //Language du navigateur
//     @Bean
//     public LocaleResolver localeResolver() {
//         return new CookieLocaleResolver();
//     }
// }
