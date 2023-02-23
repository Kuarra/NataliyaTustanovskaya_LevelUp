package ru.levelp.at.homework2;

public class Ticket {

    private boolean findLuckyTicket(String num) {
        return num.length() == 6 && num.charAt(0) + num.charAt(1) + num.charAt(2) == num.charAt(3) + num.charAt(4)
            + num.charAt(5);
    }

    public boolean findLuckyTicket(int num) {
        return findLuckyTicket(String.valueOf(num));
    }
}
