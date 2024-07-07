package org.koreait.Controller;

import org.koreait.Member;

public class Controller {
    protected Member loginMember = null;

    public void doAction(String cmd, String actionMethodName){

    }

    protected boolean isLogined() {
        return loginMember != null;
    }

}
