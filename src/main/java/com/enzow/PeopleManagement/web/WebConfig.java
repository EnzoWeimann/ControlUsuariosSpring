package com.enzow.PeopleManagement.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Handles application language switching using interceptors,
 * and general configuration of Spring MVC controller routes
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Manage the language of the application through {@link LocaleResolver} set to "es" by default
     *
     * @return Language of the current session
     */
    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;
    }

    /**
     * Interceptor that allows you to change the language of the app using a parameter in the URL
     *
     * @return An interceptor ({@link LocaleChangeInterceptor}) listening to the "lang" parameter in the URL
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    /**
     * Adds the interceptor defined before to the interceptor chain
     *
     * @param registro registry of interceptors, to which the recently created one is added
     */
    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }

    /**
     * Register index, login, and error 403 view controllers
     *
     * @param registry registry of view controllers, to which the app view controllers are added
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/errores/403").setViewName("/errores/403");
    }
}
