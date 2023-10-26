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

    @Column(name = "name", nullable = false)
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;
//    private byte[] description;
//  	- 매핑하는 필드 타입이 문자면 CLOB 매핑, 나머지는 BLOB 매핑
//		- CLOB: String, char[], java.sql.CLOB
//		- BLOB: byte[],         java.sql.BLOB

    @Transient
    private int temp;

    public Member(){}

}
