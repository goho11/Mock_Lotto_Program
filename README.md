<h1 align="center"> 주식 투자 시뮬레이션 프로그램 </h1>

![image](https://github.com/user-attachments/assets/33221cb0-77ec-42af-9bbf-11ad04f85d05)

 
<h2>목적</h2>
<p align="center">주식 경험이 없는 사용자가</p>
<p align="center">투자 전략 수집 및 리스크 관리를 통해 실제 주식 투자와 유사한 거래 체험</p>  
<p align="center">주가 변동을 차트로 시각화하여 사용자가 주식 시장의 흐름을 직관적 확인 가능</p>

<p align="center">
  <h2>사용도구</h2>
 
 <img src="https://img.shields.io/badge/Java-%23FF7800"> <img src="https://img.shields.io/badge/Eclipse-2C2255?logo=eclipseide&logoColor=white"> <img src="https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white">   

## 프로젝트 배경 
- 가상 투자 환경   
  실제 주식 시장 데이터 기반으로 사용자가 다양한 투자 전략 실험 가능한 환경 조성   
  이를 통해 실제 투자 전, 위험 없이 투자 경험을 겪고 자신감 증진 가능
  
- 리스크 관리 및 전략 수립   
주식 거래에 중요한 리스크 관리와 전략 수립 중요성을 실전처럼 체험   
사용자의 선택에 따라 변동성 높은 시장에서 어떻게 리스크를 줄이고, 효과적인 투자 전략 수립을 학습

## 주요 핵심 기능
### 로그인 후 메인 화면   
원금, 금액, 수익, 수익률, 회사별 주식 가격   
![전체 회사 주식 정보 최초 화면](https://github.com/user-attachments/assets/895b484d-0024-4d7d-89af-895cdff356b5)

### 내 정보 화면   
통장 잔고, 원금, 수익, 수익률, 평가 금액, 회사별 보유 주식 상황   
![내 주식 현황 화면](https://github.com/user-attachments/assets/5c4d254f-de0b-4a46-9dad-bb2f8ce855ec)

### 나의 거래 내역 보기 화면   
내 투자(평가 금액), 잔고, 주식 거래 내역   
![내 주식 거래 현황 화면](https://github.com/user-attachments/assets/c0164455-506b-433a-8c9c-954d16a3d48c)

### 오늘의 뉴스 화면
주가 변동에 대한 힌트를 제공      
![뉴스 화면](https://github.com/user-attachments/assets/6c435d4a-2693-47c4-b14f-20792738367f)

### 그래프 차트
날짜(X축)별 주식 가격(Y축) 변동을 확인 가능   
![graph chart](https://github.com/user-attachments/assets/94d892f3-f79e-446a-b3b0-105c86ccf019)

### 매수 화면
![매수 화면](https://github.com/user-attachments/assets/36b2001f-dbd6-49d6-841f-a5391354b537)

## 진행과정
- 테이블 명세서 구성   
![image](https://github.com/user-attachments/assets/8a6af56e-f048-4277-bbfe-bef25674a03f)

- 동작방식 및 데이터 흐름 파악   
　1. 사용자 로그인 : 계정을 통해 개인 투자 상황 관리   
　2. 검색 및 정보 열람 : 주식 종목 검색과 정보 열람   
　3. 주식 구매 결정 : 매수 또는 매도 결정을 내릴 수 있는 인터페이스   
　4. 구매 완료 및 주가 변동 : 거래 완료 후 시뮬레이션된 시장 정보에 따라 주가 변동   
　5. 시뮬레이션 진행 : 주기적인 시장 상황 업데이트로 최대 90일 진행   
　6. 시뮬레이션 종료 및 결과 확인 : 투자 성과를 시각적 제공   

- RDBMS를 사용한 데이터 관리 및 연결      
  Java 프로그램과 DB를 연결해 데이터를 주고 받기 위한 JDBC 방식 사용   
  DB연결 객체 생성, PreparedStatement 인터페이스를 통해 SQL 쿼리를 실행하여 데이터를 주고 받음

## Authors
GitHub [@goho11](https://github.com/goho11)
