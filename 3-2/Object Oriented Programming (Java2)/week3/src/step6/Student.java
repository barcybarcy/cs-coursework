package step6;

import java.util.Scanner;

public class Student {
	// 201611012 김성관 2 010-9148-2689
    String id;
	String name;
	int year;
	String phone;

    Student(String id){
        this.id = id;
    }
    void read(Scanner scan){
//        this.id = scan.next();
        this.name = scan.next();
        this.year = scan.nextInt();
        this.phone = scan.next();
    }

    void print(){
        System.out.printf("%s %s %d학년, %s\n",this.id ,this.name, this.year, this.phone);
    }
}
