package step1;

import java.util.Scanner;

public class Student {
	// 진달래 2 80
	String name;
	int year;
	int score;

    void read(Scanner scan){
        this.name = scan.next();
        this.year = scan.nextInt();
        this.score = scan.nextInt();
    }

    void print(){
        System.out.printf("%s %d학년, %d점\n", this.name, this.year, this.score);
    }
}
