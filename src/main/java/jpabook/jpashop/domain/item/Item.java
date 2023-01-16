package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
//상속 관계 매핑, 단일 테이블 전략으로(한 테이블에 모든 정보를 넣는 거)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//Dtype를 설정, 엔티티 명으로 들어간다
//dtype를 바꾸고 싶으면 자식 엔티티에 가서 DiscriminationValue("xxx")이렇게 바꾸면 된다.
@DiscriminatorColumn(name = "dtype")
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items") //반대편인 category에서 FK를 가지고 category_item이라는 새로운 테이블을 만든다.
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
