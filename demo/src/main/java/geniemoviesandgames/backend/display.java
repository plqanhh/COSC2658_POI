package geniemoviesandgames.backend;

import java.util.ArrayList;
import java.util.Comparator;

import geniemoviesandgames.model.product.item;
import geniemoviesandgames.model.product.item.Media_Formats;
import geniemoviesandgames.model.user.account;
import geniemoviesandgames.model.user.account.LevelOfServices;

public class display {

    public static ArrayList<account> allAccount(){
        return mainSystem.listOfAccounts;
    }

    public static ArrayList<item> allItem(){
        return mainSystem.listOfItems;
    }

    public static ArrayList<item> allDVD(){
        ArrayList<item> sortedList = new ArrayList<>();
        for(item i: mainSystem.listOfItems){
            if (i.getMedia() == Media_Formats.DVD){
                 sortedList.add(i);
            }
        }
        return sortedList;
    } 

    public static ArrayList<item> allGame(){
        ArrayList<item> sortedList = new ArrayList<>();
        for(item i: mainSystem.listOfItems){
            if (i.getMedia() == Media_Formats.Game){
                 sortedList.add(i);
            }
        }
        return sortedList;
    }

    public static ArrayList<item> allRecord(){
        ArrayList<item> sortedList = new ArrayList<>();
        for(item i: mainSystem.listOfItems){
            if (i.getMedia() == Media_Formats.Record){
                 sortedList.add(i);
            }
        }
        return sortedList;
    }

    public static ArrayList<account> allGuest(){
        ArrayList<account> sortedList = new ArrayList<>();
        for(account a: mainSystem.listOfAccounts){
            if (a.getLevelOfServices() == LevelOfServices.Guest){
                 sortedList.add(a);
            }
        }
        return sortedList;
    }

    public static ArrayList<account> allRegular(){
        ArrayList<account> sortedList = new ArrayList<>();
        for(account a: mainSystem.listOfAccounts){
            if (a.getLevelOfServices() == LevelOfServices.Regular){
                 sortedList.add(a);
            }
        }
        return sortedList;
    }

    public static ArrayList<account> allVip(){
        ArrayList<account> sortedList = new ArrayList<>();
        for(account a: mainSystem.listOfAccounts){
            if (a.getLevelOfServices() == LevelOfServices.VIP){
                 sortedList.add(a);
            }
        }
        return sortedList;
    }

    public static ArrayList<item> allNocopies(){
        ArrayList<item> sortedList = new ArrayList<>();
        for(item i: mainSystem.listOfItems){
            if (i.getStock() == 0){
                 sortedList.add(i);
            }
        }
        return sortedList;
    }

    public static ArrayList<account> accSortedByName(ArrayList<account> listAcc){

        ArrayList<account> sortedList = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>(); 

        for(account a: listAcc){
            nameList.add(a.getFullname());
        }
        nameList.sort(Comparator.naturalOrder());
        for(String name: nameList){
            sortedList.add(searchOption.searchAccountByName(name));
        }
        return sortedList;
    }

    public static ArrayList<account> accSortedByID(ArrayList<account> listAcc){

        ArrayList<account> sortedList = new ArrayList<>();
        ArrayList<String> IDList = new ArrayList<>(); 

        for(account a: listAcc){
            IDList.add(a.getID());
        }
        IDList.sort(Comparator.naturalOrder());
        for(String id: IDList){
            sortedList.add(searchOption.searchAccountByID(id));
        }
        return sortedList;
    }

    public static ArrayList<item> itemSortedByTitle(ArrayList<item> listItem){

        ArrayList<item> sortedList = new ArrayList<>();
        ArrayList<String> titleList = new ArrayList<>(); 

        for(item i: listItem){
            titleList.add(i.getTitle());
        }
        titleList.sort(Comparator.naturalOrder());
        for(String id: titleList){
            sortedList.add(searchOption.searchItemByTitle(id));
        }
        return sortedList;
    }

    public static ArrayList<item> itemSortedByID(ArrayList<item> listItem){

        ArrayList<item> sortedList = new ArrayList<>();
        ArrayList<String> IDList = new ArrayList<>(); 

        for(item i: listItem){
            IDList.add(i.getTitle());
        }
        IDList.sort(Comparator.naturalOrder());
        for(String id: IDList){
            sortedList.add(searchOption.searchItemByID(id));
        }
        return sortedList;
    }

}
