package geniemoviesandgames.backend;

import geniemoviesandgames.model.product.item;
import geniemoviesandgames.model.user.VipAccount;
import geniemoviesandgames.model.user.guestAccount;
import geniemoviesandgames.model.user.regularAccount;

public class returning {

    public static double returnItem(guestAccount accIn, item itemIn) {
        double bill;
        guestAccount accReturn = accIn;
        if (accReturn.getListOfRentals().contains(itemIn)) {
            itemIn.returnItem();
            bill = accReturn.accReturnItem(itemIn);

            // Check if guest customer is eligible for promotion to regular customer
            if (accReturn.getItemReturned() >= 3) {
                accIn.setPromoteReady(true);
            }
            mainSystem.updateAccount(accIn, accReturn);
            return bill;
        } else {
            System.out.println("Item " + itemIn.getTitle() + " is not currently rented by the guest account.");
            return 0;
        }
       
    }

    public static double returnItem(regularAccount accIn, item itemIn) {
        double bill;
        regularAccount accReturn = accIn;
        if (accReturn.getListOfRentals().contains(itemIn)) {
            itemIn.returnItem();
            bill = accReturn.accReturnItem(itemIn);

            // Check if regular customer is eligible for promotion to VIP customer
            if (accReturn.getItemReturned() > 5) {
                accReturn.setPromoteReady(true);
            }
            mainSystem.updateAccount(accIn, accReturn);
            return bill;
        } else {
            System.out.println("Item " + itemIn.getID() + " is not currently rented by the regular account.");
            return 0;
        }

    }
  
    public static double returnItem(VipAccount accIn, item itemIn) {
        double bill;
        VipAccount accReturn = accIn;
        if (accIn.getListOfRentals().contains(itemIn)) {
            itemIn.returnItem();
            bill = accReturn.accReturnItem(itemIn);;

            if(accReturn.getFreeRent()>0){
                accReturn.setFreeRent(accReturn.getFreeRent()-1);
                mainSystem.updateAccount(accIn, accReturn);
                return 0.0;
            }
            mainSystem.updateAccount(accIn, accReturn);
            return bill;
        } else {
            System.out.println("Item " + itemIn.getID() + " is not currently rented by the VIP account.");
            return 0;
        }
    }

        
}
