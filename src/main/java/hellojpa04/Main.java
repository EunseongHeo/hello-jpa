package hellojpa04;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

//[결과값-콘솔]
//Hibernate:
//
//    create table Member (
//        id bigint not null,
//        age decimal(19,0),
//        create_date timestamp,
//        description clob,
//        last_modified_date timestamp,
//        roleType varchar(255),
//        name varchar(100) default 'EMPTY' not null,
//        primary key (id)
//    )
//
//Hibernate:
//
//    alter table Member
//        add constraint create_last_modified_dates_unique unique (create_date, last_modified_date)
