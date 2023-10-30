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
            //@Id를 사용하여 기본키를 직접 할당할 경우에는 아래와 같이 직접 할당하면 된다.
            //이 경우, Member 클래스의 id 객체가 String 타입이어도 가능하다.
            member.setId("ID_A");   //직접 할당
            member.setUsername("C");
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

//[결과값 - DB]
//| ID   | AGE  | CREATEDATE | DESCRIPTION | LASTMODIFIEDDATE | ROLETYPE | NAME |
//|------|------|------------|-------------|------------------|----------|------|
//| ID_A | null | null       | null        | null             | null     | C    |
