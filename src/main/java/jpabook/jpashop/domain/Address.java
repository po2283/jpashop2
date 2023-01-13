package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter //값이 바뀌지 않도록
public class Address {

    private String city;
    private String street;
    private String zipcode;

    //프록시 같은 기술을 서야 해서 기본 생성자를 만들어 줘야 한다.
    protected Address(){
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
