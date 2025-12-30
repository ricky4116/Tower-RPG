package 메인;

import 소비.물약;
import 소비.주문서;

import java.util.ArrayList;
import java.util.Scanner;

public class 소비상점 {

    ArrayList<물약> 물약 = new ArrayList<>();
    ArrayList<주문서> 주문서 = new ArrayList<>();

    Scanner 구매소비아이템 = new Scanner(System.in);

    public void 소비상점입장(플레이어 플레이어) {
        while (true) {
            System.out.println("소비상점에 들어왔습니다");
            System.out.println("1. 물약을 산다");
            System.out.println("2. 주문서를 산다");
            System.out.println("3. 소비아이템을 판다");
            System.out.println("4. 나간다");
            int 소비아이템 = 구매소비아이템.nextInt();
            if (소비아이템 == 1) {
                if (플레이어.보유골드 < 50) {
                    System.out.println("최소한의 금액이 부족하여 소비아이템을 구매할 수 없습니다!!");
                } else {
                    물약구매(플레이어);
                }
            } else if (소비아이템 == 2) {
                주문서구매(플레이어);
            } else if (소비아이템 == 3) {
                소비아이템판매(플레이어);
            } else {
                break;
            }
        }
    }

    public void 상점에서보여질물약설정(){
        물약.clear();
        if(던전.floor<10){
            물약.add(new 물약("초급","체력의 물약", "체력", 20, 0, 60));
            물약.add(new 물약("초급","회피의 물약", "회피", 5, 0, 60));
            물약.add(new 물약("초급","방어력의 물약", "방어력", 5, 0, 60));
            물약.add(new 물약("초급","공격력의 물약", "공격력", 5, 0, 100));
        }else if(던전.floor<15){
            물약.add(new 물약("중급","체력의 물약", "체력", 40, 0, 110));
            물약.add(new 물약("중급","회피의 물약", "회피", 7, 0, 110));
            물약.add(new 물약("중급","방어력의 물약", "방어력", 7, 0, 110));
            물약.add(new 물약("중급","공격력의 물약", "공격력", 7, 0, 150));
        }else {
            물약.add(new 물약("고급","체력의 물약", "체력", 60, 0, 160));
            물약.add(new 물약("고급","회피의 물약", "회피", 9, 0, 160));
            물약.add(new 물약("고급","방어력의 물약", "방어력", 9, 0, 160));
            물약.add(new 물약("고급","공격력의 물약", "공격력", 9, 0, 200));
        }
    }

    public void 물약구매(플레이어 구매자) {
        상점에서보여질물약설정();
        while (true) {
            System.out.println("어떤 물약을 사시겠습니까?                보유골드 : " + 구매자.보유골드 + "G");
            for(int i=0;i<물약.size();i++){
                System.out.print((i+1)+". ");
                물약.get(i).소비아이템출력();
                System.out.println();
            }
            System.out.println("5. 뒤로가기");
            int 구매할물약 = 구매소비아이템.nextInt();
            if (구매할물약 == 5) {
                break;
            } else {
                System.out.println("물약을 몇 개 구매하시겠습니까?");
                int 구매할물약개수 = 구매소비아이템.nextInt();
                구매자.물약구매하기(물약.get(구매할물약-1),구매할물약개수);
            }
        }
    }


    public void 상점에서보여질주문서설정(){
        주문서.clear();
        if(던전.floor<10){
            주문서.add(new 주문서("초급","공격력의 주문서", "공격력", 3, 0, 150));
            주문서.add(new 주문서("초급", "방어력의 주문서","방어력", 3, 0, 100));
            주문서.add(new 주문서("초급", "회피의 주문서","회피", 3, 0, 100));
        }else if(던전.floor<15){
            주문서.add(new 주문서("중급","공격력의 주문서", "공격력", 4, 0, 200));
            주문서.add(new 주문서("중급", "방어력의 주문서","방어력", 4, 0, 150));
            주문서.add(new 주문서("중급", "회피의 주문서","회피", 4, 0, 150));
        }else {
            주문서.add(new 주문서("고급","공격력의 주문서", "공격력", 5, 0, 200));
            주문서.add(new 주문서("고급", "방어력의 주문서","방어력", 5, 0, 150));
            주문서.add(new 주문서("고급", "회피의 주문서","회피", 5, 0, 150));
        }
    }

    public void 주문서구매(플레이어 구매자) {
        while (true) {
            상점에서보여질주문서설정();
            System.out.println("어떤 주문서를 사시겠습니까?                보유골드 : " + 구매자.보유골드 + "G");
            for(int i=0;i<주문서.size();i++){
                System.out.print((i+1)+". ");
                주문서.get(i).소비아이템출력();
                System.out.println();
            }
            System.out.println("4. 뒤로가기");
            int 구매할주문서 = 구매소비아이템.nextInt();
            if(구매할주문서==4) {
                break;
            }else {
                System.out.println("주문서을 몇 개 구매하시겠습니까?");
                int 구매할주문서개수 = 구매소비아이템.nextInt();
                구매자.주문서구매하기(주문서.get(구매할주문서-1),구매할주문서개수);
            }
        }
    }



    public void 소비아이템판매(플레이어 판매자) {
        while (true) {
            for (int i = 판매자.소비창.size() - 1; i >= 0; i--) {  //아이템의 개수가 0이면 지우기
                if (판매자.소비창.get(i).소비아이템개수 == 0) {
                    판매자.소비창.remove(i);
                }
            }
            for (int i = 판매자.주문서함.size() - 1; i >= 0; i--) {  //아이템의 개수가 0이면 지우기
                if (판매자.주문서함.get(i).소비아이템개수 == 0) {
                    판매자.주문서함.remove(i);
                }
            }
            판매자.소비아이템보기();
            System.out.println("몇 번째 소비아이템을 파시겠습니까?");
            System.out.println("0을 입력하면 판매를 종료합니다");
            int 소비판매선택지 = 구매소비아이템.nextInt();
            if (소비판매선택지 == 0 ) {
                break;
            }else if(판매자.소비창.size()<소비판매선택지){ // 주문서 판매
                System.out.println("아이템을 몇 개 파시겠습니까?");
                int 아이템파는개수 = 구매소비아이템.nextInt();
                if (판매자.주문서함.get(소비판매선택지 - (판매자.소비창.size()+1)).소비아이템개수 - 아이템파는개수 < 0) {
                    System.out.println("아이템이 부족해서 그만큼 판매할 수 없습니다!!");
                } else {
                    System.out.println(판매자.주문서함.get(소비판매선택지 -(판매자.소비창.size()+1)).소비아이템이름 + "을 판매하였습니다!!");
                    판매자.보유골드 += 아이템파는개수 * (판매자.주문서함.get(소비판매선택지 - (판매자.소비창.size()+1)).소비아이템가격);
                    판매자.주문서함.get(소비판매선택지 - (판매자.소비창.size()+1)).소비아이템개수 -= 아이템파는개수;
                }
            } else {     //나머지 판매
                System.out.println("아이템을 몇 개 파시겠습니까?");
                int 아이템파는개수 = 구매소비아이템.nextInt();
                if (판매자.소비창.get(소비판매선택지 - 1).소비아이템개수 - 아이템파는개수 < 0) {
                    System.out.println("아이템이 부족해서 그만큼 판매할 수 없습니다!!");
                } else {
                    System.out.println(판매자.소비창.get(소비판매선택지 - 1).소비아이템이름 + "을 판매하였습니다!!");
                    판매자.보유골드 += 아이템파는개수 * (판매자.소비창.get(소비판매선택지 - 1).소비아이템가격);
                    판매자.소비창.get(소비판매선택지 - 1).소비아이템개수 -= 아이템파는개수;
                }
            }
        }
    }
}
