package jpabook.jpashop.web;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {

    //상품 수정이 있어서 id가 있어야 한다.
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String publisher;
    private int isbn;
    private boolean ebook;

}
