package geniemoviesandgames.model;

import java.time.LocalDate;

import geniemoviesandgames.model.product.item;
import geniemoviesandgames.model.product.item.LoanType;
import geniemoviesandgames.model.user.account;

public class returnCheck {

    public enum deadLine{
        LATE,EARLY,ON_TIME,
    }
    protected deadLine userdeadLine;
    
    public deadLine getUserDeadLine(){
        return userdeadLine;
    }
    public void setUserDeadLine(deadLine userDeadLine){
        this.userdeadLine = userDeadLine;
    }
    public returnCheck(account accIn, item itemIn) {
        LocalDate today = LocalDate.now();
        LocalDate dayToReturn = accIn.getDate(itemIn);
        if (itemIn.getLoanType() == LoanType.ONE_WEEK) {
            dayToReturn.plusDays(7);
        } else if (itemIn.getLoanType() == LoanType.TWO_DAY) {
            dayToReturn.plusDays(2);
        }
        int compareValue = today.compareTo(dayToReturn);
        System.out.println(compareValue);
        if (compareValue > 0) {
            System.out.println("You are late");
            setUserDeadLine(deadLine.LATE);
        } else if (compareValue < 0) {
            System.out.println("You are early");
            setUserDeadLine(deadLine.EARLY);
        } else {
            System.out.println("You are just on time");
            setUserDeadLine(deadLine.ON_TIME);
        }
    }
    
}
