package geniemoviesandgames.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import geniemoviesandgames.model.account.VipAccount;
import geniemoviesandgames.model.account.account;
import geniemoviesandgames.model.account.guestAccount;
import geniemoviesandgames.model.account.regularAccount;
import geniemoviesandgames.model.item.item;

public class modifyTextfile extends mainSystem{

    public static String itemToString(item itemIn) {
        String phrase;
        String itemGenre = "";
        if (itemIn.getItemGenre() != null) {
            itemGenre = itemIn.getItemGenre().toString();
        }
        phrase = itemIn.getItemID() + "," + itemIn.getItemTitle() + "," + itemIn.getItemMedia() + ","
                + itemIn.getLoanType() + ","
                + Integer.toString(itemIn.getItemStock()) + "," + Double.toString(itemIn.getItemFees()) + ","
                + itemGenre;

        return phrase;
    }

    public static String accountToString(account accIn) {
        String phrase;
        String accType = "Guest";
        String itemrentSize;
        String itemOwn = "";
        if (accIn.getAccountListOfRentals() == null) {
            itemrentSize = "0";
        } else {
            itemrentSize = Integer.toString(accIn.getAccountListOfRentals().size());
            for (int i = 0; i < accIn.getAccountListOfRentals().size(); i++) {
                itemOwn = "\n" + accIn.getAccountListOfRentals().get(0).getItemID();
            }
        }
        if (accIn instanceof guestAccount) {
            accType = "Guest";
        } else if (accIn instanceof regularAccount) {
            accType = "Regular";
        } else if (accIn instanceof VipAccount) {
            accType = "VIP";
        }
        phrase = accIn.getAccountID() + "," + accIn.getAccountFullname() + "," + accIn.getAccountAddress() + ","
                + Integer.toString(accIn.getAccountPhone()) + "," + itemrentSize + ","
                + accType + "," + accIn.getAccountUsername() + "," + accIn.getAccountPassword() + itemOwn;

        return phrase;
    }
    
    public static void addItemToTextFile(item itemIn) {
        String phrase = itemToString(itemIn);
        try {
            FileWriter fw = new FileWriter(itemFilePath, true);
            fw.write("\n" + phrase);
            fw.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static void addaccountToTextFile(account accIn) {
        String phrase = accountToString(accIn);

        try {
            FileWriter fw = new FileWriter(AccountFilePath, true);
            fw.write("\n" + phrase);
            fw.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void editAccountTextFile(String wordBeingReplaced, String wordReplacing) {
        try {
            File fileToEdit = new File(AccountFilePath);
            BufferedReader reader = new BufferedReader(new FileReader(fileToEdit));

            String line = "", oldText = "";
            while ((line = reader.readLine()) != null) {
                oldText += line + "\n";
            }
            reader.close();
            String newContent = oldText.replaceAll(wordBeingReplaced, wordReplacing);

            FileWriter writer = new FileWriter(fileToEdit, false);
            writer.write(newContent);
            writer.close();

            System.out.println("File edited successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while editing the file.");
            e.printStackTrace();
        }
    }

    public static void editItemTextFile(String wordBeingReplaced, String wordReplacing) {
        try {
            File fileToEdit = new File(AccountFilePath);
            BufferedReader reader = new BufferedReader(new FileReader(fileToEdit));

            String line = "", oldText = "";
            while ((line = reader.readLine()) != null) {
                oldText += line + "\n";
            }
            reader.close();

            String newContent = oldText.replaceAll(wordBeingReplaced, wordReplacing);

            FileWriter writer = new FileWriter(fileToEdit, false);
            writer.write(newContent);
            writer.close();

            System.out.println("File edited successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while editing the file.");
            e.printStackTrace();
        }
    }

    public static void removeItemFromTextFile(item itemIn) {
        String phrase = itemToString(itemIn);
        try {
            File fileToEdit = new File(itemFilePath);
            BufferedReader reader = new BufferedReader(new FileReader(fileToEdit));

            String line = "", oldText = "";
            while ((line = reader.readLine()) != null) {
                oldText += line + "\n";
            }
            reader.close();
            String newContent = oldText.replaceAll(phrase, "");

            FileWriter writer = new FileWriter(fileToEdit, false);
            writer.write(newContent);
            writer.close();

            System.out.println("File edited successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while editing the file.");
            e.printStackTrace();
        }
    }

    public static void removeAccountFromTextFile(account accIn) {
        String phrase = accountToString(accIn);
        try {
            File fileToEdit = new File(accountToString(accIn));
            BufferedReader reader = new BufferedReader(new FileReader(fileToEdit));

            String line = "", oldText = "";
            while ((line = reader.readLine()) != null) {
                oldText += line + "\n";
            }
            reader.close();
            String newContent = oldText.replaceAll(phrase, "");

            FileWriter writer = new FileWriter(fileToEdit, false);
            writer.write(newContent);
            writer.close();

            System.out.println("File edited successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while editing the file.");
            e.printStackTrace();
        }
    }
}
