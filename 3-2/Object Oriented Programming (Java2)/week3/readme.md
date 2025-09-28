# 자바 3주 과제

Assignee: 조수민
Due Date: 2025년 9월 19일 → 2025년 9월 25일
Status: Done
Week: 3
🧑‍💻 공부 리스트: Object Oriented Programming (https://www.notion.so/Object-Oriented-Programming-26977b59e04280638a5ccbab42cf95ff?pvs=21)

# 1. 과제 개요

<aside>
💡

이름 : 조수민

학번 : 201540321

학과 : 컴퓨터공학과

</aside>

첨부된 입력파일을 이용하여 주식거래내역 클래스와 주식거래관리 클래스를 만드시오.

수업시간에 했던 1~7단계를 적용하여 단계적으로 기능을 확장하고 클래스 구조는 수업시간에 다룬 것과 동일하게 해 주세요.

주식거래내역 클래스에 월, 일, 단가, 개수 등은 int 타입 필드로 할 것

관리 클래스는 (배열에서 시작 생략 가능) ArrayList를 가지고 readAllRecords(), printAllRecords() 메소드를 가지게 할 것

주식거래내역의 출력 형식은 자유롭게 하되 줄 맞추어 출력되도록 할 것.

# 2. 구현 내용

## 1단계 - AI 도움 받은 부분

### 프로그램 기본 구조 설계

과제 요구사항에 맞는 `TradingRecord`와 `TradingManager` 두 클래스의 기본 뼈대 코드(스켈레톤 코드)를 제안받아 프로젝트를 시작했습니다.

```java
public class TradingManager {
    private ArrayList<TradingRecord> records;

    public TradingManager() {
        this.records = new ArrayList<>();
    }

    public void readAllRecords() {
        // 이 부분의 로직을 직접 구현함
    }

    public void printAllRecords() {
        // 이 부분의 로직을 직접 구현함
    }

    public static void main(String[] args) {
        // ...
    }
}
```

### 단계별 오류 해결 (디버깅)

- **`Scanner` 무한 대기 문제:** `while(scan.hasNext())` 구문이 콘솔 입력에서 종료되지 않는 원인을 배우고, `for` 반복문으로 수정하여 해결했습니다.
    
    ```java
    public void readAllRecords(Scanner scan) {
        // 총 개수를 읽지만 활용하지 않음
        int total = scan.nextInt(); 
    
        // 사용자가 입력을 마쳐도 계속 대기하는 문제가 발생
        while (scan.hasNext()) { 
            int month = scan.nextInt();
            // ... 이하 데이터 읽는 코드 ...
            records.add(new TradingRecord(...));
        }
    }
    // 변경 후
    public void readAllRecords(Scanner scan) {
        // 첫 줄에서 총 데이터 개수를 읽어옴
        int total = scan.nextInt(); 
    
        // 읽어온 개수(total)만큼 정확히 반복하고 종료됨
        for (int i = 0; i < total; i++) {
            int month = scan.nextInt();
            // ... 이하 데이터 읽는 코드 ...
            records.add(new TradingRecord(...));
        }
    }
    ```
    
- **`InputMismatchException` 해결:** `nextInt()` 사용 후 입력 버퍼에 남는 `\n`(줄바꿈) 문자가 문제의 원인임을 파악하고, `scan.nextLine()`으로 버퍼를 비워 해결했습니다.
    
    ```java
    public void readAllRecords(Scanner scan) {
        // 1. 숫자 48을 읽은 후, 줄바꿈 문자(\n)가 버퍼에 그대로 남음
        int total = scan.nextInt();
    
        // 2. 루프가 시작될 때 남아있는 \n이 문제를 일으킴
        for (int i = 0; i < total; i++) {
            int month = scan.nextInt(); // 여기서 예외 발생 가능성
            // ...
        }
    }
    
    //변경 후
    public void readAllRecords(Scanner scan) {
        // 1. 숫자 48을 읽음
        int total = scan.nextInt();
        
        // 2. 버퍼에 남아있는 줄바꿈 문자(\n)를 읽어서 버림 (버퍼를 정리)
        scan.nextLine(); 
    
        // 3. 깨끗해진 버퍼 상태에서 다음 입력을 안전하게 읽어옴
        for (int i = 0; i < total; i++) {
            int month = scan.nextInt();
            // ...
        }
    }
    ```
    

## 2단계 - 스스로 작성한 부분

### 스켈레톤 코드 기반의 단계적 구현

- AI가 구성한 뼈대 코드를 직접 구현했습니다.

```java
    public void readAllRecords(){
        System.out.println("주식 거래 내역을 아래에 붙여넣기 하세요 (첫 줄은 총 개수):");
        //첫 줄 총 거래 횟수
        int total = scan.nextInt();

        // 2. 위에서 읽고 남은 줄바꿈(Enter) 문자를 소비합니다. (이 줄이 추가되었습니다!)
        scan.nextLine();

        for (int i = 0; i < total; i++){
            int month = scan.nextInt();
            int day = scan.nextInt();
            String stockCode = scan.next();
            String tradeType = scan.next();
            int unitPrice = scan.nextInt();
            int quantity = scan.nextInt();

            //읽어온 데이터로 TradingRecord 객체 생성하여 리스트에 추가
            records.add(new TradingRecord(month, day, stockCode, tradeType, unitPrice, quantity));
        }
        System.out.println("총 " + records.size() + "개의 거래 내역을 읽었습니다.");
    }
```

### 메인 메소드의 역할 분리 및 객체 지향적 설계

- `main` 메소드는 프로그램의 진입점(Entry Point)으로서, `TradingManager`의 인스턴스를 생성하고 실제 로직이 담긴 `mymain` 메소드를 호출하는 최소한의 역할만 수행하도록 했습니다.

```java
public class TradingManager {
    // 인스턴스 변수 (객체가 소유하는 데이터)
    private ArrayList<TradingRecord> records;
    private Scanner scan;

    // ... (생성자 및 다른 메소드)

    public void mymain(){
        this.readAllRecords();
        this.printAllRecords();
        this.scan.close();
        System.out.println("프로그램을 종료합니다.");
    }

    public static void  main(String [] args){
        TradingManager manager = new TradingManager();
        manager.mymain();
    }
}
```

# 3. 실행 결과

![Screenshot 2025-09-23 at 9.57.10 PM.png](%EC%9E%90%EB%B0%94%203%EC%A3%BC%20%EA%B3%BC%EC%A0%9C%2027377b59e0428084ad97c9f136cb03bc/Screenshot_2025-09-23_at_9.57.10_PM.png)

# 4. Lesson Learn

- **`Scanner`의 입력 버퍼 관리:** `nextInt()`와 같은 메소드는 숫자만 소비하고 줄바꿈 문자(`\n`)는 버퍼에 남겨둔다는 사실을 명확히 알게 되었습니다. 이로 인해 `InputMismatchException` 등 예기치 않은 오류가 발생할 수 있으며, `scan.nextLine()`을 통해 의도적으로 버퍼를 비워주는 작업이 왜 필수적인지 이해했습니다. 이는 안정적인 콘솔 입력을 위해 반드시 기억해야 할 중요한 원칙입니다.
- **정교한 출력 제어를 위한 형식 지정자 활용:** `printf` 사용 시 `%-4s`와 `%4s`의 차이점을 통해 출력 정렬을 제어하는 법을 익혔습니다.  플래그의 유무가 **왼쪽 정렬**과 **오른쪽 정렬**을 결정한다는 것을 배우며, 이를 통해 데이터를 훨씬 가독성 높은 표 형태로 표현할 수 있었습니다. 이는 기능 구현뿐만 아니라 결과물을 깔끔하게 표현하는 능력의 중요성을 깨닫게 했습니다.
- **`main` 메소드의 역할 분리:** `static` 영역인 `main` 메소드에 모든 로직을 구현하는 대신, 객체를 생성하고 실제 로직은 인스턴스 메소드(`mymain`)에 위임하는 습관을 들였습니다. 이는 `main` 메소드를 프로그램의 간결한 진입점(Entry Point)으로 유지하고, 핵심 기능은 객체의 상태와 메소드를 온전히 활용하는 **객체 지향 설계 원칙**을 따르는 좋은 방법임을 배웠습니다.