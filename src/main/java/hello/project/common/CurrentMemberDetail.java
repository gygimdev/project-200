package hello.project.common;

import hello.project.domain.Member;
import hello.project.security.MemberDetails;

public class CurrentMemberDetail {

    public static Member getLoginMember(MemberDetails memberDetails) {
        return memberDetails.getMember();
    }

    public static String getLoginMemberEmail(MemberDetails memberDetails) {
        return memberDetails.getUsername();
    }
}
