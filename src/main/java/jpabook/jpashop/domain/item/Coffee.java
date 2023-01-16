package jpabook.jpashop.domain.item;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("M")
public class Coffee extends Item {

    private int country;
    private int gram;
}
