package application;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller implements Initializable {
	Board user = new Board();
	
	@FXML
	private Button btnGameStart;
	
	@FXML
	private Button btnRanking;
	
	@FXML
	private Button btnHelp;
	
	@FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() == btnGameStart) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         // 게임 실행 메서드
//          2022.10.22 : 게임 룰 살짝 변경. 시간이 다 될때 까지 문제를 풀고,
//          주어진 제한 시간이 종료되면 풀고 있는 문제를 마지막으로 풀면 게임이 종료된다. 
          Correct correct = new Correct();
          Correct.setScore(0);   // 점수 초기화
          Correct.setCount(0);   // count 초기화
          
          int answer;   // 정답
          Random rand = new Random();   
          char[] mark = { '+', '-', '÷', '*' };   // 연산 기호
          correct.sign();   // 시작 메세지 + 3초의 준비 시간
          correct.start();   // 타이머 시작
          // 게임 시작
          while (correct.is_Time_out()) { // 제한시간이 다 되면 게임 종료
             
             int a = (int) (Math.random() * 9 + 1); //랜덤값
             int b = (int) (Math.random() * 9 + 1);//랜덤값 지정
             char randomMark = mark[rand.nextInt(4)];
             switch (randomMark) {
             case '*':
                System.out.println(a + " x " + b + " 는?");
                answer = Integer.parseInt(br.readLine());
                
                if (answer == a * b) {
                   correct.true1(); // Correct 클래스로 넘어가서 정답을 리턴
                   break;
                }
                else if(answer == 9999){
                   correct.setEnd(false);
                }else {
                   correct.false1();// Correct 클래스로 넘어가서 틀림을 리턴
                   break;
                }
             case '+':
                
                System.out.println(a + " + " + b + " 는?");
                answer = Integer.parseInt(br.readLine());
                if (answer == a + b) {
                   correct.true1();
                   break;
                } else if(answer == 9999){
                   correct.setEnd(false);
                }else {
                   correct.false1();
                   break;
                }
             case '-':
                
                System.out.println(a + " - " + b + " 는?");
                answer = Integer.parseInt(br.readLine());
                if (answer == a - b) {
                   correct.true1();
                   break;
                }else if(answer == 9999){
                   correct.setEnd(false);
                }else {
                   correct.false1();
                   break;
                }
             case '/':
                
                if (a % b == 0) {
                   System.out.println(a + " ÷ " + b + " 는?");
                   answer = Integer.parseInt(br.readLine());
                   if (answer == a / b) {
                      correct.true1();
                      break;
                   }else if(answer == 9999){
                      correct.setEnd(false);
                   } else {
                      correct.false1();
                      break;
                   }
                } else {
                   break;
                }

             }
             if (Correct.getCount()==5) {
                System.out.println("☆2단계☆");
                while (correct.is_Time_out() && Correct.getCount()<10) {
                   int c = (int) (Math.random() * 9 + 11);
                   int d = (int) (Math.random() * 9 + 11);
                   char randomMark2 = mark[rand.nextInt(4)];
                   switch (randomMark2) {
                   case '*':
                      System.out.println(c + " x " + d + " 는?");
                      answer = Integer.parseInt(br.readLine());
                      if (answer == c * d) {
                         correct.true2();
                         break;
                      }else if(answer == 9999){
                         correct.setEnd(false);
                      } else {
                         correct.false2();
                         break;
                      }
                   case '+':
                      System.out.println(c + " + " + d + " 는?");
                      answer = Integer.parseInt(br.readLine());
                      if (answer == c + d) {
                         correct.true2();
                         break;
                      }else if(answer == 9999){
                         correct.setEnd(false);
                      } else {
                         correct.false2();
                         break;
                      }
                   case '-':
                      System.out.println(c + " - " + d + " 는?");
                      answer = Integer.parseInt(br.readLine());
                      if (answer == c - d) {
                         correct.true2();
                         break;
                      }else if(answer == 9999){
                         correct.setEnd(false);
                      } else {
                         correct.false2();
                         break;
                      }

                   case '/':
                      if (c % d == 0) {
                         System.out.println(c + " ÷ " + d + " 는?");
                         answer = Integer.parseInt(br.readLine());
                         if (answer == c / d) {
                            correct.true2();
                            break;
                         }else if(answer == 9999){
                            correct.setEnd(false);
                         } else {
                            correct.false2();
                            break;
                         }
                      } else {
                         break;
                      }
                      
                   }
                }
             } 
             
             if (Correct.getCount()==10) {
                System.out.println("☆3단계☆");
                while (correct.is_Time_out()) {
                   int c = (int) (Math.random() * 9 + 11);
                   int d = (int) (Math.random() * 9 + 11);
                   int e = (int) (Math.random() * 9 + 11);
                   char randomMark2 = mark[rand.nextInt(4)];
                   switch (randomMark2) {
                   case '*':
                      System.out.println(c + " x " + d + " + " + e +" 는?");
                      answer = Integer.parseInt(br.readLine());
                      if (answer == c * d + e) {
                         correct.true2();
                         break;
                      } else {
                         correct.false2();
                         break;
                      }
                   case '+':
                      System.out.println(c + " x " + "(" + d + " + " + e +")" + " 는?");                  
                      answer = Integer.parseInt(br.readLine());
                      if (answer == c * (d+e)) {
                         correct.true2();
                         break;
                      }else if(answer == 9999){
                         correct.setEnd(false);
                      } else {
                         correct.false2();
                         break;
                      }
                   case '-':
                      System.out.println("(" + c + " - " + d + ")" + " x " +e + " 는?");
                      answer = Integer.parseInt(br.readLine());
                      if (answer == (c - d)*e) {
                         correct.true2();
                         break;
                      }else if(answer == 9999){
                         correct.setEnd(false);
                      } else {
                         correct.false2();
                         break;
                      }

                   case '/':
                      if (c % d == 0) {
                         System.out.println(c + " ÷ " + d + " x " + e + " 는?");
                         answer = Integer.parseInt(br.readLine());
                         if (answer == c / d *e) {
                            correct.true2();
                            break;
                         }else if(answer == 9999){
                            correct.setEnd(false);
                         } else {
                            correct.false2();
                            break;
                         }
                      } else {
                         break;
                         
                      }
                      
                   }
                }
             } 
          }
          
          System.out.println("게임이 종료되었습니다!");
          System.out.println("총점수:" + correct.getScore());
          
          System.out.println("랭킹에 본인의 점수를 기록하시겠습니까?   기록을 원하시면 y를, 아니라면 아무 키나 눌러주세요.");
          String res = br.readLine();   // 사용자의 응답 -> y or 아무키나 입력 
          if (res.equalsIgnoreCase("y")) {   // y 입력 시 본인의 이름을 입력하면, 해당 판의 점수와 같이 기록됨.
             
             System.out.println("본인의 이름을 입력해주세요! >>");
             String user_n = br.readLine();
             user.insertUser(user_n, Integer.toString(correct.getScore()));
             System.out.println("기록이 저장되었습니다! 게임이 종료되었습니다.");
             
          } else {   // 기록을 저장하기 원하지 않는다면, 그냥 메인 화면으로 돌아감.
             
             System.out.println("기록을 저장하지 않습니다. 게임이 종료되었습니다.");   
          }

        } else if (mouseEvent.getSource() == btnRanking) {
        	System.out.println();
        	System.out.println("< 랭킹 TOP 10 >");
            user.selectRanker();
            
        } else if (mouseEvent.getSource() == btnHelp) {
            loadStage("Help.fxml");
        }
    }
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {

    }
	
	// fxml 실행
    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Board {
	// 이클립스와 DB 연동
	private Connection conn = null;
	// DBMS로 접속할 DB명 / url 양식 -> jdbc:mysql://(사용자명):(포트번호)/(사용할 데이터베이스)
	private String url = "jdbc:mysql://localhost:3306/team1";	
	private String user = "root";	// mysql 사용자	-> 본인이 돌려보고 싶으면 본인꺼 입력!!
	private String password = "1234";	// mysql 비밀번호 -> 본인이 돌려보고 싶으면 본인꺼 입력!!
	private String sql = "";	// 입력한 sql 문 -> 본인이 돌려보고 싶으면 본인꺼 입력!!
	
	// 생성자
	public Board() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Loading...");
			System.out.println();
			
		} catch (Exception e) {
			System.out.println("데이터 불러오기에 실패하였습니다.");
			System.out.println();
			
			try {
				conn.close();
			} catch (SQLException e1) {
				
			}
		}
	}
	
	// insertBoard() 설정
	
//	create table users (
//			user_name varchar(50) not null,
//			user_no int auto_increment primary key,
//		    score int not null
//		);
//	users 테이블은 다음과 같이 만들어져 있으니 참고!
	
	public void insertUser(String u_name, String score) {
		
		sql = "insert into users values(?, ?, ?)";	// users 테이블에 값이 (?, ?, ?) 데이터를 삽입
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_name);	// 데이터값 설정
			pstmt.setString(2,  null);
			pstmt.setString(3, score);
			
			int result = pstmt.executeUpdate();	// 데이터가 저장되면 result = 1 이 됨.
			
			if(result == 1) {	// 저장 성공 시 출력문구
				System.out.println("유저의 정보가 저장되었습니다.");
			}
			
		} catch (Exception e) {	// 저장 실패시 출력문구
			System.out.println("저장에 실패하였습니다.");
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {
				
			}
		}
	}
	
	// 상위 10명을 출력
	public void selectRanker() {
		// sql = users 테이블에서 데이터들을 score 를 기준으로 내림차순 정렬하되, 10개의 데이터만 출력
		String sql = "select * from users order by score desc limit 10";
		PreparedStatement pstmt = null;
		int rank = 1;
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println(rank++ + "\t" + rs.getInt("score") + "\t" + rs.getString("user_name"));
//				System.out.println("유저 이름: " + rs.getString("user_name"));
//				System.out.println("점수: " + rs.getInt("score"));
			}
		} catch (Exception e) {
			System.out.println("예외가 발생하였습니다.");
		} finally {
			try {
				if (pstmt!=null && pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {
				
			}
		}		
	}	
}


class Correct extends Thread {
//   2022.10.22 : 게임 룰 살짝 변경. 시간이 다 될때 까지 문제를 풀고,
//   주어진 제한 시간이 종료되면 풀고 있는 문제를 마지막으로 풀면 게임이 종료된다. 
   private boolean end = true;
   private static int count = 0;   // 문제를 맞힌 개수
   private static int score = 0;   // 점수
   
   @Override
   public void run() {
      
      while (isEnd()) {
         for(int i=60; i>=1; i--) {
            if(isEnd()==true) {
            try {
               Thread.sleep(1000);
            } catch (InterruptedException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }else {
            break;
         }
         }
         System.out.println("마지막 문제를 풀면 게임이 종료됩니다.");
         setEnd(false);
      }
   }
   // 게임시작 시 출력되는 사전 문구와 3초의 준비시간을 주는 메소드 
   public void sign() {
	  System.out.println("=======================");
      System.out.println("=====시간 제한 60초====");
      System.out.println("========암산왕=========");
      System.out.println("==9999입력시 게임종료==");
      System.out.println("==     게임시작     ===");
      // 3초 뒤에 시작
      try {
         for (int i = 3; i >= 0; i--) {
            Thread.sleep(1000);
            System.out.println(i + "초 뒤에 시작합니다!");
         }
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }

   public void true1() {
      System.out.println("정답입니다.");
      count++;
      score += 100;
   }

   public void false1() {
      System.out.println("오답입니다.");
   }

   public void true2() {
      System.out.println("정답입니다.");
      count++;
      score += 200;
   }

   public void false2() {
      System.out.println("오답입니다.");
   }
   public void true3() {
      System.out.println("정답입니다.");
      count++;
      score += 300;
   }

   public void false3() {
      System.out.println("오답입니다.");
   }

   public boolean is_Time_out() {
      return isEnd();
   }

   public void setSolution(boolean solution) {
      this.setEnd(solution);
   }

   public static int getCount() {
      return count;
   }

   public static void setCount(int count) {
      Correct.count = count;
   }

   public static int getScore() {
      return score;
   }

   public static void setScore(int score) {
      Correct.score = score;
   }
   public boolean isEnd() {
      return end;
   }
   public void setEnd(boolean end) {
      this.end = end;
   }
}