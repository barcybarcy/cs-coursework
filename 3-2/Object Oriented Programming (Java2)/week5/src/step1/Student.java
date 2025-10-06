package step1;

import java.util.ArrayList;
import java.util.Scanner;

public class Student {
	// 201611012 김성관 2 010-9148-2689
	String id;
	String name;
	int year;
	String phone;
    //int score = -1;
    ArrayList<Team> teams = new ArrayList<>();
	Student(String id) {
		this.id = id;
	}
    void setTeam(Team t){
        teams.add(t);
    }
	public void read(Scanner scan) {
		// TODO Auto-generated method stub
		//id = scan.next();
		name = scan.next();
		year = scan.nextInt();
		phone = scan.next();
	}
	public void print() {
            System.out.printf("[%s] %s %d학년 (%s)", id, name, year, phone);
            if(teams.size() > 0)
                for(Team t : teams)
                    System.out.println(t.name);

//        if (score > -1)
//		    System.out.printf("%d점", score);
            System.out.println();
	}

    public boolean matches(String kwd){
        // TODO Auto-generated method stub
        if (kwd.length() == 1){
            if ((""+year).equals(kwd))
                return true;
        }
        if (kwd.length() >= 4){
            if (id.contains(kwd)) return true;
            if (phone.contains(kwd)) return true;
        }
        return name.contains(kwd);
    }

    public boolean matches(String[] kwds) {
        for (String kwd: kwds){
            if (kwd.charAt(0) == '-'){
                //처음 - 뺀다.
                if (matches(kwd.substring(1)))
                    return false;
            } else if (!this.matches(kwd))
                return false;
        }
        return true;
    }

    public void inputScore(Scanner scan) {
        System.out.println(this.name + ":");
        this.score = scan.nextInt();
    }

    public boolean matchesScore(int start, int end) {
        return start <= score && score <= end;
    }
}
