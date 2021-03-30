package config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Properties;

public class JPAConfiguration {


      @Bean
      public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

            LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

            JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

            factoryBean.setJpaVendorAdapter(vendorAdapter);

            DriverManagerDataSource dataSource = new DriverManagerDataSource();

            dataSource.setUsername("");
            dataSource.setPassword("");
            dataSource.setUrl("jdbc:h2:mem:default");
            dataSource.setSchema("public");
            dataSource.setDriverClassName("org.h2.Driver");


            factoryBean.setDataSource(dataSource);

            Properties props = new Properties();

            props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            props.setProperty("hibernate.show_sql", "true");
            props.setProperty("hibernate.hbm2ddl.auto", "update");

            factoryBean.setPackagesToScan("br.com.casadocodigo.loja.model");

            return factoryBean;
      }


}
