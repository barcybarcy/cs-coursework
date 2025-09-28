package step6;  // 강의노트 p.7

import java.util.ArrayList;
import java.util.Scanner;

public class Department {
	Scanner scan = new Scanner(System.in);
    ArrayList<Student> stList = new ArrayList<>();
    void readStudents(){
        while(true){
            String id = scan.next();
            if (id.equals("0")){
                break;
            }
            Student st = new Student(id);
            st.read(scan);
            stList.add(st);
        }
    }
    void printStudents(){
        for (Student st: stList){
            st.print();
        }
    }
	void mymain() {
        readStudents();
        printStudents();
	}
    //main 함수는 맨 끝에 하는게 좋다.
    public static void main(String[]args){
        Department d = new Department();
        d.mymain();
    }
}
