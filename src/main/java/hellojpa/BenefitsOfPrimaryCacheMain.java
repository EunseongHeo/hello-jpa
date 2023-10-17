package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BenefitsOfPrimaryCacheMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
//          1. 1차 캐시
//                :1차 캐시에서 조회
            Member member = new Member();
            member.setId(40L);
            member.setName("cache1");
//            1차 캐시에 저장됨
            em.persist(member);
//            1차 캐시에서 조회
            Member findOne = em.find(Member.class, 40L);
//          - (조회) DB에서 조회한 내용을 1차 캐시에 저장할 수 있다.
//          - (등록) INSERT 쿼리문 이전에 아래의 출력 코드가 정상적으로 작동함을 확인할 수 있다.
            System.out.println("findOne.id = " + findOne.getId());
            System.out.println("findOne.name = " + findOne.getName());

            System.out.println("==================================================================");


//          2. 동일성(identity) 보장
            Member a = em.find(Member.class, 20L);
            Member b = em.find(Member.class, 20L);
//          - (동일성 비교 출력값: true)
            System.out.println("동일성 비교: " + (a == b));


//          3. 트랜잭션을 지원하는 쓰기 지연 
//          - (transactional write-behind)
//          - `tx.commit();` 일 때, 데이터베이스에 쓰기 지연 SQL 저장소 내의 모든 쿼리를 전송한다.


//          4. 변경 감지(Dirty Checking)
            Member toUdate = em.find(Member.class, 1L);
            toUdate.setName("Update");
//          em.update(toUpdate); 이런 코드가 있어야 하지 않을까?
//          Nope
//          : 1차 캐시의 Entity와 스냅샷을 비교하여 변경을 감지한다.
//          : 변경이 있을 경우, UPDATE SQL을 생성하여 쓰기 지연 SQL 저장소에 저장한다.
//          : 그리고 tx.commit(); 시, 쓰기 지연 SQL 저장소에 저장된 모든 쿼리를 일괄 처리한다.

//          5. 지연 로딩(Lazy Loading)

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
