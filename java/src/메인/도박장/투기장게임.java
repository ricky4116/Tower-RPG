package 메인.도박장;

import 메인.플레이어;

import java.util.Random;
import java.util.Scanner;

public class 투기장게임{
    Scanner sc = new Scanner(System.in);
    검투사 검투사A = new 검투사("검투사A",50,5);
    검투사 검투사B = new 검투사("검투사B",60,3);
    Random ran = new Random();
    public double A배당,B배당;

    public void 투기장시작(플레이어 플레이어){
        int 선후공 = ran.nextInt(2);
        검투사A설정(); 검투사B설정();
        배당계산();
        System.out.println("투기장게임에 들어왔습니다");
        System.out.println("A와 B중에서 어떤 검투사의 승리에 거시겠습니까?");
        System.out.println("두 검투사는 공격확률이 동일하게 50%입니다");
        System.out.println("1. 검투사A    배당:"+A배당);
        System.out.println("2. 검투사B    배당:"+B배당);
        System.out.println("3. 투기장에서 나간다");
        int 투기장선택=sc.nextInt();
        if(투기장선택==1 || 투기장선택==2) {
            if(투기장선택==1){
            System.out.println("검투사A를 선택하였습니다");
            }
            else {
                System.out.println("검투사B를 선택하였습니다");
            }
            System.out.println("검투사A    배당:" + A배당 + " || 검투사B    배당:" + B배당);
            System.out.println("얼마를 거시겠습니까?               보유금액 : " + 플레이어.보유골드);
            int 금액얼마낼지 = sc.nextInt();
            if (플레이어.보유골드 < 금액얼마낼지) {
                System.out.println("돈이 부족합니다");
                System.out.println("투기장에서 쫒겨났습니다");
            } else {
                if (선후공 == 1) {
                    System.out.println("선공은 검투사A입니다");
                } else {
                    System.out.println("선공은 검투사B입니다");
                }
                System.out.println("검투사 A   체력:" + 검투사A.검투사체력 + " 공격력:" + 검투사B.검투사공격력);
                System.out.println("검투사 B   체력:" + 검투사B.검투사체력 + " 공격력:" + 검투사B.검투사공격력);
                while (검투사A.검투사체력 > 0 && 검투사B.검투사체력 > 0) {
                    if (선후공 == 1) {
                        공격하기(검투사A, 검투사B);
                        if (검투사B.검투사체력 < 0) break;
                    } else {
                        공격하기(검투사B, 검투사A);
                        if (검투사A.검투사체력 < 0) break;
                    }
                }
                if (투기장선택 == 1 && 검투사B.검투사체력 < 0) {
                    System.out.println("축하합니다 A검투사가 승리하였습니다.");
                    System.out.println((int) (금액얼마낼지 * A배당) + "G를 획득하였습니다");
                    플레이어.보유골드 = 플레이어.보유골드 - 금액얼마낼지 + (int) (금액얼마낼지 * A배당);
                } else if (투기장선택 == 2 && 검투사A.검투사체력 < 0) {
                    System.out.println("축하합니다 B검투사가 승리하였습니다.");
                    System.out.println((int) (금액얼마낼지 * B배당) + "G를 획득하였습니다");
                    플레이어.보유골드 = 플레이어.보유골드 - 금액얼마낼지 + (int) (금액얼마낼지 * B배당);
                } else {
                    System.out.println("선택하신 검투사가 승리하지 못해서 " + 금액얼마낼지 + "G를 잃었습니다");
                    플레이어.보유골드 -= 금액얼마낼지;
                }
            }
        }
    }

    public void 배당계산(){
        int 총합;
        총합 = (검투사A.검투사체력/ 검투사B.검투사공격력)+(검투사B.검투사체력/ 검투사A.검투사공격력);
        B배당 = 4*(double)(검투사A.검투사체력/ 검투사B.검투사공격력)/(총합);
        B배당=Math.round(B배당*100)/100.0;
        A배당 = 4-B배당;
        A배당=Math.round(A배당*100)/100.0;
    }

    public void 검투사A설정(){
        int A체력랜덤 = ran.nextInt(30);
        int A랜덤 = ran.nextInt(5);
        검투사A.검투사체력+=A체력랜덤;
        검투사A.검투사공격력+=A랜덤;
    }
    public void 검투사B설정(){
        int B체력랜덤 = ran.nextInt(30);
        int B랜덤 = ran.nextInt(5);
        검투사B.검투사체력+=B체력랜덤;
        검투사B.검투사공격력+=B랜덤;
    }

    public void 공격하기(검투사 선공검투사,검투사 후공검투사){
        int 랜덤공격 = ran.nextInt(100)+1;
        if(랜덤공격<=50){
            후공검투사.검투사체력-=선공검투사.검투사공격력;
        }
        랜덤공격 = ran.nextInt(100)+1;
        if(랜덤공격<=50){
            선공검투사.검투사체력-=후공검투사.검투사공격력;
        }
    }
}
