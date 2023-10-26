package hellojpa04;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Member")
public class Member {

    @Id
    private Long id;

    private String username;

    public Member(){}   //기본 생성자

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
