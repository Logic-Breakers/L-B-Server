package com.airbnb.airbnb.mapper;

import com.airbnb.airbnb.dto.MemberPatchDto;
import com.airbnb.airbnb.dto.MemberPostDto;
import com.airbnb.airbnb.dto.MemberResponseDto;
import com.airbnb.airbnb.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member toMember (MemberPostDto memberPostDto);
    Member toMember (MemberPatchDto memberPatchDto);
    MemberResponseDto toMemberResponseDto (Member member);
}
