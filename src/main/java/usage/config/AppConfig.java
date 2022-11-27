package usage.config;


import entities.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class AppConfig {

    private static final AppConfig INSTANCE = new AppConfig();

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    private AppConfig() {
    }

    private SessionFactory sessionFactory;

    public void init() {
        sessionFactory = sessionFactory();
    }

    private SessionFactory sessionFactory() throws HibernateException {
        Configuration configuration = configuration();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private Configuration configuration() {
        Configuration configuration = new Configuration();
        configuration.setProperties(hibernateProperties());
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Movie.class);
        configuration.addAnnotatedClass(CinemaHall.class);
        return configuration;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        hibernateProperties.put(Environment.URL, "jdbc:mysql://localhost/cinemadb?serverTimezone=Europe/Moscow&useSSL=false&generateSimpleParameterMetadata=true");
        hibernateProperties.put(Environment.USER, "root");
        hibernateProperties.put(Environment.PASS, "password");
        hibernateProperties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        hibernateProperties.put(Environment.HBM2DDL_AUTO, "update");
        hibernateProperties.put(Environment.SHOW_SQL, "true");
        return hibernateProperties;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
