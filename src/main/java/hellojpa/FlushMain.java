package hellojpa;

import javax.persistence.*;

public class FlushMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            /*
            * 영속성 컨텍스트를 플러시하는 방법
            * 1. em.flush() - 직접 호출
            * 2. em.commit() - 플러시 자동 호출
            * 3. JPQL 쿼리 실행 - 플러시 자동 호출
            */
            Member member = new Member();
            member.setId(50L);
            member.setName("FlushTest");
            em.persist(member);
            em.flush();
            System.out.println("위의 INSERT SQL를 확인할 수 있음");
            System.out.println("em.flush(); 하지 않으면 이 문장이 출력된 후 INSERT SQL이 출력될 것이다.");

            /*
            * 플러시 모드 옵션
            * FlushModeType.AUTO
            *   : 커밋이나 쿼리를 실행할 때 플러시 (기본값)
            * FlushModeType.COMMIT
            *   : 커밋할 때만 플러시
            */
            em.setFlushMode(FlushModeType.COMMIT);
            em.setFlushMode(FlushModeType.AUTO);

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
