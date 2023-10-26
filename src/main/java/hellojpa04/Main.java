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

//[String 타입 결과값 - 콘솔]
//Hibernate:
//
//    create table Member (
//        id bigint not null,
//        age integer,
//        createDate timestamp,
//***     description clob,     ***
//        lastModifiedDate timestamp,
//        roleType varchar(255),
//        name varchar(255) not null,
//        primary key (id)
//    )

//[byte[] 타입 결과값 - 콘솔]
//Hibernate:
//
//    create table Member (
//        id bigint not null,
//        age integer,
//        createDate timestamp,
//***     description blob,     ***
//        lastModifiedDate timestamp,
//        roleType varchar(255),
//        name varchar(255) not null,
//        primary key (id)
//    )
