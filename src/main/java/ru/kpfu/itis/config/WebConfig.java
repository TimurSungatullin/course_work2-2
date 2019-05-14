package ru.kpfu.itis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import ru.kpfu.itis.converter.StringToCityConvert;
import ru.kpfu.itis.converter.StringToCountryConvert;

import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "ru.kpfu.itis.controller",
        "ru.kpfu.itis.model",
        "ru.kpfu.itis.converter",
        "ru.kpfu.itis.service"})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private StringToCityConvert stringToCityConvert;

    @Autowired
    private StringToCountryConvert stringToCountryConvert;


    @Bean
    public ViewResolver viewResolver() {
    FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
    resolver.setCache(false);
    resolver.setPrefix("");
    resolver.setSuffix(".ftl");
    resolver.setRequestContextAttribute("context");
    resolver.setContentType("text/html;charset=UTF-8");
    return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer config = new FreeMarkerConfigurer();
        config.setTemplateLoaderPath("/WEB-INF/ftl/");
        config.setDefaultEncoding("UTF-8");
        config.setFreemarkerSettings(new Properties() {{
            this.put("default_encoding", "UTF-8");
        }});
        return config;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("home");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:validation");
        messageSource.setCacheSeconds(0);
        messageSource.setUseCodeAsDefaultMessage(false);
        return messageSource;
    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {
        formatterRegistry.addConverter(stringToCityConvert);
        formatterRegistry.addConverter(stringToCountryConvert);
    }

}
