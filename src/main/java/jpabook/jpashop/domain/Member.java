package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    //임베디드 타입 -> 기본 값 타입을 모아서 만들어 놓은 복합 값 타입입
    @Embedded
    private Address address;

    //order에 있는 member하고 ont to many
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
