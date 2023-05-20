package geniemoviesandgames;

import java.io.FileWriter;
import java.time.LocalDate;

public class test {
    protected enum Genre {
        Action, Horror, Drama, Comedy
    };
    public static void main(String[] args){
        final String itemFilePath = "../GenieMoviesAndGames/demo/src/main/resources/geniemoviesandgames/test.txt";

        LocalDate date = LocalDate.now();
        LocalDate.parse("2023-03-2");
        try {
            FileWriter fw1 = new FileWriter(itemFilePath, false);
            fw1.write(date.toString());
            fw1.close();
            System.out.println("Save successfully");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Save failed");
        }
    }
}
