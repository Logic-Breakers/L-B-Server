package com.airbnb.airbnb.member.controller;

import com.airbnb.airbnb.member.dto.MemberPatchDto;
import com.airbnb.airbnb.member.dto.MemberPostDto;
import com.airbnb.airbnb.member.entity.Member;
import com.airbnb.airbnb.member.mapper.MemberMapper;
import com.airbnb.airbnb.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @PostMapping("/signup")
    public ResponseEntity postUser(@Valid @RequestBody MemberPostDto memberPostDto) {
        memberService.createMember(memberPostDto);
        return new ResponseEntity<>("계정 생성이 완료되었습니다.", HttpStatus.CREATED);
    }

    @PatchMapping()
    public ResponseEntity patchUser(@AuthenticationPrincipal Member host,
                                    @Valid @RequestBody MemberPatchDto memberPatchDto) {
        memberService.updateMember(memberPatchDto, host.getId());
        return new ResponseEntity<>("계정 정보 수정이 완료되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity deleteUser(@AuthenticationPrincipal Member member) {
        memberService.removeMember(member.getId());
        return new ResponseEntity<>("계정이 삭제되었습니다.", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getUser (@AuthenticationPrincipal Member member) {
        return new ResponseEntity<>(memberMapper.toMemberResponseDto(memberService.findVerifiedMember(member)),HttpStatus.OK);
    }

}
