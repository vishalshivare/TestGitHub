package com.gh.utils;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.gh")
public class ConfigurationClass {

	@Bean
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
	    return hemf.getSessionFactory();
	}
}
