package geniemoviesandgames.backend;

import java.util.ArrayList;

import geniemoviesandgames.model.account.VipAccount;
import geniemoviesandgames.model.account.Account;
import geniemoviesandgames.model.account.GuestAccount;
import geniemoviesandgames.model.account.RegularAccount;
import geniemoviesandgames.model.account.Account.LevelOfServices;
import geniemoviesandgames.model.item.ItemDVD;
import geniemoviesandgames.model.item.ItemGame;
import geniemoviesandgames.model.item.Item;
import geniemoviesandgames.model.item.ItemRecord;
import geniemoviesandgames.model.item.Item.Genre;
import geniemoviesandgames.model.item.Item.LoanType;
import geniemoviesandgames.model.item.Item.Media_Formats;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class mainSystem {
    protected static ArrayList<Item> listOfItems = new ArrayList<>();
    protected static ArrayList<Account> listOfAccounts = new ArrayList<>();
    protected final static String itemFilePath = "../GenieMoviesAndGames/demo/src/main/resources/geniemoviesandgames/items.txt";
    protected final static String AccountFilePath = "../GenieMoviesAndGames/demo/src/main/resources/geniemoviesandgames/customers.txt";

    public static ArrayList<Item> getListOfItems() {
        return listOfItems;
    }

    public static ArrayList<Account> getListOfAccounts() {
        return listOfAccounts;
    }

    public static void addListOfItems(Item itemIn) {
        listOfItems.add(itemIn);
    }

    public static void addlistOfAccounts(Account accin) {
        listOfAccounts.add(accin);
    }

    public static Boolean acountLogin(String username, String password) {
        for (Account a : listOfAccounts) {
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
            String itemID, itemTitle;
            double itemFee;
            int itemCopies;
            Media_Formats itemMedia;
            LoanType itemLoanType;
            Genre itemGenre;
            while ((line = br.readLine()) != null) {
                if(line.length() == 0 ){
                    break;
                }
                fields = line.split(",");
                // String ID, String title, Media media, LoanType loanType, int stocks, Double
                // fees, Genre genre
                itemID = fields[0];
                itemTitle = fields[1];
                itemMedia = Media_Formats.valueOf(fields[2]);
                itemLoanType = LoanType.valueOf(fields[3]);
                itemCopies = Integer.parseInt(fields[4]);
                itemFee = Double.parseDouble(fields[5]);
                switch (itemMedia) {
                    case Game:
                        ItemGame g1 = new ItemGame(itemID, itemTitle, itemLoanType, itemCopies, itemFee );
                        listOfItems.add(g1);
                        break;
                    case Record:
                        itemGenre = Genre.valueOf(fields[6]);
                        ItemRecord m1 = new ItemRecord(itemID, itemTitle, itemLoanType, itemCopies, itemFee,
                                itemGenre);
                        listOfItems.add(m1);
                        break;
                    case DVD:
                        itemGenre = Genre.valueOf(fields[6]);
                        ItemDVD d1 = new ItemDVD(itemID, itemTitle, itemLoanType, itemCopies, itemFee,
                                itemGenre);
                        listOfItems.add(d1);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void readAccountsFromFile() {
        FileReader customerFile;
        BufferedReader br1;
        try {
            customerFile = new FileReader(AccountFilePath);
            br1 = new BufferedReader(customerFile);
            String line;
            String[] fields;
            String id = null, name = null, address = null, username = null, password = null;
            int phone = 0;
            LevelOfServices services=null;
            ArrayList<Item> itemOwn = new ArrayList<>();

            while ((line = br1.readLine()) != null) {
                if(line.length() == 0 ){
                    break;
                }
                if (line.charAt(0) == 'C') {
                    fields = line.split(",");
                    id = fields[0];
                    name = fields[1];
                    address = fields[2];
                    phone = Integer.parseInt(fields[3]);
                    services = LevelOfServices.valueOf(fields[5]);
                    username = fields[6];
                    password = fields[7];
                    if (fields[4] == "0") {
                        itemOwn = null;
                    }
                } else if(line.charAt(0) == 'I'){
                    if(itemOwn!=null){
                        itemOwn.add(searchOption.searchItemByID(line));
                    }
                }
                switch (services) {
                    case VIP:
                        //VipAccount v1 = new VipAccount(id, name, address, phone, itemOwn, username, password);
                       // listOfAccounts.add(v1);
                        break;
                    case Guest:
                      //  GuestAccount g1 = new GuestAccount(id, name, address, phone, itemOwn, username, password);
                       // listOfAccounts.add(g1);
                        break;
                    case Regular:
                       // RegularAccount r1 = new RegularAccount(id, name, address, phone, itemOwn, username, password);
                       // listOfAccounts.add(r1);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String accountToString(Account accIn) {
        String phrase = "";
        if(accIn.getAccountListOfRentals()!=null){
            phrase = phrase+accIn.getAccountID() + "," + accIn.getAccountFullname() + "," + accIn.getAccountAddress() + ","
                + Integer.toString(accIn.getAccountPhone()) + "," + accIn.getAccountListOfRentals().size() + ","
                + accIn.getAccountLevelOfServices() + "," + accIn.getAccountUsername() + ","
                + accIn.getAccountPassword();
            for (int i = 0; i < accIn.getAccountListOfRentals().size(); i++) {
                phrase = phrase + "\n" + accIn.getAccountListOfRentals().get(i).getItem();
            }
        }else{
            phrase = phrase+accIn.getAccountID() + "," + accIn.getAccountFullname() + "," + accIn.getAccountAddress() + ","
            + Integer.toString(accIn.getAccountPhone()) + ","+ "0" + ","
            + accIn.getAccountLevelOfServices() + "," + accIn.getAccountUsername() + ","
            + accIn.getAccountPassword();
        }
        System.out.println(phrase);
        return phrase ;
    }

    public static String itemToString(Item itemIn) {
        String phrase="";
        phrase = itemIn.getItemID() + "," + itemIn.getItemTitle() + "," + itemIn.getItemMedia() + ","
                + itemIn.getLoanType() + "," + itemIn.getItemStock() + "," + itemIn.getItemFees();
        if(itemIn.getItemGenre()!=null){
            phrase =phrase+","+itemIn.getItemGenre().toString();
        }
        return phrase;
    }

    public static void saveItemsToFile() {
        String phrase="";
        for (Item i : listOfItems) {

            phrase = phrase+itemToString(i)+"\n";

        }
          try {
            FileWriter fw1 = new FileWriter(itemFilePath,false);
            fw1.write(phrase);
            fw1.close();
            System.out.println("Save successfully");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Save failed");
        }  
    }

    public static void saveAccountsToFile() {
        String phrase ="";
        for (Account a : listOfAccounts) {
            phrase = phrase+accountToString(a)+"\n";
        }
/*         try {
            FileWriter fw1 = new FileWriter(AccountFilePath,false);
            fw1.write(phrase);
            fw1.close();
            System.out.println("Save successfully");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Save failed");
        } */
    }

}
