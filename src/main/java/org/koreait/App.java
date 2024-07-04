package org.koreait;

import org.koreait.Controller.ArticleController;
import org.koreait.Controller.Controller;
import org.koreait.Controller.MemberController;

import java.util.Scanner;

public class App {
    public void run() {
        Scanner sc = new Scanner(System.in);
        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);

        articleController.articleMakeTestData();
        memberController.memberMakeTestData();

        Controller controller = null;

        while (true) {

            System.out.print("명령어) ");
            String cmd = sc.nextLine();

            if(cmd.length() == 0){
                System.out.println("명령어를 입력하세요.");
                continue;
            }
            if(cmd.equals("exit")){
                break;
            }

            String[] cmdBits = cmd.split(" ");

            String controllerName = cmdBits[0];

            if(cmdBits.length == 1){
                System.out.println("명령어를 잘못입력했습니다.");
                continue;
            }

            String actionMethodName = cmdBits[1];

            if(controllerName.equals("article")){
                controller = articleController;
            }else if (controllerName.equals("member")){
                controller = memberController;
            }else {
                System.out.println("사용불가 명령어");
                continue;
            }
            controller.doAction(cmd, actionMethodName);

        }
        System.out.println("프로그램 종료");
        sc.close();
    }
}


