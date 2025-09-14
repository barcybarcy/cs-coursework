# Week1 과제

## 1. 과제 개요

학번: 201540321

이름: 조수민

## 2. 과제 요약

본 과제에서는 사용자로부터 입력받은 개수만큼 1~100 사이의 난수를 생성하고, 생성된 난수 중 최대값과 최소값을 출력하는 프로그램을 작성하였다. Java에서의 기본적인 입출력, Random 클래스 사용법, 조건문을 통한 값 비교 등의 기초적인 프로그래밍 개념을 학습하는 것이 주된 목표이다.

## 3. 구현 내용

### 3.1 AI 도움 받은 부분

코드 틀, boiler template를 받아서 내가 어떻게 코드를 짜나가야하는지 명확히 인식하게되었다.

```java
currentNumber = randomGenerator.nextInt(100) + 1;
```

randomGenerator에서 0도 return할 수 있기 때문에 자연수 난수를 만든다면 + 1 을 해야한다는 점 인식하였다.

### 3.2 스스로 작성한 부분

for문을 반복해나가면서 maximumValue, minimumValue를 설정해나가는 코드

```java
            if (currentNumber > maximumValue){
                maximumValue = currentNumber;
            }
            if (currentNumber < minimumValue){
                minimumValue = currentNumber;
            }
```

## 4. 실행 결과

![Screenshot 2025-09-09 at 7.55.58 PM.png](attachment:35c2350e-3b4f-4c24-8c2e-f124df9abadf:Screenshot_2025-09-09_at_7.55.58_PM.png)

## 5. Lesson Learn

maximumValue는 범위의 가장 작은값, minimumValue는 범위의 가장 높은값으로 초기화해야한다는 점을 구체적으로 인식했다.