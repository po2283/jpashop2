package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Setter @Getter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    //외래키, order에서 member로 가고 싶지만 member에서 order로 가고 싶지는 않음
    @JoinColumn(name = "member_id")
    private Member member;

    //외래키는 OrderItem에 있는 order에 있다.
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    //cascade, order을 할 때 자동으로 delivery도 함께 영속 상태로
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Delivery delivery;

    private LocalDateTime orderDate;

    //ORDER과 CANCLE의 값을 문자열 그대로
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItem.add(orderItem);
        orderItem.setOrder()
    }


}
