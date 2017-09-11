package com.od.oh;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.od.oh.model.domain.OldDriver;

@SpringBootTest
public class OHApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(OHApplicationTests.class);

    @Test
    public void contextLoads() {

        Configuration configuration = new Configuration();

        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(OldDriver.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        Session session = sessionFactory.openSession();

        OldDriver od = new OldDriver();
        session.beginTransaction();
//
//
//
//
//        // session.save(od);
//        // session.delete(od);
//        // session.update(od);
//
////        session.load(OldDriver.class, 1L);
//
        logger.info("=======================第一次查询======================= session --> " + session.hashCode());
        logger.info("=======================End======================= --> " + session.find(OldDriver.class, 1L));
        session.getTransaction().commit();
        // session.update(od);

        Session session2 = sessionFactory.openSession();
        logger.info("=======================第二次查询======================= session --> " + session.hashCode());
        logger.info("=======================End======================= ---> " + session2.find(OldDriver.class, 1L));

        logger.info("hello world");

    }

}
