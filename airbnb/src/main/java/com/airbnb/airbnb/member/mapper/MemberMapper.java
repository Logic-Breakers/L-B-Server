package com.airbnb.airbnb.member.mapper;

import com.airbnb.airbnb.member.dto.MemberPatchDto;
import com.airbnb.airbnb.member.dto.MemberPostDto;
import com.airbnb.airbnb.member.dto.MemberResponseDto;
import com.airbnb.airbnb.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member toMember (MemberPostDto memberPostDto);
    Member toMember (MemberPatchDto memberPatchDto);
    MemberResponseDto toMemberResponseDto (Member member);
}
