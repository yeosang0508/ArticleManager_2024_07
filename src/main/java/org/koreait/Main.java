package org.koreait;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Article> articles = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int id = 1;

        while (true) {
            System.out.println("== 프로그램 시작 ==");

            System.out.print("명령어) ");
            String cmd = sc.nextLine();


            if (cmd.equals("article write")) {
                System.out.println("== 게시글 작성 ==");
                System.out.print("제목: ");
                String title = sc.nextLine();

                System.out.print("내용: ");
                String body = sc.nextLine();

                Article article = new Article(id, title, body);

                articles.add(article);

                System.out.println(id++ + "번 글이 생성되었습니다");


            } else if (cmd.equals("article list")) {
                System.out.println("== 게시글 목록 ==");
                System.out.println("번호   /   제목   /   내용");

                for (int i = articles.size() - 1; i >= 0; i--) {
                    Article article = articles.get(i);

                    System.out.println(article.getId() + "   /   " + article.getTitle() + "   /   " + article.getBody());
                }

            } else if (cmd.startsWith("article detail")) {
                int findId = Integer.parseInt(cmd.substring("article detail ".length()).trim());

                Article article = FindId(findId);

                if (article == null) {
                    System.out.println(findId + "번 게시글은 없습니다.");
                    continue;
                }

                System.out.println("번호 : " + article.getId());
                System.out.println("날짜 : " + Util.data());
                System.out.println("제목 : " + article.getTitle());
                System.out.println("내용 : " + article.getBody());

            } else if (cmd.equals("exit")) {
                System.out.println("== 프로그램 종료 ==");
                break;

            } else {
                System.out.println("명령어를 잘못입력했습니다. 다시 입력해주세요.");
                continue;
            }

        }
    }

    public static Article FindId(int findId) {
        for (Article article : articles) {
            if (article.getId() == findId) {
                return article;
            }
        }
        return null;
    }

}

class Article {

    int id;
    String title;
    String body;

    public Article(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}