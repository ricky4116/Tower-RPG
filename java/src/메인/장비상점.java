package 메인;

import java.util.ArrayList;
import java.util.Scanner;

public class 장비상점 {

    Scanner 물품선택 = new Scanner(System.in);
    ArrayList<장비> 무기 = new ArrayList<>();
    ArrayList<장비> 방어구 = new ArrayList<>();

    public void 장비상점입장(플레이어 플레이어){
       while(true) {
            System.out.println("상점에 입장하였습니다.");
            System.out.println("1. 장비를 산다");
            System.out.println("2. 장비를 판매한다");
            System.out.println("3. 상점에서 나간다");
            int choose = 물품선택.nextInt();
            if(choose==1){
                if(플레이어.보유골드<150){ System.out.println("최소한의 금액이 부족하여 장비를 구매할 수 없습니다");}
                else {장비구매(플레이어);}
            } else if(choose==2){
                if(플레이어.내장비.isEmpty()){System.out.println("장비가 아무것도 없어서 구매할 수 없습니다.");}
                else{장비판매(플레이어);}
            }else {
                break;
            }
        }
    }

    public void 상점에서보여질무기설정(){
        무기.clear();
        for(int i=0;i<4;i++){
            무기.add(new 장비("무기"));
        }
    }

    public void 상점에서보여질방어구설정(){
        방어구.clear();
        for(int i=0;i<4;i++){
            방어구.add(new 장비("방어구"));
        }
    }

    public void 장비출력(장비 구매한장비){
        System.out.println( 구매한장비.희귀도표현 +" "+ 구매한장비.내구도표현 + " " + 구매한장비.장비종류
                + " " + 구매한장비.희귀도능력치표현 + " +" + 구매한장비.희귀도 + " 내구도 " + 구매한장비.내구도 +"  속성-" +구매한장비.속성+"  "+ 구매한장비.골드측정 + "G");
    }

    public void 장비구매(플레이어 구매자){
        System.out.println("어떤 것을 구매하시겠습니까?");
        System.out.println("1.무기             2.방어구");
        int 구매할장비= 물품선택.nextInt();
        while(true) {
            System.out.println("어떤 무기를 구매하시겠습니까?                 "+구매자.보유골드+"G");
            if(구매할장비==1){
                상점에서보여질무기설정();
                for(int i=0;i<무기.size();i++){
                    System.out.print((i+1)+". ");
                    장비출력(무기.get(i));
                }
            }else{
                상점에서보여질방어구설정();
                for(int i=0;i<방어구.size();i++){
                    System.out.print((i+1)+". ");
                    장비출력(방어구.get(i));
                }
            }
            System.out.println("5. 50G를 지불하고 새로고침한다       6. 뒤로가기");
            int 장비선택= 물품선택.nextInt();
            if(장비선택==5){
                구매자.보유골드-=50;
            }else if(장비선택==6){
                break;
            }else {
                if(구매할장비==1){
                    구매자.장비구매하기(무기.get(장비선택-1));
                }else {
                    구매자.장비구매하기(방어구.get(장비선택-1));
                }
            }

        }
    }



    public void 장비판매(플레이어 판매자){
        while(true) {
            System.out.println("어떤 장비를 판매하시겠습니까?                " + 판매자.보유골드 + "G");
            판매자.장비보기();
            System.out.println("0을 입력하면 판매를 종료합니다");
            int 장비판매선택=물품선택.nextInt();
            if(장비판매선택==0) {break;}
            else{
                판매자.보유골드+=판매자.내장비.get(장비판매선택-1).골드측정;
                판매자.내장비.remove(장비판매선택-1);
                System.out.println("******장비판매를 완료하였습니다******");
                System.out.println();
            }
        }
    }

}
