package hyeong.backend.controller;

import hyeong.backend.Dto.Member.MemberDto;
import hyeong.backend.Service.MemberServiceImpl;
import hyeong.backend.aop.LogTrace.LogTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    MemberServiceImpl memberService;

    @LogTrace
    @PostMapping("/join")
    public String join(@PathVariable String username , @PathVariable int age){
       memberService.save(username , age);
       return "save";
    }

    @LogTrace
    @PostMapping("/check")
    public Optional<MemberDto> check(Long id){
        Optional<MemberDto> memberDto = memberService.find(id);
        return memberDto;
    }

    @LogTrace
    @GetMapping("/all")
    public Page<MemberDto> all() {

        PageRequest page = PageRequest.of(0, 10, null);
        Pageable pageable = Pageable.unpaged();
        Page<MemberDto> members = memberService.findAll(pageable);
        return members;
    }

}
