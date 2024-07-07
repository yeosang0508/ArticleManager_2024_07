package org.koreait.Controller;

import org.koreait.Member;
import org.koreait.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {
    private Scanner sc;
    private List<Member> members;
    private String cmd;
    private Member loginMember = null;

    private int memberid = 1;

    public MemberController(Scanner sc) {
        this.sc = sc;
        members = new ArrayList<>();
    }

    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;

        switch (actionMethodName) {
            case "join":
                doJoin();
                break;
            case "login":
                doLogin();
                break;
            default:
                System.out.println("명령어 확인 (actionMethodName) 오류");
                break;
        }
    }

    private boolean isLogined() {
        return loginMember != null;
    }

    private void doLogout() {
        if (!isLogined()) {
            System.out.println("이미 로그아웃 되었습니다.");
            return;
        }
        loginMember = null;

        System.out.println("로그아웃 되었습니다.");

    }


    private void doLogin() {

        if (isLogined()) {
            System.out.println("이미 로그인 중 입니다.");
            return;
        }

        System.out.print("아이디 : ");
        String loginId = sc.nextLine();

        System.out.print("비밀번호 : ");
        String loginPw = sc.nextLine();

        Member member = getMember(loginId);

        if (member == null) {
            System.out.println("로그인 정보를 찾을 수 없습니다. 다른 아이디 또는 회원가입 해주세요.");
            return;
        }

        if (member.getLoginPw().equals(loginPw) == false) {
            System.out.println("비밀번호가 다릅니다.");
            return;
        }


        loginMember = member;
        System.out.println(member.getName() + "님 로그인 되었습니다.");


    }


    private void doJoin() {

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

        System.out.println(name + "님 회원가입 되었습니다.");
    }


    private Member getMember(String loginId) {
        for (Member member : members) {
            if (member.getLoginId().equals(loginId)) {
                return member;
            }
        }
        return null;
    }

    private boolean isJoinableLoginId(String loginid) {
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
