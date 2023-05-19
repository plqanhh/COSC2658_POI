package geniemoviesandgames.service;

import java.util.ArrayList;

import geniemoviesandgames.model.Account.Account;
import geniemoviesandgames.model.Account.GuestAccount;
import geniemoviesandgames.model.Account.RegularAccount;
import geniemoviesandgames.model.Account.VipAccount;
import geniemoviesandgames.model.Item.DVD;
import geniemoviesandgames.model.Item.Item;
import geniemoviesandgames.model.Item.OMRecord;
import geniemoviesandgames.model.Item.VideoGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class mainSystem {
    private static ArrayList<Item> listOfItems = new ArrayList<>();
    private static ArrayList<Account> listOfAccounts = new ArrayList<>();
    private final static String itemFilePath = "../GenieMoviesAndGames/demo/src/main/resources/geniemoviesandgames/items.txt";
    private final static String CustomerFilePath = "../GenieMoviesAndGames/demo/src/main/resources/geniemoviesandgames/customers.txt";

    public static ArrayList<Item> getListOfItems() {
        return listOfItems;
    }

    public static ArrayList<Account> getListOfAccounts() {
        return listOfAccounts;
    }

    public static void addListOfItems(Item itemIn){
        listOfItems.add(itemIn);
    }
    public static void addlistOfAccounts(Account accin){
        listOfAccounts.add(accin);
    }

    public static Item searchItemByID(String id) {
        for (Item i : listOfItems) {
            if ((i.getItemID()).equals(id)) {
                return i;
            }
        }
        return null;
    }

    public static Account searchAccountByID(String id) {
        for (Account a : listOfAccounts) {
            if ((a.getUserID()).equals(id)) {
                return a;
            }
        }
        return null;
    }

    public static Boolean acountLogin(String username, String password) {
        for (Account a : listOfAccounts) {
            if ((a.getUsername()).equals(username) && a.getUserPassword().equals(password)) {
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
            Boolean Item2Day;
            String[] fields;
            String itemID, itemTitle, itemType, itemLoanType;
            double itemPrice;
            int itemCopies;
            while ((line = br.readLine()) != null) {

                fields = line.split(",");
                itemID = fields[0];
                itemTitle = fields[1];
                itemType = fields[2];
                itemLoanType = fields[3];
                itemCopies = Integer.parseInt(fields[4]);
                itemPrice = Double.parseDouble(fields[5]);

                if (itemLoanType == "1-week") {
                    Item2Day = false;
                } else {
                    Item2Day = true;
                }

                switch (itemType) {
                    case "Game":
                        VideoGame g1 = new VideoGame(itemID, itemTitle, Item2Day, itemCopies, itemPrice);
                        listOfItems.add(g1);
                        break;
                    case "Record":
                        String ItemGenre = fields[6];
                        OMRecord m1 = new OMRecord(itemID, itemTitle, Item2Day, itemCopies, itemPrice, ItemGenre);
                        listOfItems.add(m1);
                        break;
                    case "DVD":
                        ItemGenre = fields[6];
                        DVD d1 = new DVD(itemID, itemTitle, Item2Day, itemCopies, itemPrice, ItemGenre);
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
            customerFile = new FileReader(CustomerFilePath);
            br1 = new BufferedReader(customerFile);
            String line;
            String[] fields;
            String id = null, name = null, address = null, username = null, password = null, customerType = null;
            int phone = 0;
            ArrayList<Item> itemOwn = new ArrayList<>();

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
                    if(fields[4] =="0"){
                        itemOwn=null;
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
                        GuestAccount g1 = new GuestAccount(id, name, address, phone, itemOwn, username, password);
                        listOfAccounts.add(g1);
                        break;
                    case "Regular":
                        RegularAccount r1 = new RegularAccount(id, name, address, phone, itemOwn, username, password);
                        listOfAccounts.add(r1);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addItemToTextFile(Item itemIn) {
        String phrase;
        String LoanType;
        String itemType;
        if (itemIn.isItem2DayLoan() == true) {
            LoanType = "2-day";
        } else {
            LoanType = "1-week";
        }
        if (itemIn instanceof VideoGame) {
            itemType = "Game";
        } else if (itemIn instanceof DVD) {
            itemType = "DVD";
        } else {
            itemType = "Record";
        }

        phrase = itemIn.getItemID() + "," + itemIn.getItemTitle() + "," + itemType + "," + LoanType + ","
                + Integer.toString(itemIn.getStock()) + "," + Double.toString(itemIn.getRentalFee());
        try {
            FileWriter fw = new FileWriter(itemFilePath, true);
            fw.write("\n" + phrase);
            fw.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static void addaccountToTextFile(Account accIn) {
        String phrase;
        String accType = "Guest";
        String itemrentSize;
         if(accIn.getCustomerListofRentals()== null){
            itemrentSize="0";
        } else{
            itemrentSize=Integer.toString(accIn.getCustomerListofRentals().size());
        }
        if (accIn instanceof GuestAccount) {
            accType = "Guest";
        } else if (accIn instanceof RegularAccount) {
            accType = "Regular";
        } else if (accIn instanceof VipAccount) {
            accType = "VIP";
        }
        phrase = accIn.getCustomerID() + "," + accIn.getCustomerFullname() + "," + accIn.getCustomerAddress() + ","
                + Integer.toString(accIn.getCustomerPhone()) + "," + itemrentSize + ","
                + accType + "," + accIn.getCustomerUsername() + "," + accIn.getCustomerPassWord();
        try {
            FileWriter fw = new FileWriter(CustomerFilePath,true);
            fw.write("\n" +phrase);
            fw.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
