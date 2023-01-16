package jpabook.jpashop.Service;


import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Test
    @Rollback(false)
    public void 상품등록() throws Exception{
        //Given
        Item item = new Item();
        item.setName("ItemA");

        //when
        itemService.saveItem(item);
        Item findItem = itemService.findOne(item.getId());

        //Then
        assertEquals(item, findItem);
    }



}
