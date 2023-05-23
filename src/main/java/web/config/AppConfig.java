package web.config;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(value = "web")
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/my_db?serverTimezone=Europe/Moscow");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setJpaVendorAdapter(getJpaVendorAdapter());
        em.setDataSource(dataSource());
        em.setPersistenceUnitName("myJpaPersistenceUnit");
        em.setPackagesToScan("web");
        em.setJpaProperties(hibernateProperties());
        return em;
    }

    @Bean
    public JpaVendorAdapter getJpaVendorAdapter() {
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return adapter;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager txManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;

    }
}
