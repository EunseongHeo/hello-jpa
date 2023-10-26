package hellojpa03;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

//        [회원 등록]
        EntityManager em1 = emf.createEntityManager();
        EntityTransaction tx1 = em1.getTransaction();
        tx1.begin();
        try {
            for(Long i = 0L; i<20; i++) {
                Member member = new Member();
                member.setId(i+1);
                member.setName("Hello" + (char)(i+65));
                em1.persist(member);
            }
        } catch (Exception e){
            tx1.rollback();
        } finally {
            em1.close();
        }

//        [회원 단건 조회]
        EntityManager em2 = emf.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();
        tx2.begin();
        try {
            Member findMember = em2.find(Member.class, 1L);
            System.out.println("member.name that id is 1L: " + findMember.getName());
        } catch (Exception e){
            tx2.rollback();
        } finally {
            em2.close();
        }

//        [회원 수정]
        EntityManager em3 = emf.createEntityManager();
        EntityTransaction tx3 = em3.getTransaction();
        tx3.begin();
        try {
            Member findMember = em3.find(Member.class, 1L);
            findMember.setName("HelloJPA");
            tx3.commit();
        } catch (Exception e){
            tx3.rollback();
        } finally {
            em3.close();
        }

//        [회원 삭제]
        EntityManager em4 = emf.createEntityManager();
        EntityTransaction tx4 = em4.getTransaction();
        tx4.begin();
        try {
            Member findMember = em4.find(Member.class, 19L);
            em4.remove(findMember);
            tx4.commit();
        } catch (Exception e){
            em4.close();
        }

//        [회원 다건 조회]
        EntityManager em5 = emf.createEntityManager();
        EntityTransaction tx5 = em5.getTransaction();
        tx5.begin();
        try {
            List<Member> result = em5.createQuery("select m from Member as m", Member.class)
//                    paging
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member: result){
                System.out.println("member.name: " + member.getName());
            }

            tx5.commit();
        } catch (Exception e){
            tx5.rollback();
        } finally {
            em5.close();
        }

        emf.close();
    }
}
