package jpabook.jpashop.Service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //컴포넌트 스캔이 있어서 자동으로 스프링 빈에 등록이 된다.
//transaction, 쓰기 지연 혹은 버퍼링
//JPA 모든 데이터 변경은 Transactional 안에서 움직여야 한다.
//readOnly는 JPA가 조회하는 곳에서는 성능을 최적화한다. --> 전체에 넣고 쓰기에만 따로 Transactional을 넣어준다.
@Transactional(readOnly = true)
@RequiredArgsConstructor //final이 있는 필드만 가지고 생성자를 만들어 준다.
public class MemberService {

    //해당 서비스 실행 시점에 굳이 @Bean을 안 넣어도 @Autowired가 표시된 부분은 자동 주입해준다.
    //@Autowired, 생성자가 하나면 생략 가능
    private final MemberRepository memberRepository;

    //생성자 주입으로 한번 설정하면 set등으로 바꾸지 못하게
    //public MemberService(MemberRepository memberRepository) {
        //this.memberRepository = memberRepository;
    //}

    //쓰기이기 때문에 readOnly를 넣으면 안 된다.
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //멤버 리파지토리에서 이름으로 멤버를 찾아 리스트에 넣는다.
    //만일 리스트에 값이 있을 경우는 이미 존재하는 회원이라는 예외를 던진다.
    //실무에서는 멀티 스레드로 중복되는 값이 저장될 수 있으니 member의 값을 유니크로 걸어야 한다.
    private void validateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if(!findMembers.isEmpty()){
            //객체 상태가 메서드 호출을 처리하기에 적절하지 않을 때
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
