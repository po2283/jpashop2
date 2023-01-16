package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
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
    @JoinColumn(name = "delivery_id") //일대일 관계에서 order가 delivery의 id를 FK로 가진다(일대일은 둘 중 아무에게만 하나만 가지면 됨)
    private Delivery delivery;

    private LocalDateTime orderDate;

    //ORDER과 CANCLE의 값을 문자열 그대로
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //==연관관계 편입 메서드==//
    //컨트롤 하는 쪽이 들고 있는 게 좋다
    public void setMember(Member member){
        this.member = member;
        //양 방향, 멤버에서 오더 리스트를 가지고 와서 현재 오더를 가지고 온다.
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드==//
    //...의 의미, 해당 변수가 배열이가 단일 변수여도 된다.
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);

        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }

        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비즈니스 로직==//
    /** 주문 취소 */
    public void cancel(){
        if(delivery.getDeliveryStatus() == DeliveryStatus.COMP){
            throw  new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(OrderStatus.CANCLE);
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }

    }

    //==조회 로직==//
    /** 전체 주문 가격 조회 */
    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

}
