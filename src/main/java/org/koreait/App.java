package org.koreait;

import org.koreait.Controller.ArticleController;
import org.koreait.Controller.MemberController;

import java.util.Scanner;

public class App {
    public static void run(Scanner sc) {

        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);

        articleController.articleMakeTestData();
        memberController.memberMakeTestData();

        while (true) {

            System.out.print("명령어) ");
            String cmd = sc.nextLine();

            switch (cmd) {
                case "member join":
                    memberController.doJoin();
                    break;
                case "article write":
                    articleController.dowrite();
                    break;
                case "article list":
                    articleController.showlist(cmd);
                    break;
                case "article detail":
                    articleController.showdetail(cmd);
                    break;
                case "article delete":
                    articleController.dodelete(cmd);
                    break;
                case "article modify":
                    articleController.domodify(cmd);
                    break;
                case "exit":
                    System.out.println("프로그램 종료");
                    break;
            }


        }
    }
}
