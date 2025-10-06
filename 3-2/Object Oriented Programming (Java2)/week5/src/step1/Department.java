package step1;  // 강의노트 p.7
import java.util.ArrayList;
import java.util.Scanner;

public class Department {
	Scanner scan = new Scanner(System.in);
	ArrayList<Student> stList = new ArrayList<>();;
    ArrayList<Team> teamList = new ArrayList<>();
	void mymain() {
		readStudents();
		printStudents();
        readTeams();
        printTeams();
        search();
//        inputScores();
//        searchScore();
	}

    public Student getStudent(int num){
        return stList.get(num - 1);
    }

    void printTeams(){
        for (Team t : teamList){
            t.print();
        }
    }

    void readTeams(){
        String name;
        while(true){
            name = scan.next();
            if (name.equals("0")) break;
            Team t = new Team(name);
            t.read(scan, this);
            teamList.add(t);
        }
    }
    void searchScore(){
        String kwd;
        //남아있는 엔터를 없애야한다.
        scan.nextLine();
        while (true){
            System.out.print("점수구간: ");
            int start = scan.nextInt();
            int end = scan.nextInt();
            if(start == 0 && end == 0) break;
            for (Student st: stList){
                if (st.matchesScore(start, end))
                    st.print();
            }
        }
    }
    void inputScores(){
        for (Student st: stList){
            st.inputScore(scan);
        }

    }

    // [5단계] 2017 -2 김
    void search(){
        String kwd;
        //남아있는 엔터를 없애야한다.
        scan.nextLine();
        while (true){
            System.out.print("키워드: ");
            kwd = scan.nextLine();
            if(kwd.equals("end")) break;
            for (Student st: stList){
                String[] kwds = kwd.split(" ");
                if (st.matches(kwds))
                    st.print();
            }
        }
    }
	void readStudents() {
		Student st;
		String id;
		while (true) {
			id = scan.next();
			//System.out.println("id: "+id);
			if (id.equals("0")) break;
			st = new Student(id);
			st.read(scan);
			stList.add(st);
		}
	}
	void printStudents() {
		for (Student st: stList) {
			st.print();
		}
	}
	public static void main(String[] args) {
		Department m = new Department();
		m.mymain();
	}
}
