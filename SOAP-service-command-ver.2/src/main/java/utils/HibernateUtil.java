package utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private volatile static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().
                configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().
                build();

        SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();

        return sessionFactoryBuilder.build();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            synchronized (HibernateUtil.class) {
                sessionFactory = buildSessionFactory();
            }
        }
        return sessionFactory;
    }

    public static void close() {
        if (sessionFactory.isOpen()) {
            sessionFactory.close();
        }
    }
}
