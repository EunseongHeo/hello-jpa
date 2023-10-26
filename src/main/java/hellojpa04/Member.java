package hellojpa04;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "create_last_modified_dates_unique",
                        columnNames = {"create_date", "last_modified_date"}
                )
        }
)
public class Member {
//    [요구사항] - 필드와 컬럼 매핑
//    1. 회원은 일반 회원과 관리자로 구분해야 한다.
//    2. 회원 가입일과 수정일이 있어야 한다.
//    3. 회원을 설명할 수 있는 필드가 있어야 한다. 이 필드는 길이 제한이 없다.

    @Id
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(100) default 'EMPTY'")
    private String username;
//    name 속성           <-- 필드와 매핑할 테이블의 컬럼 이름 (DEFAULT: 객체의 필드 이름)
//    insertable 속성     <-- 등록 여부 (DEFAULT: true)
//    updatable 속성      <-- 변경 여부 (DEFAULT: true)
//    nullable 속성       <-- null 값의 허용 여부 (nullable = false 이면 NOT NULL) (DEFAULT: true, DDL-auto)
//    unique 속성         <-- 한 컬럼에 간단히 유니크 제약조건을 걸 때 (DEFAULT: false)
//                       <-- 보통 @Table(uniqueConstraints = ...) 으로 사용 (unique 제약 조건의 이름 지정 가능)
//    columnDefinition   <--  (DEFAULT: 필드의 자바 타입과 DB 타입에 맞는 적절한 컬럼 타입, DDL-auto)
//    length             <--  문자 길이 제약조건 - *String 타입만 (DEFAULT: 255, DDL-auto)
//    precision          <--  소수점을 포함한 전체 자릿수 (DEFAULT: 19)
//    scale              <--  소수의 자릿수 (DEFAULT: 2, DDL-auto)
    //    ** precision & scale      : double, ﬂoat 타입에는 적용되지 않는다.
    //                              : BigDecimal 타입 또는 BigInteger 타입에 해당
    //    ** precision              : 아주 큰 숫자나 정밀한 소수를 다루어야 할 때 사용
    //    ** scale                  : 아주 큰 숫자 또는 소수점 사용 시 사용

    @Column(precision = 19)
    private BigDecimal age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private int temp;

    public Member(){}

}
