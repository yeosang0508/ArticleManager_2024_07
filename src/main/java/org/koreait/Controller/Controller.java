package org.koreait.Controller;

import org.koreait.Member;

public class Controller {
    protected static Member loginMember = null;

    public void doAction(String cmd, String actionMethodName){

    }

    public static boolean isLogined() {
        return loginMember != null;
    }

}
