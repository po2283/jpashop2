package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //영속성 컨텍스트, 엔티티를 저장하는 논리적인 영역
    // EntityManager을 이용해서 영속성 컨텍스트에 접근한다.
    //@PersistenceContext
    //영속 컨텍스트에 접근해서 엔티티에 대한 DB 작업 제공
    //private EntityManager em;

    private final EntityManager em;

    //영속성 컨텍스트에 member을 넣는다
    public void save(Member member){
        em.persist(member);
    }

    //엔티티가 Member이고 Id가 id인 멤버를 찾아서 반환
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    //외부에서 받은 nam을 쿼리의 파라미터로
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name =: name", Member.class)
                .setParameter("name", name).getResultList();
    }
}
