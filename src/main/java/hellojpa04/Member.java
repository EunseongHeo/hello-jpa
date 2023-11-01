package hellojpa04;

import javax.persistence.*;
import java.util.Date;

@Entity
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",  //식별자 생성기 이름    (필수)
        table = "MY_SEQUENCES",         //키생성 테이블명       (기본값: hibernate_sequences)
        pkColumnValue = "MEMBER_SEQ",   //키로 사용할 값 이름   (기본값: 엔티티 이름)
        allocationSize = 50  //시퀀스 한 번 호출에 증가하는 수    (기본값: 50)
)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
        generator = "MEMBER_SEQ_GENERATOR"
    )
    private Long id;

    @Column(name = "name", nullable = false)
    private String userName;

    public Member(){}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }
}

/* @TableGenerator - 속성
	속성 			        설명 							                기본값
	name 			        식별자 생성기 이름 					            필수
	table 			        키생성 테이블명 					                hibernate_sequences
	pkColumnName 		    시퀀스 컬럼명 						            sequence_name
	valueColumnName		    시퀀스 값 컬럼명 					                next_val
	pkColumnValue 		    키로 사용할 값 이름 					            엔티티 이름
	initialValue 		    초기 값, 마지막으로 생성된 값이 기준이다.             0
	allocationSize 		    시퀀스 한 번 호출에 증가하는 수(성능 최적화에 사용됨) 	50
	catalog, schema 	    데이터베이스 catalog, schema 이름
	uniqueConstraints(DDL)  유니크 제약 조건을 지정 가능

 */
