package 메인.도박장;

import 메인.플레이어;

import java.util.Scanner;

public class 도박장 {

    투기장게임 투기장게임 = new 투기장게임();
    야바위게임 야바위게임 = new 야바위게임();
    가위바위보게임 가위바위보게임 = new 가위바위보게임();

    public void 도박장기본(플레이어 플레이어){
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("어떤 게임을 하러 가시겠습니까?");
            System.out.println("1. 투기장 게임");
            System.out.println("2. 가위바위보 게임");
            System.out.println("3. 야바위 게임");
            System.out.println("4. 도박장에서 나간다");
            int 어디갈지 = sc.nextInt();
            if (어디갈지 == 1) {
                투기장게임.투기장시작(플레이어);
            } else if (어디갈지 == 2) {
                가위바위보게임.가위바위보게임시작(플레이어);
            } else if (어디갈지 == 3) {
                야바위게임.야바위게임시작(플레이어);
            }else {
                break;
            }
        }
    }
}
