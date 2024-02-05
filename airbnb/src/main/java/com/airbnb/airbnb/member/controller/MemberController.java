package com.airbnb.airbnb.member.controller;

import com.airbnb.airbnb.dto.MemberPatchDto;
import com.airbnb.airbnb.dto.MemberPostDto;
import com.airbnb.airbnb.mapper.MemberMapper;
import com.airbnb.airbnb.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PatchMapping("/{user-id}")
    public ResponseEntity patchUser(@Valid @RequestBody MemberPatchDto memberPatchDto,
                                    @PathVariable ("user-id") Long id) {
        memberService.updateMember(memberPatchDto, id);
        return new ResponseEntity<>("계정 정보 수정이 완료되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity deleteUser(@PathVariable ("user-id") Long id) {
        memberService.removeMember(id);
        return new ResponseEntity<>("계정이 삭제되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity getUser (@PathVariable ("user-id") Long id) {
        return new ResponseEntity<>(memberMapper.toMemberResponseDto(memberService.findVerifiedMember(id)),HttpStatus.OK);
    }

}
