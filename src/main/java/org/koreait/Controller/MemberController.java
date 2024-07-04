package org.koreait.Controller;

import org.koreait.Member;
import org.koreait.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {
    Scanner sc;
    List<Member> members;
    private int memberid = 1;

    public MemberController(Scanner sc) {
        this.sc = sc;
        members = new ArrayList<>();
    }

    public void doJoin() {

        System.out.println("== 회원가입 ==");
        int id = memberid + 1;
        String regDate = Util.date();
        String loginId = null;
        String loginPw = null;

        while (true) {
            System.out.print("아이디 : ");
            loginId = sc.nextLine().trim();

            if (isJoinableLoginId(loginId) == false) {
                System.out.println("아이디 중복, 다른 아이디를 입력해주세요.");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("비밀번호 : ");
            loginPw = sc.nextLine();

            System.out.print("비밀번호 확인 : ");
            String checkPw = sc.nextLine();

            if (!loginPw.equals(checkPw)) {
                System.out.println("비밀번호가 다릅니다. 다시 입력해주세요.");
                continue;
            }
            System.out.println("비밀번호 일치");
            break;
        }
        System.out.print("사용자 이름 : ");
        String name = sc.nextLine();
        Member member = new Member(id++, regDate, loginId, loginPw, name);

        members.add(member);

        System.out.println("회원가입 완료");
    }

    public boolean isJoinableLoginId(String loginid) {
        for (Member member : members) {
            if (member.getLoginId().equals(loginid)) {
                return false;
            }
        }
        return true;
    }

    public void memberMakeTestData() {
        System.out.println("회원 데이터 생성");

        members.add(new Member(1, Util.date(), "a", "a", "a"));
        members.add(new Member(2, Util.date(), "b", "b", "b"));
        members.add(new Member(3, Util.date(), "c", "c", "c"));

    }

}
