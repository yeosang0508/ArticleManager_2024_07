package org.koreait;

import org.koreait.Controller.ArticleController;
import org.koreait.Controller.MemberController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);

        articleController.articleMakeTestData();
        memberController.memberMakeTestData();

        while (true) {

            System.out.print("명령어) ");
            String cmd = sc.nextLine();

            if (cmd.equals("member join")) {
                memberController.doJoin();
            } else if (cmd.equals("article write")) {
                articleController.dowrite();
            } else if (cmd.startsWith("article list")) {
                articleController.showlist(cmd);
            } else if (cmd.startsWith("article detail")) {
                articleController.showdetail(cmd);
            } else if (cmd.startsWith("article delete")) {
                articleController.dodelete(cmd);
            } else if (cmd.startsWith("article modify")) {
                articleController.domodify(cmd);
            } else if (cmd.equals("exit")) {
                System.out.println("프로그램 종료");
                break;
            }
        }
    }

}
