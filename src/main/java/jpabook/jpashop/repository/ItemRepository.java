package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    //item은 처음 저장할 때는 id가 없다.
    public void save(Item item){
        //만일 저장할 아이템이 새 항목이라면 영속성 컨텍스트에 넣어준다.
        if(item.getId() == null){
            em.persist(item);
        }else{
            //그게 아니면 merge
            //데이터를 완전히 바꿔치기 하는 거
            //단점, 변합은 모든 속성이 변경된다. 또한 값이 없으면 null 업테이트 위험성이 있음
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}
