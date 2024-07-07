package org.koreait;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int id = 1;

        while (true) {
            System.out.println("== 프로그램 시작 ==");

            System.out.print("명령어) ");
            String cmd = sc.nextLine();


            if (cmd.equals("article write")) {
                System.out.print("제목: ");
                String title = sc.nextLine();

                System.out.print("내용: ");
                String body = sc.nextLine();


                System.out.println(id++ + "번 글이 생성되었습니다");
            }


            if(cmd.equals("exit")){
                System.out.println("== 프로그램 종료 ==");
                break;
            }

        }
    }
}
