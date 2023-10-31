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
            /*
            //  Member 테이블 CREATE SQL을 미리 실행 시켜놓기
                create table member(
                    id bigint generated by default as identity,
                    name varchar(255),
                    primary key (id)
                );
             */

            Member member = new Member();
            member.setUsername("C");
            System.out.println("============start==========");
            em.persist(member);
            System.out.println ("member.getId() = " + member.getId());

            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
