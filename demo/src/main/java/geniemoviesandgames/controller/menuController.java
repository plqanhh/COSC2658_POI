package geniemoviesandgames.controller;

import geniemoviesandgames.Switchingscence;
import geniemoviesandgames.model.user.account;

public class menuController extends Switchingscence {
    protected static account mainAcc;

    public static void setMainAcc(account acc){
        mainAcc = acc;
    }
}
