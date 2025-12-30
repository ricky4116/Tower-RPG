package 메인.도박장;

import 메인.플레이어;

import java.util.Random;
import java.util.Scanner;

public class 야바위게임 {
    Random ran = new Random();
    Scanner sc = new Scanner(System.in);
    public int 배팅금액,깔대기선택,랜덤나오기;
    public void 야바위게임시작(플레이어 플레이어){
        int 선택지=4;
        while(true){
            System.out.println("야바위 게임에 들어왔습니다");
            System.out.println("몇개의 깔대기로 야바위를 하시겠습니까?             보유골드:" + 플레이어.보유골드 + "G");
            System.out.println("구슬이 있는 깔대기 선택시 (깔대기의 개수x배팅한 금액)으로 정산됩니다");
            System.out.println("1. 3개");
            System.out.println("2. 6개");
            System.out.println("3. 9개");
            System.out.println("4. 나간다");
            선택지 = sc.nextInt();
            if(선택지==4) {
                break;
            }
            System.out.println("얼마나 배팅하시겠습니까?");
            배팅금액 = sc.nextInt();
            if (선택지 == 1) {
                세개야바위(플레이어);
            } else if (선택지 == 2) {
                여섯개야바위(플레이어);
            } else if (선택지 == 3) {
                아홉개야바위(플레이어);
            }
        }
    }
    public void 세개야바위(플레이어 플레이어){
        System.out.println("어떤 숫자의 깔대기를 고르시겠습니까?");
        야바위UI(1);
        깔대기선택 = sc.nextInt();
        랜덤나오기 = ran.nextInt(3)+1;
        야바위보상(플레이어,3);
    }
    public void 여섯개야바위(플레이어 플레이어){
        System.out.println("어떤 숫자의 깔대기를 고르시겠습니까?");
        야바위UI(1);
        야바위UI(4);
        깔대기선택 = sc.nextInt();
        랜덤나오기 = ran.nextInt(6)+1;
        야바위보상(플레이어,6);
    }
    public void 아홉개야바위(플레이어 플레이어){
        System.out.println("어떤 숫자의 깔대기를 고르시겠습니까?");
        야바위UI(1);
        야바위UI(4);
        야바위UI(7);
        깔대기선택 = sc.nextInt();
        랜덤나오기 = ran.nextInt(9)+1;
        야바위보상(플레이어,9);
    }

    public void 야바위보상(플레이어 플레이어,int 야바위종류){
        if(깔대기선택==랜덤나오기){
            System.out.println("축하합니다 구슬이 있는 깔대기를 정확하게 찾았습니다");
            System.out.println("보상으로 "+배팅금액*야바위종류+"의 골드를 얻었습니다");
            System.out.println();
            플레이어.보유골드+=(야바위종류-1)*배팅금액;
        }else {
            System.out.println("아쉽습니다 구슬이 있는 깔대기를 찾지 못했습니다");
            System.out.println("배팅한 금액 "+배팅금액+"G를 모두 잃었습니다" );
            System.out.println();
            플레이어.보유골드-=배팅금액;
        }
    }

    public void 야바위UI(int num){
        System.out.println("****  ****  ****  ");
        System.out.println("*  *  *  *  *  *");
        System.out.println("*  *  *  *  *  *");
        System.out.println(" "+num+"     "+(num+1)+"     "+(num+2));
    }
}
