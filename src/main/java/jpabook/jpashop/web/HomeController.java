package jpabook.jpashop.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //어떤 방식으로 처리할지 요청
@Slf4j //로그를 뽑아온다.
public class HomeController {

    //들어온 요청을 특정 메서드와 매핑하기 위해서 사용
    @RequestMapping("/")
    public String home(){
        log.info("home controller");
        return "home";
    }
}
