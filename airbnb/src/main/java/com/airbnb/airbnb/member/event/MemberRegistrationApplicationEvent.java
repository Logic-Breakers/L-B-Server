package com.airbnb.airbnb.member.event;

import com.airbnb.airbnb.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberRegistrationApplicationEvent {
    private Member member;
    public MemberRegistrationApplicationEvent(Member member) {
        this.member = member;
    }
}
