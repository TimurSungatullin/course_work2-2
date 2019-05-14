package ru.kpfu.itis.config;

import org.springframework.core.annotation.Order;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

@Order(1)
public class WebInitializer extends AbstractDispatcherServletInitializer {

  @Override
  protected WebApplicationContext createRootApplicationContext() {
    AnnotationConfigWebApplicationContext cxt = new AnnotationConfigWebApplicationContext();
    cxt.register(RootConfig.class, SecurityConfig.class);
    return cxt;
  }

  @Override
  protected WebApplicationContext createServletApplicationContext() {
    AnnotationConfigWebApplicationContext cxt = new AnnotationConfigWebApplicationContext();
    cxt.register(WebConfig.class);
    return cxt;
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

}
