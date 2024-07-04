package org.koreait;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Article> articles = new ArrayList<>();
    static List<Member> members = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int id = 4;
        int memberid = 1;
        byte status = 1;

        articleMakeTestData();
        memberMakeTestData();

        while (true) {

            System.out.print("명령어) ");
            String order = sc.nextLine();

            if (order.equals("member join")) {
                System.out.println("== 회원가입 ==");
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

                Member member = new Member(memberid++, regDate, loginId, loginPw, name);

                members.add(member);

                System.out.println("회원가입 완료");


            } else if (order.equals("article write")) {
                System.out.print("제목: ");
                String title = sc.nextLine();
                System.out.print("내용: ");
                String body = sc.nextLine();

                Article article = new Article(id, title, body, Util.date());

                articles.add(article);

                System.out.println(id++ + "번 글이 생성되었습니다.");

            } else if (order.startsWith("article list")) {
                System.out.println("번호   /   제목   /   내용");

                String findparttitle = order.substring("article list".length()).trim();
                if (findparttitle != "") {
                    for (Article article : articles) {
                        if (article.getTitle().contains(findparttitle)) {
                            System.out.println(article.getId() + "   /   " + article.getTitle() + "   /   " + article.getBody());
                        }
                    }
                    continue;
                }
                for (int i = articles.size() - 1; i >= 0; i--) {
                    Article article = articles.get(i);

                    System.out.println(article.getId() + "   /   " + article.getTitle() + "   /   " + article.getBody());
                }

            } else if (order.startsWith("article detail")) {
                String Id = order.substring("article detail".length()).trim();

                int articleId = Integer.parseInt(Id);

                haveId(articleId);
                findArticleId(articleId);

                Article article = findArticleId(articleId);

                System.out.println("번호 : " + articleId);
                System.out.println("날짜 : " + article.getDate());
                System.out.println("제목 : " + article.getTitle());
                System.out.println("내용 : " + article.getBody());


            } else if (order.startsWith("article delete")) {
                String Id = order.substring("article delete".length()).trim();

                int articleId = Integer.parseInt(Id);

                haveId(articleId);
                findArticleId(articleId);


                articles.remove(articleId - 1);

                System.out.println(articleId + "번 게시글이 삭제되었습니다.");


            } else if (order.startsWith("article modify")) {
                String Id = order.substring("article modify".length()).trim();

                int articleId = Integer.parseInt(Id);

                haveId(articleId);
                findArticleId(articleId);

                Article article = findArticleId(articleId);

                System.out.println("기존 제목 : " + article.getTitle());
                System.out.println("기존 내용 : " + article.getBody());
                System.out.println("=== 수정 ===");
                System.out.print("제목 : ");
                String title = sc.nextLine();

                System.out.print("내용 : ");
                String body = sc.nextLine();

                article.setTitle(title);
                article.setBody(body);

                System.out.println(articleId + "번 게시글이 수정되었습니다.");
            } else if (order.equals("exit")) {
                System.out.println("프로그램 종료");
                break;
            }
        }
    }


    private static void articleMakeTestData() {
        System.out.println("테스트 데이터 생성");

        articles.add(new Article(1, "제목1", "내용1", Util.date()));
        articles.add(new Article(2, "제목2", "내용2", Util.date()));
        articles.add(new Article(3, "제목3", "내용3", Util.date()));

    }

    private static void memberMakeTestData() {
        System.out.println("회원 데이터 생성");

        members.add(new Member(1, Util.date(), "a", "a", "a"));
        members.add(new Member(2, Util.date(), "b", "b", "b"));
        members.add(new Member(3, Util.date(), "c", "c", "c"));

    }


    public static boolean isJoinableLoginId(String loginid) {
        for (Member member : members) {
            if (member.getLoginId().equals(loginid)) {
                return false;
            }
        }
        return true;
    }

    public static void haveId(int id) {
        if (id == 0) {
            System.out.println("Id를 입력하지 않았습니다.");
            return;
        }

        if (findArticleId(id) == null) {
            System.out.println(id + "번 게시글은 없습니다.");
        }
    }

    public static Article findArticleId(int articleID) {
        for (Article article : articles) {
            if (article.getId() == articleID) {
                return article;
            }
        }
        return null;
    }

}
