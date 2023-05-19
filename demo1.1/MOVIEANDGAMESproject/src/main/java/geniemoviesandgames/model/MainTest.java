package geniemoviesandgames.model;

import geniemoviesandgames.model.Account.GuestAccount;
import geniemoviesandgames.model.Account.RegularAccount;
import geniemoviesandgames.model.Account.VipAccount;

public class MainTest {
    public static void main(String[] args) {
        // Creating two GuestAccount objects
        GuestAccount guest1 = new GuestAccount("G001", "John Doe", "123 Main St", "1234567890", "guest1", "password1");
        GuestAccount guest2 = new GuestAccount("G002", "Jane Smith", "456 Elm St", "9876543210", "guest2", "password2");

        // Creating two RegularAccount objects
        RegularAccount regular1 = new RegularAccount("R001", "Mike Johnson", "789 Oak St", "2345678901", "regular1", "password1");
        RegularAccount regular2 = new RegularAccount("R002", "Emily Davis", "987 Pine St", "1098765432", "regular2", "password2");

        // Creating two VipAccount objects
        VipAccount vip1 = new VipAccount("V001", "David Wilson", "567 Maple St", "8765432109", "vip1", "password1");
        VipAccount vip2 = new VipAccount("V002", "Sophia Anderson", "321 Cedar St", "9012345678", "vip2", "password2");

        // Print the created objects
        System.out.println("Guest Accounts:");
        System.out.println(guest1);
        System.out.println(guest2);

        System.out.println("Regular Accounts:");
        System.out.println(regular1);
        System.out.println(regular2);

        System.out.println("VIP Accounts:");
        System.out.println(vip1);
        System.out.println(vip2);
    }
}