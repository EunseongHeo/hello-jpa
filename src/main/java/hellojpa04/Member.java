package hellojpa04;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {
//    [요구사항] - 필드와 컬럼 매핑
//    1. 회원은 일반 회원과 관리자로 구분해야 한다.
//    2. 회원 가입일과 수정일이 있어야 한다.
//    3. 회원을 설명할 수 있는 필드가 있어야 한다. 이 필드는 길이 제한이 없다.

    @Id
    private Long id;

    @Column(name = "name")  //컬럼 매핑
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)    //enum 타입 매핑
    private RoleType roleType;

//    TemporalType은     DATE(날짜), TIME(시간), TIMESTAMP(날짜 + 시간) 이 있다.
//    DB는 보통 날짜, 시간, 날짜+시간 을 구분해서 쓰기 때문에 타입을 지정해줘야 한다.
    @Temporal(TemporalType.TIMESTAMP)   //날짜 타입 매핑
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)   //날짜 타입 매핑
    private Date lastModifiedDate;

//    데이터베이스에 varchar를 넘어서는 큰 컨텐스를 넣고 싶을 때 사용한다.
    @Lob    //BLOB, CLOB_문자타입 매핑
    private String description;

    @Transient
    private int temp;   //매핑 무시 <-- 메모리에서만 쓰고 싶을 때

    public Member(){}

}
