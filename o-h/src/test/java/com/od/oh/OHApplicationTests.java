package com.od.oh;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.od.oh.model.domain.OldDriver;

@SpringBootTest
public class OHApplicationTests {

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
        // session.save(od);
        // session.delete(od);
        // session.update(od);

        session.load(OldDriver.class, 1L);

        System.out.println("=======================第一次查询======================= session --> " + session.hashCode());
        System.out
                .println("=======================End======================= --> " + session.find(OldDriver.class, 1L));
        session.getTransaction().commit();
        // session.update(od);

        Session session2 = sessionFactory.openSession();
        System.out.println("=======================第二次查询======================= session --> " + session.hashCode());
        System.out.println(
                "=======================End======================= ---> " + session2.find(OldDriver.class, 1L));

    }

}
