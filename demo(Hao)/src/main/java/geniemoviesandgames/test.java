package geniemoviesandgames;

import java.util.ArrayList;

public class test {
    protected enum Genre {
        Action, Horror, Drama, Comedy
    };
    public static void main(String[] args){
        ArrayList<Integer> alist = new ArrayList<>();
        for(int i=0;i<10;i++){
            alist.add(i);
        }
        System.out.println(alist);
        int a = alist.indexOf(5);
        alist.remove(5);
        System.out.println(alist);
        alist.add(a, 10);
        System.out.println(alist);
        Genre g1 = Genre.valueOf("Action");
        System.out.println(g1);
    }
}
