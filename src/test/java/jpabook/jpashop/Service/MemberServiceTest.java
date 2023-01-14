package jpabook.jpashop.Service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) //juni으로 하면 스프링으로
@SpringBootTest //스프링 컨테이너 안에서 테스트 돌린다.
@Transactional //롤벡을 자동으로 지원해서 한 태스트를 실행하고 롤벡한 후 다른 테스트를 진행한다. --> commit을 안 함
//같은 영속성 안에서 같은 엔티티이면 하나로 관리
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception{
        //Given
        Member member = new Member();
        member.setName("kim");
        //When
        Long saveId = memberService.join(member);
        //Then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    //try, catch를 안 쓰도록
    //예외가 터져서 나간 애가 IllegalStateException이면 된다.
    public void 중복_회원_예외() throws Exception{
        //Given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //When
        memberService.join(member1);
        memberService.join(member2);

        /*
        memberService.join(member1);
        try{
            memberService.join(member2);
        }catch(IllegalStateException e){
            return
        }
         */

        //Then
        //코드가 돌다가 여기 오면 안 되는 거. 여기 오면 문제가 생겼음.
        fail("예외가 발생해야 한다.");
    }

}