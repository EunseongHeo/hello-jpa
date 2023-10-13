package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {
    @Id
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

//  @Entity: JPA를 사용하는 것으로 인식하게 하는 어노테이션
//  @Table(name = "USER"): 실제 DB의 테이블명이 클래스명과 다를 경우, 실제 DB의 테이블을 지정하는 어노테이션
//  @Id: ==PK
//  @Column(name = "username"):실제 DB의 컬럼명이 객체명과 다를 경우, 실제 DB의 컬럼을 지정하는 어노테이션
