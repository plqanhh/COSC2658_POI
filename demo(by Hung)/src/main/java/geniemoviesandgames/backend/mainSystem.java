package geniemoviesandgames.backend;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import geniemoviesandgames.model.*;

public class mainSystem {
    public static ArrayList<item> listOfItems = new ArrayList<>();
    public static   ArrayList<item> outOfStockListOfItem = new ArrayList<>();
    private static ArrayList<account> listOfAccounts = new ArrayList<>();
//    public static ArrayList<order> listOfOrder = new ArrayList<>();
    private final static String itemFilePath = "../GenieMoviesAndGames/demo/src/main/resources/geniemoviesandgames/items.txt";
    private final static String CustomerFilePath = "../GenieMoviesAndGames/demo/src/main/resources/geniemoviesandgames/customers.txt";
    private final static String orderFilePath = "../GenieMoviesAndGames/demo/src/main/resources/geniemoviesandgames/order.txt";
    private final static String outOfStockItemFilePath = "../GenieMoviesAndGames/demo/src/main/resources/geniemoviesandgames/outOfStock.txt";
    public static ArrayList<item> getListOfItems() {
        return listOfItems;
    }

    public static ArrayList<account> getListOfAccounts() {
        return listOfAccounts;
    }

    public static void addListOfItems(item itemIn){
        listOfItems.add(itemIn);
    }
    public static void addListOfOutOfStockItems(item itemOutOfStock) {outOfStockListOfItem.add(itemOutOfStock);}
    public static void addlistOfAccounts(account accin){
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
            if ((a.getCustomerID()).equals(id)) {
                return a;
            }
        }
        return null;
    }

    public static Boolean acountLogin(String username, String password) {
        for (account a : listOfAccounts) {
            if ((a.getCustomerUsername()).equals(username) && a.getCustomerPassWord().equals(password)) {
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
                System.out.println(line);
                if (line.length() == 0) {
                    break;
                }
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

                System.out.println(itemID +  itemTitle  + itemLoanType  + itemCopies  + itemPrice);

                switch (itemType) {
                    case "Game":
                        game g1 = new game(itemID, itemTitle, Item2Day, itemCopies, itemPrice);
                        listOfItems.add(g1);
                        break;
                    case "Record":
                        String ItemGenre = fields[6];
                        movies m1 = new movies(itemID, itemTitle, Item2Day, itemCopies, itemPrice, ItemGenre);
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
                    if(fields[4] =="0"){
                        itemOwn=null;
                    }
                } else {
                    itemOwn.add(searchItemByID(id));
                }
                switch (customerType) {
                    case "VIP":
                        Vip v1 = new Vip(id, name, address, phone, itemOwn, username, password);
                        listOfAccounts.add(v1);
                        break;
                    case "Guest":
                        guest g1 = new guest(id, name, address, phone, itemOwn, username, password);
                        listOfAccounts.add(g1);
                        break;
                    case "Regular":
                        regular r1 = new regular(id, name, address, phone, itemOwn, username, password);
                        listOfAccounts.add(r1);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readOutOfStockItemFromFile() {
        FileReader ItemFiles;
        BufferedReader br;
        try {
            ItemFiles = new FileReader(outOfStockItemFilePath);
            br = new BufferedReader(ItemFiles);
            String line;
            Boolean Item2Day;
            String[] fields;
            String itemID, itemTitle, itemType, itemLoanType;
            double itemPrice;
            int itemCopies;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if (line.length() == 0) {
                    break;
                }
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

                System.out.println(itemID +  itemTitle  + itemLoanType  + itemCopies  + itemPrice);

                switch (itemType) {
                    case "Game":
                        game g1 = new game(itemID, itemTitle, Item2Day, itemCopies, itemPrice);
                        outOfStockListOfItem.add(g1);
                        break;
                    case "Record":
                        String ItemGenre = fields[6];
                        movies m1 = new movies(itemID, itemTitle, Item2Day, itemCopies, itemPrice, ItemGenre);
                        outOfStockListOfItem.add(m1);
                        break;
                    case "DVD":
                        ItemGenre = fields[6];
                        DVD d1 = new DVD(itemID, itemTitle, Item2Day, itemCopies, itemPrice, ItemGenre);
                        outOfStockListOfItem.add(d1);
                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addItemToTextFile(item itemIn) {
        String phrase;
        String LoanType;
        String itemType;
        if (itemIn.isItem2DayLoan()) {
            LoanType = "2-day";
        } else {
            LoanType = "1-week";
        }
        if (itemIn instanceof game) {
            itemType = "Game";
        } else if (itemIn instanceof DVD) {
            itemType = "DVD";
        } else {
            itemType = "Record";
        }

        phrase = itemIn.getItemID() + "," + itemIn.getItemTitle() + "," + itemType + "," + LoanType + ","
                + Integer.toString(itemIn.getItemCopies()) + "," + Double.toString(itemIn.getItemFees());
        try {
            FileWriter fw = new FileWriter(itemFilePath, true);
            fw.write("\n" + phrase);
            fw.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

//    public static void writeItemToOrderList() {
//
//        try {
//            FileWriter fw = new FileWriter(orderFilePath, false);
//
//            for (item itm : mainSystem.listOfOrder){
//                String phrase;
//                String LoanType;
//                String itemType;
//                if (itm.isItem2DayLoan()) {
//                    LoanType = "2-day";
//                } else {
//                    LoanType = "1-week";
//                }
//                if (itm instanceof game) {
//                    itemType = "Game";
//                } else if (itm instanceof DVD) {
//                    itemType = "DVD";
//                } else {
//                    itemType = "Record";
//                }
//
//                phrase = itm.getItemID() + "," + itm.getItemTitle() + "," + itemType + "," + LoanType + ","
//                        + Integer.toString(itm.getItemCopies()) + "," + Double.toString(itm.getItemFees()) ;
//
//                if (itm instanceof DVD){
//                    phrase = phrase + "," + ((DVD) itm).getItemGenre();
//                } else if (itm instanceof movies){
//                    phrase = phrase + "," + ((movies) itm).getItemGenre();
//                }
//
//                fw.write(phrase + "\n");
//            }
//
//            fw.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void writeItemToTextFile() {

        try {
            FileWriter fw = new FileWriter(itemFilePath, false);

            for (item itm : mainSystem.listOfItems){
                String phrase;
                String LoanType;
                String itemType;
                if (itm.isItem2DayLoan()) {
                    LoanType = "2-day";
                } else {
                    LoanType = "1-week";
                }
                if (itm instanceof game) {
                    itemType = "Game";
                } else if (itm instanceof DVD) {
                    itemType = "DVD";
                } else {
                    itemType = "Record";
                }

                phrase = itm.getItemID() + "," + itm.getItemTitle() + "," + itemType + "," + LoanType + ","
                        + Integer.toString(itm.getItemCopies()) + "," + Double.toString(itm.getItemFees()) ;

                if (itm instanceof DVD){
                    phrase = phrase + "," + ((DVD) itm).getItemGenre();
                } else if (itm instanceof movies){
                    phrase = phrase + "," + ((movies) itm).getItemGenre();
                }

                fw.write(phrase + "\n");
            }

            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void writeItemToOutOfStockList() {

        try {
            FileWriter outOfStockFW = new FileWriter(outOfStockItemFilePath, false);

            for (item itm : mainSystem.outOfStockListOfItem) {

                    String phrase;
                    String LoanType;
                    String itemType;
                    if (itm.isItem2DayLoan()) {
                        LoanType = "2-day";
                    } else {
                        LoanType = "1-week";
                    }
                    if (itm instanceof game) {
                        itemType = "Game";
                    } else if (itm instanceof DVD) {
                        itemType = "DVD";
                    } else {
                        itemType = "Record";
                    }


                    phrase = itm.getItemID() + "," + itm.getItemTitle() + "," + itemType + "," + LoanType + ","
                            + Integer.toString(itm.getItemCopies()) + "," + Double.toString(itm.getItemFees());

                    if (itm instanceof DVD) {
                        phrase = phrase + "," + ((DVD) itm).getItemGenre();
                    } else if (itm instanceof movies) {
                        phrase = phrase + "," + ((movies) itm).getItemGenre();
                    }

                    outOfStockFW.write(phrase + "\n");
                }
//                 else if (itm.getItemCopies() > 0) {
//                    return ;
//                }

            outOfStockFW.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addaccountToTextFile(account accIn) {
        String phrase;
        String accType = "Guest";
        String itemrentSize;
         if(accIn.getCustomerListofRentals()== null){
            itemrentSize="0";
        } else{
            itemrentSize=Integer.toString(accIn.getCustomerListofRentals().size());
        }
        if (accIn instanceof guest) {
            accType = "Guest";
        } else if (accIn instanceof regular) {
            accType = "Regular";
        } else if (accIn instanceof Vip) {
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
