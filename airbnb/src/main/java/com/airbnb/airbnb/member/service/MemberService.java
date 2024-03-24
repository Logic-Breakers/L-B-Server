package com.airbnb.airbnb.member.service;

import com.airbnb.airbnb.member.event.MemberRegistrationApplicationEvent;
import com.airbnb.airbnb.member.dto.MemberPatchDto;
import com.airbnb.airbnb.member.dto.MemberPostDto;
import com.airbnb.airbnb.member.entity.Member;
import com.airbnb.airbnb.exception.BusinessLogicException;
import com.airbnb.airbnb.exception.ExceptionCode;
import com.airbnb.airbnb.member.mapper.MemberMapper;
import com.airbnb.airbnb.member.repository.MemberRepository;
import com.airbnb.airbnb.auth.utils.CustomAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;
    private final ApplicationEventPublisher publisher;
    private final CustomAuthorityUtils authorityUtils;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member createMember(MemberPostDto memberPostDto) {
        Member member = memberMapper.toMember(memberPostDto);
        //Password 암호화
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);
        //DB에 User Role 저장
        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);
        member.setCreatedAt(LocalDateTime.now());
        Member savedMember = memberRepository.save(member);

        publisher.publishEvent(new MemberRegistrationApplicationEvent(savedMember));
        return member;
    }

    @Transactional
    public void updateMember(MemberPatchDto memberPatchDto, Long id) {
        Member findMember = findVerifiedMember(id);
        Optional.ofNullable(memberPatchDto.getName())
                .ifPresent(userName -> findMember.setUserName(userName));
        Optional.ofNullable(memberPatchDto.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone));
        Optional.ofNullable(memberPatchDto.getEmail())
                .ifPresent(email -> findMember.setEmail(email));
        Optional.ofNullable(memberPatchDto.getPassword())
                .ifPresent(password -> findMember.setPassword(passwordEncoder.encode(password)));
    }

    @Transactional(readOnly = true)
    public Member findVerifiedMember(Long id) {
        return memberRepository.findById(id)
                 .orElseThrow(()-> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Member findVerifiedMember(Member member) {
        Long id = member.getId();
        return memberRepository.findById(id)
                .orElseThrow(()-> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }


    @Transactional
    public void removeMember(Long id) {
        memberRepository.delete(findVerifiedMember(id));
    }


}
