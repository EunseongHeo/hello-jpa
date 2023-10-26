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
//        primary key (id)
//        id bigint not null,
//        name varchar(255) not null,
//        age integer,
//        roleType varchar(255),
//***     createDate timestamp,         ***
//***     lastModifiedDate timestamp,   ***
//***     testLocalDate date,           ***
//***     testLocalDateTime timestamp,  ***
//        description clob,
//    )

/*
- createDate,        testLocalDate      타입이    date으로         같음을 확인할 수 있다.
- lastModifiedDate,  testLocalDateTime  타입이    timestamp으로    같음을 확인할 수 있다.
    *createDate       <-- TemporalType.DATE      | testLocalDate     <-- LocalDate 타입
    *lastModifiedDate <-- TemporalType.TIMESTAMP | testLocalDateTime <-- LocalDateTime 타입
*/
