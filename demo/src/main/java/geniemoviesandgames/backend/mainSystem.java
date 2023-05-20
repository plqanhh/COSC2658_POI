package geniemoviesandgames.backend;

import java.util.ArrayList;

import geniemoviesandgames.model.product.item;
import geniemoviesandgames.model.product.itemDVD;
import geniemoviesandgames.model.product.itemGame;
import geniemoviesandgames.model.product.itemRecord;
import geniemoviesandgames.model.product.item.Genre;
import geniemoviesandgames.model.product.item.LoanType;
import geniemoviesandgames.model.product.item.Media_Formats;
import geniemoviesandgames.model.user.VipAccount;
import geniemoviesandgames.model.user.account;
import geniemoviesandgames.model.user.guestAccount;
import geniemoviesandgames.model.user.regularAccount;
import geniemoviesandgames.model.user.account.LevelOfServices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class mainSystem {

    public enum accountPart{
        ID,Name,Address,Phone,Number_of_rentals,customer_type, username, password
    }
    public enum itemPart{
        ID,Title,Media_Formats,Loan_type,copies,rental_fee,genre
    }

    protected static  ArrayList<item> listOfItems = new ArrayList<>();
    protected static ArrayList<account> listOfAccounts = new ArrayList<>();
    protected final static String itemFilePath = "../GenieMoviesAndGames/demo/src/main/resources/geniemoviesandgames/items.txt";
    protected final static String AccountFilePath = "../GenieMoviesAndGames/demo/src/main/resources/geniemoviesandgames/customers.txt";

    public static ArrayList<item> getListOfItems() {
        return listOfItems;
    }

    public static ArrayList<account> getListOfAccounts() {
        return listOfAccounts;
    }

    public static void addItemListOfItems(item itemIn) {
        listOfItems.add(itemIn);
    }

    public static void addAccountlistOfAccounts(account accin) {
        listOfAccounts.add(accin);
    }

    public static void removeItemListOfItems(item itemIn){
        listOfItems.remove(itemIn);
    }

    public static void removeAccountListOfAccount(account accIn){
        listOfAccounts.remove(accIn);
    }

    public static Boolean acountLogin(String username, String password) {
        for (account a : listOfAccounts) {
            if ((a.getUsername()).equals(username) && a.getPassword().equals(password)) {
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
                if (line.length() == 0) {
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
                        itemGame g1 = new itemGame(itemID, itemTitle, itemLoanType, itemCopies, itemFee);
                        listOfItems.add(g1);
                        break;
                    case Record:
                        itemGenre = Genre.valueOf(fields[6]);
                        itemRecord m1 = new itemRecord(itemID, itemTitle, itemLoanType, itemCopies, itemFee,
                                itemGenre);
                        listOfItems.add(m1);
                        break;
                    case DVD:
                        itemGenre = Genre.valueOf(fields[6]);
                        itemDVD d1 = new itemDVD(itemID, itemTitle, itemLoanType, itemCopies, itemFee,
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
            String id = null, name = null, address = null, username = null, password = null, phone = null,itemOwnID=null;
            LevelOfServices services = null;
            ArrayList<item> itemOwn = new ArrayList<>();
            ArrayList<LocalDate> itemOwnDate= new ArrayList<>();
            while ((line = br1.readLine()) != null) {
                if (line.length() == 0) {
                    break;
                }

                if (line.charAt(0) == 'C') {
                    if (services != null) {
                        switch (services) {
                            case VIP:
                                VipAccount v1 = new VipAccount(id, name, address, phone, itemOwn,itemOwnDate, username, password);
                                listOfAccounts.add(v1);
                                break;
                            case Guest:
                                guestAccount g1 = new guestAccount(id, name, address, phone, itemOwn,itemOwnDate, username,
                                        password);
                                listOfAccounts.add(g1);
                                break;
                            case Regular:
                                regularAccount r1 = new regularAccount(id, name, address, phone, itemOwn,itemOwnDate, username,
                                        password);
                                listOfAccounts.add(r1);
                                break;
                        }
                        services = null;
                        itemOwn.clear();
                    }
                    fields = line.split(",");
                    id = fields[0];
                    name = fields[1];
                    address = fields[2];
                    phone = fields[3];
                    services = LevelOfServices.valueOf(fields[5]);
                    username = fields[6];
                    password = fields[7];
                    if (fields[4] == "0") {
                        itemOwn = null;
                    }
                } else if (line.charAt(0) == 'I') {
                    if (itemOwn != null) {
                        fields = line.split(",");
                        itemOwnID = fields[0];
                        itemOwnDate.add(LocalDate.parse(fields[1])) ;
                        itemOwn.add(searchOption.searchItemByID(itemOwnID));
                    }
                }
            }
            switch (services) {
                case VIP:
                    VipAccount v1 = new VipAccount(id, name, address, phone, itemOwn,itemOwnDate, username, password);
                    listOfAccounts.add(v1);
                    break;
                case Guest:
                    guestAccount g1 = new guestAccount(id, name, address, phone, itemOwn,itemOwnDate, username, password);
                    listOfAccounts.add(g1);
                    break;
                    case Regular:
                    regularAccount r1 = new regularAccount(id, name, address, phone, itemOwn,itemOwnDate, username, password);
                    listOfAccounts.add(r1);
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String accountToString(account accIn) {
        String phrase = "";
        if (accIn.getListOfRentals() != null) {
            phrase = phrase + accIn.getID() + "," + accIn.getFullname() + "," + accIn.getAddress()
                    + ","
                    + accIn.getPhone() + "," + accIn.getListOfRentals().size() + ","
                    + accIn.getLevelOfServices() + "," + accIn.getUsername() + ","
                    + accIn.getPassword();
            for (int i = 0; i < accIn.getListOfRentals().size(); i++) {
                phrase = phrase + "\n" + accIn.getListOfRentals().get(i).getID()+","+accIn.getListOfDates().get(i);
            }
        } else {
            phrase = phrase + accIn.getID() + "," + accIn.getFullname() + "," + accIn.getAddress()
                    + ","
                    + accIn.getPhone() + "," + "0" + ","
                    + accIn.getLevelOfServices() + "," + accIn.getUsername() + ","
                    + accIn.getPassword();
        }
        return phrase;
    }

    public static String itemToString(item itemIn) {
        String phrase = "";
        phrase = itemIn.getID() + "," + itemIn.getTitle() + "," + itemIn.getMedia() + ","
                + itemIn.getLoanType() + "," + itemIn.getStock() + "," + itemIn.getFees();
        if (itemIn.getGenre() != null) {
            phrase = phrase + "," + itemIn.getGenre().toString();
        }
        return phrase;
    }

    public static void saveItemsToFile() {
        String phrase = "";
        for (item i : listOfItems) {

            phrase = phrase + itemToString(i) + "\n";

        }
        try {
            FileWriter fw1 = new FileWriter(itemFilePath, false);
            fw1.write(phrase);
            fw1.close();
            System.out.println("Save successfully");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Save failed");
        }
    }

    public static void saveAccountsToFile() {
        String phrase = "";
        for (account a : listOfAccounts) {
            phrase = phrase + accountToString(a) + "\n";
        }
        try {
            FileWriter fw1 = new FileWriter(AccountFilePath, false);
            fw1.write(phrase);
            fw1.close();
            System.out.println("Save successfully");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Save failed");
        }
    }

}
