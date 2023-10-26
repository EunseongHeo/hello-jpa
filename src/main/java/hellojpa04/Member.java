package hellojpa04;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity // JPA가 관리하는 엔티티임을 명시(DEFAULT => @Entity(name = "Member")) (기본 생성자 필수)
@Table(name = "MBR")
public class Member {

    @Id
    private Long id;
    @Column(name = "name", unique = true, length = 10) //DDL 생성 기능 - 런타임(JPA의 실행 로직)에 영향을 주지 않지만 어노테이션을 통해 적절한 DDL을 자동으로 생성해주는 기능
//    unique = true 지정 시, 아래의 코드가 실행됨을 확인할 수 있다.
//    =====================================
//    Hibernate:
//    alter table Member
//    add constraint UK_ektea7vp6e3low620iewuxhlq unique (name)
//    =====================================
    private String username;

    public Member(){}   //기본생성자 *필수(for @Entity)
    public Member(Long id, String username){
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
