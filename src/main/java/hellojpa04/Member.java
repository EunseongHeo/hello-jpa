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

//    @Enumerated
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
//    EnumType.ORDINAL  <-- enum 순서를 데이터베이스에 저장 (DEFAULT)
//        - 운영에서는 사용하지 말 것!!!
//        - 추가된 요구사항으로 RoleType에 대하여 상수가 추가되었을 때 큰 혼란이 야기될 수 있음
//    EnumType.STRING   <-- enum 이름을 데이터베이스에 저장

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private int temp;

    public Member(){}

//    @Enumerated 속성 비교를 위해 setters 만 생성
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

}
