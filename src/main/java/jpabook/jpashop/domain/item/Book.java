package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//픽션
@Entity
@Getter @Setter
@DiscriminatorValue("B") //dtype에서 값을 f로 주겠다는 의미
public class Book extends Item {

    //작가, 출판사, 전자책 여부, isbn
    private String author;
    private String publisher;
    private int isbn;
    private boolean ebook;

}
