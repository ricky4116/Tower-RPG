package 메인.도박장;

import 메인.플레이어;

import java.util.Random;
import java.util.Scanner;

public class 가위바위보게임 {

    Scanner sc = new Scanner(System.in);
    Random ran = new Random();
    public int 몇배=2;
    public String 누가승리;
    public void 가위바위보게임시작(플레이어 플레이어){
        while (true){
            System.out.println("가위바위보 게임에 들어왔습니다" );
            System.out.println("1. 가위바위보 게임에 대한 설명을 듣는다");
            System.out.println("2. 가위바위보 게임을 시작한다");
            System.out.println("3. 나간다");
            int 선택 = sc.nextInt();
            if (선택 == 1) {
                가위바위보게임설명();
            } else if (선택 == 2) {
                가위바위보하기(플레이어);
            }else {
                break;
            }
        }

    }

    public void 가위바위보하기(플레이어 플레이어){ // 1 - 가위  2 - 바위  3 - 보  1/2 2이김 2/3 3이김 3/1 1이김
       int 딜러내는것=0,내가내는것=0;
        System.out.println("얼마를 배팅하겠습니까?");
        int 배팅금액=sc.nextInt();
        while(true){
            if(배팅금액>플레이어.보유골드){
                System.out.println("플레이어의 돈이 부족하여 배팅을 할 수 없습니다");
                break;
            }else {
                플레이어.보유골드-=배팅금액;
            }
            System.out.println("어떤 것을 내겠습니까?     현재배율 : "+몇배+"배       배팅금액 : "+배팅금액+"G");
            System.out.println("1. 가위  2.바위  3.보");
            딜러내는것 = ran.nextInt(3);
            내가내는것 = sc.nextInt();
            if(딜러내는것==내가내는것) {
                System.out.println("서로 똑같은 것을 내었습니다");
                System.out.println("다시 가위바위보를 시작합니다");
            } else{
                누가승리 = 가위바위보비교(딜러내는것, 내가내는것);
                if (누가승리.equals("딜러승리")) {
                    System.out.println("딜러가 승리했습니다");
                    몇배 = 2;
                    System.out.println("플레이어가 패배하여 "+배팅금액+"G를 잃었습니다");
                    플레이어.보유골드-=배팅금액;
                    break;
                } else {
                    몇배++;
                    System.out.println("플레이어가 승리하였습니다");
                    System.out.println("계속하시겠습니까?   현재배율 : "+몇배+"배       보유금액 : "+배팅금액+"G");
                    System.out.println("1. 계속한다        2.그만둔다");
                    int 선택 = sc.nextInt();
                    if(선택==2) {
                        System.out.println("플레이어가 승리를 하여 "+(배팅금액*몇배)+"G를 얻었습니다");
                        플레이어.보유골드+=배팅금액*몇배;
                        break;
                    }
                }
            }
        }

    }

    public String 가위바위보비교(int 딜러내는것,int 내가내는것){
        if((딜러내는것==2 && 내가내는것 ==1) ||(딜러내는것==3 && 내가내는것==2) || (딜러내는것==1 && 내가내는것==3)){
            return "딜러승리";
        }else {
            return "플레이어승리";
        }
    }
    public void 가위바위보게임설명(){
        System.out.println("_____________________________________________");
        System.out.println("한 번 이기면 2배의 배당금을 얻을수 있습니다");
        System.out.println("계속해서 이기면 +1배만큼 배율이 올라갑니다");
        System.out.println("Stop을 하면 지금 배율에 따른 금액을 얻을 수 있고");
        System.out.println("Go를 하면 계속해서 배율을 추가할 수 있습니다");
        System.out.println("그러나 지면 배팅한 금액은 모두 사라지게 됩니다");
        System.out.println("_____________________________________________");
    }

}
