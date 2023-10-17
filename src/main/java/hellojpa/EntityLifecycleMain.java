package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityLifecycleMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

//            비영속(New)
//            : 객체를 생성한 상태
            Member member = new Member();
            member.setId(32L);
            member.setName("member3");

//            영속(Managed)
//            : 영속성 컨텍스트에 관리되는 상태
//            : 쓰기 지연 SQL 저장소에 필요한 쿼리를 저장하고 1차 캐시에 저장한 상태.
            em.persist(member);
//            : 수정할 객체를 가져온 상태
            Member findMember = em.find(Member.class, 21L);
            findMember.setName("JPQL");

//            준영속(Detached)
//            : 회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
//            : 쓰기 지연 SQL 저장소에 member 객체와 관련된 쿼리를 제거하고
//            : 1차 캐시에 저장된 member 객체와 관련된 데이터를 삭제한다.
//            em.clear(); //<-- 쓰기 지연 SQL 저장소의 모든 쿼리와 1차 캐시에 저장된 데이터를 모두 삭제한다.
            em.detach(findMember);
            em.detach(member);

//            삭제(Remove)
//            : 객체를 삭제한 상태
            em.remove(findMember);

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
