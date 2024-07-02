package org.koreait;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Article> articles = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int id = 1;

        makeTestData();

        while (true) {

            System.out.print("명령어) ");
            String order = sc.nextLine();

            if (order.equals("article write")) {
                System.out.print("제목: ");
                String title = sc.nextLine();
                System.out.print("내용: ");
                String body = sc.nextLine();

                Article article = new Article(id, title, body, Util.date());

                articles.add(article);

                System.out.println(id++ + "번 글이 생성되었습니다.");

            } else if (order.equals("article list")) {
                System.out.println("번호   /   제목   /   내용");

                for (int i = articles.size() - 1; i >= 0; i--) {
                    Article article = articles.get(i);

                    System.out.println(article.getId() + "   /   " + article.getTitle() + "   /   " + article.getBody());
                }
            } else if (order.startsWith("article detail")) {
                String Id = order.substring("article detail".length()).trim();

                if (Id.isEmpty()) {
                    System.out.println("Id를 입력하지 않았습니다.");
                    continue;
                }

                int articleId = Integer.parseInt(Id);

                findArticleId(articleId);

                if (findArticleId(articleId) == null) {
                    System.out.println(articleId + "번 게시글은 없습니다");
                } else {
                    Article article = findArticleId(articleId);

                    System.out.println("번호 : " + articleId);
                    System.out.println("날짜 : " + article.getDate());
                    System.out.println("제목 : " + article.getTitle());
                    System.out.println("내용 : " + article.getBody());
                }


            } else if (order.startsWith("article delete")) {
                String Id = order.substring("article delete".length()).trim();

                if (Id.isEmpty()) {
                    System.out.println("Id를 입력하지 않았습니다.");
                    continue;
                }

                int articleId = Integer.parseInt(Id);

                findArticleId(articleId);

                if (findArticleId(articleId) == null) {
                    System.out.println(articleId + "번 게시글은 없습니다");
                } else {
                    articles.remove(articleId - 1);

                    System.out.println(articleId + "번 게시글이 삭제되었습니다.");
                }

            } else if (order.startsWith("article modify")) {
                String Id = order.substring("article modify".length()).trim();

                if (Id.isEmpty()) {
                    System.out.println("Id를 입력하지 않았습니다.");
                    continue;
                }

                int articleId = Integer.parseInt(Id);

                findArticleId(articleId);

                if (findArticleId(articleId) == null) {
                    System.out.println(articleId + "번 게시글은 없습니다.");
                } else {
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
                }

            } else if (order.equals("exit")) {
                System.out.println("프로그램 종료");
                break;
            }
        }
    }

    private static void makeTestData() {
        System.out.println("테스트 데이터 생성");

        articles.add(new Article(1, "제목1","내용1",Util.date()));
        articles.add(new Article(2,"제목2","내용2",Util.date()));
        articles.add(new Article(3,"제목3","내용3",Util.date()));

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

