package hw6;

import java.util.Scanner;

public class FoodWithAmount extends Food{
    private int amount;
    private String amountUnit;

    @Override
    void read(Scanner scan) {
        super.read(scan);
        this.amount = scan.nextInt();
        this.amountUnit = scan.next();
    }

    @Override
    void print() {
        System.out.format("%s-%s (%dkcal/%s) %d%s", type, name, cal, unit, amount, amountUnit);
        System.out.println();
    }

    @Override
    void printEat(int n, String u) {
        if(u.equals("g") || u.equals("ml") || u.equals("cc")){
            System.out.format("%s %d%s(%dkcal) - %dkcal/1%s(%d%s) -> %dkcal*%d/%d%s=%dkcal\n", name, n, u, getKcal(n, u), cal, unit, amount, amountUnit,
                    cal, n, amount, amountUnit, getKcal(n, u));
        }else{
            super.printEat(n, u);
        }
    }

    @Override
    int getKcal(int s, String u) {
        if(u.equals("g") || u.equals("ml") || u.equals("cc")) {
            return cal * s / amount;
        }
        return super.getKcal(s, u);
    }

    @Override
    String getDetail(int n, String u) {
        return super.getDetail(n, u);
    }

    @Override
    public boolean matches(String name2) {
        return super.matches(name2);
    }
}
