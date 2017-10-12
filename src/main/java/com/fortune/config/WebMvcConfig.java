package com.fortune.config;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

import javax.sql.DataSource;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  @Autowired
  @Qualifier("dataSource")
  DataSource dataSource;
  @Autowired
  private ApplicationContext appContext;

  @Bean(name = "bCryptPasswordEncoder")
  public BCryptPasswordEncoder passwordEncoder() {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;
  }

  @Bean
  public JasperReportsViewResolver getJasperReportsViewResolver() {

    JasperReportsViewResolver resolver = new JasperReportsViewResolver();
    resolver.setPrefix("classpath:jasperreports/");
    resolver.setSuffix(".jrxml");

    resolver.setReportDataKey("datasource");
    resolver.setViewNames("*rpt_*");
    resolver.setViewClass(JasperReportsMultiFormatView.class);
    resolver.setOrder(0);
    return resolver;
  }

  @Bean
  JRDataSource jrDataSource(){
    return  new JRDataSource() {
      @Override
      public boolean next() throws JRException {
        return false;
      }

      @Override
      public Object getFieldValue(JRField jrField) throws JRException {
        return null;
      }
    };
  }



}