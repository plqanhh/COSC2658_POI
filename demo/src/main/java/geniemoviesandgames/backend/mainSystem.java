package geniemoviesandgames.backend;

import java.util.ArrayList;

import geniemoviesandgames.model.account.VipAccount;
import geniemoviesandgames.model.account.account;
import geniemoviesandgames.model.account.guestAccount;
import geniemoviesandgames.model.account.regularAccount;
import geniemoviesandgames.model.item.itemDVD;
import geniemoviesandgames.model.item.itemGame;
import geniemoviesandgames.model.item.item;
import geniemoviesandgames.model.item.itemMovies;
import geniemoviesandgames.model.item.item.Genre;
import geniemoviesandgames.model.item.item.LoanType;
import geniemoviesandgames.model.item.item.Media_Formats;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class mainSystem {
    protected static ArrayList<item> listOfItems = new ArrayList<>();
    protected static ArrayList<account> listOfAccounts = new ArrayList<>();
    protected final static String itemFilePath = "../GenieMoviesAndGames/demo/src/main/resources/geniemoviesandgames/items.txt";
    protected final static String AccountFilePath = "../GenieMoviesAndGames/demo/src/main/resources/geniemoviesandgames/customers.txt";

    public static ArrayList<item> getListOfItems() {
        return listOfItems;
    }

    public static ArrayList<account> getListOfAccounts() {
        return listOfAccounts;
    }

    public static void addListOfItems(item itemIn) {
        listOfItems.add(itemIn);
    }

    public static void addlistOfAccounts(account accin) {
        listOfAccounts.add(accin);
    }

    public static item searchItemByID(String id) {
        for (item i : listOfItems) {
            if ((i.getItemID()).equals(id)) {
                return i;
            }
        }
        return null;
    }

    public static account searchAccountByID(String id) {
        for (account a : listOfAccounts) {
            if ((a.getAccountID()).equals(id)) {
                return a;
            }
        }
        return null;
    }

    public static item searchItemByName(String name) {
        for (item i : listOfItems) {
            if ((i.getItemTitle()).equals(name)) {
                return i;
            }
        }
        return null;
    }

    public static account searchAccountByName(String name) {
        for (account a : listOfAccounts) {
            if ((a.getAccountFullname()).equals(name)) {
                return a;
            }
        }
        return null;
    }

    public static Boolean acountLogin(String username, String password) {
        for (account a : listOfAccounts) {
            if ((a.getAccountUsername()).equals(username) && a.getAccountPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static void readItemsFromFile() {
        FileReader ItemFiles;
        BufferedReader br;
        try {
            ItemFiles = new FileReader(itemFilePath);
            br = new BufferedReader(ItemFiles);
            String line;
            String[] fields;
            String itemID, itemTitle, itemType;
            double itemFee;
            int itemCopies;
            Media_Formats itemMedia;
            LoanType itemLoanType;
            Genre itemGenre;
            while ((line = br.readLine()) != null) {

                fields = line.split(",");
                //String ID, String title, Media media, LoanType loanType, int stocks, Double fees, Genre genre
                itemID = fields[0];
                itemTitle = fields[1];
                itemMedia = Media_Formats.valueOf(fields[2]);
                itemLoanType = LoanType.valueOf(fields[3]);
                itemCopies = Integer.parseInt(fields[4]);
                itemFee = Double.parseDouble(fields[5]);
    

                switch (itemMedia.toString()) {
                    case "Game":
                        itemGame g1 = new itemGame(itemID, itemTitle, itemMedia, itemLoanType, itemCopies, itemFee, null);
                        listOfItems.add(g1);
                        break;
                    case "Record":
                        itemGenre = Genre.valueOf(fields[6]);
                        itemMovies m1 = new itemMovies(itemID, itemTitle, itemMedia, itemLoanType, itemCopies, itemFee, itemGenre);
                        listOfItems.add(m1);
                        break;
                    case "DVD":
                        itemGenre = Genre.valueOf(fields[6]);
                        itemDVD d1 = new itemDVD(itemID, itemTitle, itemMedia, itemLoanType, itemCopies, itemFee, itemGenre);
                        listOfItems.add(d1);
                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readCustomersFromFile() {
        FileReader customerFile;
        BufferedReader br1;
        try {
            customerFile = new FileReader(AccountFilePath);
            br1 = new BufferedReader(customerFile);
            String line;
            String[] fields;
            String id = null, name = null, address = null, username = null, password = null, customerType = null;
            int phone = 0;
            ArrayList<item> itemOwn = new ArrayList<>();

            while ((line = br1.readLine()) != null) {
                if (line.charAt(0) == 'C') {
                    fields = line.split(",");
                    id = fields[0];
                    name = fields[1];
                    address = fields[2];
                    phone = Integer.parseInt(fields[3]);
                    customerType = fields[5];
                    username = fields[6];
                    password = fields[7];
                    if (fields[4] == "0") {
                        itemOwn = null;
                    }
                } else {
                    itemOwn.add(searchItemByID(id));
                }
                switch (customerType) {
                    case "VIP":
                        VipAccount v1 = new VipAccount(id, name, address, phone, itemOwn, username, password);
                        listOfAccounts.add(v1);
                        break;
                    case "Guest":
                        guestAccount g1 = new guestAccount(id, name, address, phone, itemOwn, username, password);
                        listOfAccounts.add(g1);
                        break;
                    case "Regular":
                        regularAccount r1 = new regularAccount(id, name, address, phone, itemOwn, username, password);
                        listOfAccounts.add(r1);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String itemToString(item itemIn) {
        String phrase;
        String itemGenre = "";
        if(itemIn.getItemGenre()!=null){
            itemGenre = itemIn.getItemGenre().toString();
        }
        phrase = itemIn.getItemID() + "," + itemIn.getItemTitle() + "," + itemIn.getItemMedia() + "," + itemIn.getLoanType() + ","
                + Integer.toString(itemIn.getItemStock()) + "," + Double.toString(itemIn.getItemFees())+","+itemGenre;

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
