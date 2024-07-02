package org.koreait;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Article> articles = new ArrayList<>();

        int id = 1;

        while (true) {

            System.out.print("명령어) ");
            String order = sc.nextLine();

            if (order.equals("article write")) {
                System.out.print("제목: ");
                String title = sc.nextLine();
                System.out.print("내용: ");
                String body = sc.nextLine();

                Article article = new Article(id, title, body);

                articles.add(article);

                System.out.println(id++ + "번 글이 생성되었습니다.");

            }else if(order.equals("article list")){
                System.out.println("번호   /   제목   /   내용");

                for(int i = articles.size() - 1; i >= 0; i--) {
                    Article article = articles.get(i);

                    System.out.println(article.getId() + "   /   " + article.getTitle() + "   /   " + article.getBody());
                }
            }else if (order.equals("exit")) {
                System.out.println("프로그램 종료");
                break;
            }
        }
    }
}