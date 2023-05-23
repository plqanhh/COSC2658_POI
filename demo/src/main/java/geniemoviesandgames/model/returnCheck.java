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
    protected LocalDate dateBorrow;
    protected LocalDate dateReturn;
    
    public LocalDate getDateBorrow(){
        return dateBorrow;
    }
    public void setDateBorrow(LocalDate date){
        dateBorrow = date;
    }

    public LocalDate getDateReturn(){
        return dateBorrow;
    }
    public void setDateReturn(LocalDate date){
        dateBorrow = date;
    }

    public deadLine getUserDeadLine(){
        return userdeadLine;
    }
    public void setUserDeadLine(deadLine userDeadLine){
        this.userdeadLine = userDeadLine;
    }
    
    public returnCheck(account accIn, item itemIn) {
        if(accIn.getListOfRentals().contains(itemIn)){
            int a = accIn.getListOfRentals().indexOf(itemIn);
            setDateBorrow(accIn.getListOfDates().get(a));
            if (itemIn.getLoantype() == LoanType.ONE_WEEK) {
                setDateReturn(accIn.getListOfDates().get(a).plusDays(7));
            } else if (itemIn.getLoantype() == LoanType.TWO_DAY) {
                setDateReturn(accIn.getListOfDates().get(a).plusDays(2));
            }
            
        }

        int compareValue = dateBorrow.compareTo(dateReturn);
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
