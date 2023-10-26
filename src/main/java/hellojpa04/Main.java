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

            Member member = new Member();
            member.setId(1L);
            member.setUsername("A");
            member.setRoleType(RoleType.USER);

            em.persist(member);

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

//[@Enumerated(EnumType.ORDINAL) 경우의 결과값-콘솔]
//Hibernate:
//
//    create table Member (
//        primary key (id)
//        id bigint not null,
//        age integer,
//        createDate timestamp,
//        description clob,
//        lastModifiedDate timestamp,
//***     roleType integer,     ***
//        name varchar(255) not null,
//    )
//=============================================================================
//[@Enumerated(EnumType.ORDINAL) 경우의 결과값-DB]
//| ID | AGE | CREATEDATE | DESCRIPTION | LASTMODIFIEDDATE | ROLETYPE | NAME |
//|----|-----|------------|-------------|------------------|----------|------|
//| 1  | null| null       | null        | null             | 0        | A    |



//[@Enumerated(EnumType.STRING) 경우의 결과값-콘솔]
//Hibernate:
//
//    create table Member (
//        primary key (id)
//        id bigint not null,
//        age integer,
//        createDate timestamp,
//        description clob,
//        lastModifiedDate timestamp,
//***     roleType varchar(255),     ***
//        name varchar(255) not null,
//    )
//=============================================================================
//[@Enumerated(EnumType.STRING) 경우의 결과값-DB]
//| ID | AGE | CREATEDATE | DESCRIPTION | LASTMODIFIEDDATE | ROLETYPE | NAME |
//|----|-----|------------|-------------|------------------|----------|------|
//| 1  | null| null       | null        | null             | USER     | A    |
