package step1;

import java.util.ArrayList;
import java.util.Scanner;

public class Team {
    String name;
    ArrayList<Student> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
    public void read(Scanner scan, Department department) {
        int num;
        while(true){
            num = scan.nextInt();
            if (num == 0) break;
            Student st = department.getStudent(num);
            st.setTeam(this);
            members.add(st);
        }
    }
    void print(){
        System.out.printf("[%s]  ", name);
        for(Student st : members){
            // st.name srp원칙 위배 아닌가?
            // 유연성 - 이름은 공개?
            System.out.printf("[%s]  ", st.name);
        }
        System.out.println();
    }
}
