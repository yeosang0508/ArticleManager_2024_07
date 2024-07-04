package org.koreait.Controller;

import org.koreait.Article;
import org.koreait.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleController {
    List<Article> articles;
    Scanner sc;
    private int id = 4;

    public ArticleController(Scanner sc) {
        this.sc = sc;
        articles = new ArrayList<>();
    }

    public void dowrite() {
        System.out.print("제목: ");
        String title = sc.nextLine();
        System.out.print("내용: ");
        String body = sc.nextLine();

        Article article = new Article(id, title, body, Util.date());

        articles.add(article);

        System.out.println(id++ + "번 글이 생성되었습니다.");
    }

    public void showlist(String cmd) {
        System.out.println("번호   /   제목   /   내용");

        String findparttitle = cmd.substring("article list".length()).trim();
        if (findparttitle != "") {
            for (Article article : articles) {
                if (article.getTitle().contains(findparttitle)) {
                    System.out.println(article.getId() + "   /   " + article.getTitle() + "   /   " + article.getBody());
                }
            }
            return;
        }
        for (int i = articles.size() - 1; i >= 0; i--) {
            Article article = articles.get(i);

            System.out.println(article.getId() + "   /   " + article.getTitle() + "   /   " + article.getBody());
        }
    }


    public void showdetail(String cmd) {
        String Id = cmd.substring("article detail".length()).trim();

        int articleId = Integer.parseInt(Id);

        haveId(articleId);
        findArticleId(articleId);

        Article article = findArticleId(articleId);

        System.out.println("번호 : " + articleId);
        System.out.println("날짜 : " + article.getDate());
        System.out.println("제목 : " + article.getTitle());
        System.out.println("내용 : " + article.getBody());

    }

    public void dodelete(String cmd) {
        String Id = cmd.substring("article delete".length()).trim();

        int articleId = Integer.parseInt(Id);

        haveId(articleId);
        findArticleId(articleId);


        articles.remove(articleId - 1);

        System.out.println(articleId + "번 게시글이 삭제되었습니다.");
    }

    public void domodify(String cmd) {
        String Id = cmd.substring("article modify".length()).trim();

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
    }

    public void articleMakeTestData() {
        System.out.println("테스트 데이터 생성");

        articles.add(new Article(1, "제목1", "내용1", Util.date()));
        articles.add(new Article(2, "제목2", "내용2", Util.date()));
        articles.add(new Article(3, "제목3", "내용3", Util.date()));

    }

    public  void haveId(int id) {
        if (id == 0) {
            System.out.println("Id를 입력하지 않았습니다.");
            return;
        }

        if (findArticleId(id) == null) {
            System.out.println(id + "번 게시글은 없습니다.");
        }
    }

    public  Article findArticleId(int articleID) {
        for (Article article : articles) {
            if (article.getId() == articleID) {
                return article;
            }
        }
        return null;
    }

}

