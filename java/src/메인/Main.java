package 메인;

import 메인.도박장.도박장;
import 소비.물약;

import java.util.Scanner;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {


        플레이어 플레이어 = new 플레이어();
        장비상점 장비상점 = new 장비상점();
        던전 던전 = new 던전();
        소비상점 소비상점 = new 소비상점();
        물약 물약 = new 물약();
        도박장 도박장 = new 도박장();
        대장간 대장간 = new 대장간();
        튜토리얼 튜토리얼 = new 튜토리얼();
        Thread 튜토리얼실행쓰레드 = new Thread(튜토리얼);
        튜토리얼실행쓰레드.start();
        try {
            튜토리얼실행쓰레드.join(); //이 쓰레드가 종료될 떄까지 메인쓰레드는 대기
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JFrame 타이머 = new JFrame("타이머");  //타이머 프레임 생성
        타이머.setSize(300, 250);
        시간타이머 시간타이머 = new 시간타이머();
        타이머.add(시간타이머);
        Thread 시간타이머쓰레드 = new Thread(시간타이머);
        시간타이머쓰레드.start();
        타이머.setVisible(true);

        플레이어.장착한장비.add(new 장비("무기"));
        플레이어.장착한장비.add(new 장비("방어구"));
        플레이어.기본스탯설정();
        물약.기본물약설정(플레이어);
        while (메인.던전.floor <= 20) {
            플레이어.장비적용();
            Scanner 플레이어선택지 = new Scanner(System.in);
            System.out.println("어디로 갈까?");
            System.out.println("1. 던전에 입장한다                 6. 가방을 연다");
            System.out.println("2. 장비 상점에 간다                7. 내 능력치를 본다");
            System.out.println("3. 소비 상점에 간다");
            System.out.println("4. 대장간에 간다");
            System.out.println("5. 도박장에 간다");
            int 선택지 = 플레이어선택지.nextInt();
            if (선택지 == 1) {
                플레이어.전직결정하기();
                던전.던전입장(플레이어);
            } else if (선택지 == 2) {
                장비상점.장비상점입장(플레이어);
            } else if (선택지 == 3) {
                소비상점.소비상점입장(플레이어);
            } else if (선택지 == 4) {
                대장간.대장간입장(플레이어);
            } else if (선택지 == 5) {
                도박장.도박장기본(플레이어);
            } else if (선택지 == 6) {
                플레이어.가방보기();
            } else {
                플레이어.플레이어스탯();
            }

        }
    }
}

