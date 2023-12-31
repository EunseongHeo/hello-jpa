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

            System.out.println("=============================");

            for(int i=0; i<99; i++){
                Member member = new Member();
                member.setUserName("A");
                em.persist(member);
                System.out.println("member.id = " + member.getId());
            }

            System.out.println("=============================");

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

//특징
//1. DB에 적합한 전략을 선택
//2. H2의 경우 시퀀스 전략을 많이 사용하므로 @SequenceGenerator 어노테이션 및 속성을 지정함

//[실행 결과 - 콘솔]
//Hibernate: create sequence MEMBER_SEQ start with 1 increment by 50
//=============================
//Hibernate:
//    call next value for MEMBER_SEQ
//Hibernate:
//    call next value for MEMBER_SEQ
//member.id = 1
//member.id = 2
//member.id = 3
//member.id = 4
//member.id = 5
//member.id = 6
//...(생략)
//member.id = 50
//member.id = 51
//Hibernate:
//call next value for MEMBER_SEQ
//member.id = 52
//...(생략)
//member.id = 98
//member.id = 99
//=============================
//  (INSERT SQL 생략)

//[실행 결과 - DB 시퀀스: MY_SEQUENCES ]
//현재 값    101
//증가      50
