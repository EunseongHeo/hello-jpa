package hellojpa03;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DetachMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            /*
            * 준영속 상태로 만드는 방법
            * 1. em.detach(entity)
            * 2. em.clear()
            * 3. em.close()
            */
            Member member = em.find(Member.class, 30L);
            member.setName("AAAAA");
            em.detach(member);

            System.out.println("DB에는 변경 사항이 없음을 확인할 수 있다.");

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
