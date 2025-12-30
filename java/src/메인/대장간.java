package 메인;


import 소비.주문서;

import java.util.Random;
import java.util.Scanner;

public class 대장간 {

    public int 장비선택,주문서선택,주문서존재;
    public String 주문서능력;
    Scanner sc = new Scanner(System.in);


    public void 대장간입장(플레이어 플레이어){
        System.out.println("대장간에 입장하였습니다");
        System.out.println("대장간에서는 장비에 주문서를 부여할 수 있습니다");
        while(true) {
            System.out.println("어떤 장비에 주문서를 부여하시겠습니까?");
            System.out.println("1.장착한 장비");
            System.out.println("2.장착하지 않은 장비");
            System.out.println("3.나간다");
            int 강화할장비선택=sc.nextInt();
            if(강화할장비선택==1){
                System.out.println("내가 장착한 장비");
                System.out.println("_____________________________________________");
                for(int i=0;i<플레이어.장착한장비.size();i++){
                    System.out.print((i+1)+". ");
                   플레이어.장비출력(플레이어.장착한장비.get(i));
                   System.out.println();
                }
                System.out.println("_____________________________________________");
            }else if(강화할장비선택==2){
                플레이어.장비보기();
            }else {
                break;
            }
            System.out.println("몇 번째 장비를 강화하시겠습니까?");
            System.out.println("강화하지 않고 나가려면 0을 입력하세요");
            장비선택=sc.nextInt();
            if(장비선택==0){
                break;
            }else {
                if(강화할장비선택==1){
                    사용할수있는주문서출력(플레이어,플레이어.장착한장비.get(장비선택-1));
                    if(주문서존재==0){
                        System.out.println("부여할 수 있는 주문서가 없습니다");
                        break;
                    }
                    주문서선택 = sc.nextInt();
                    int num=1;
                    for (int i = 0; i < 플레이어.주문서함.size(); i++) {
                        if (플레이어.주문서함.get(i).소비아이템이름.contains(주문서능력) && 플레이어.주문서함.get(i).소비아이템이름.contains("주문서")) {
                            if (num == 주문서선택) {
                                주문서로장비강화하기(플레이어, 플레이어.주문서함.get(i),플레이어.장착한장비.get(장비선택-1));
                            } else {
                                num++;
                            }
                        }
                    }
                }else{
                    사용할수있는주문서출력(플레이어,플레이어.내장비.get(장비선택-1));
                    if(주문서존재==0){
                        System.out.println("부여할 수 있는 주문서가 없습니다");
                        break;
                    }
                    주문서선택 = sc.nextInt();
                    int num=1;
                    for (int i = 0; i < 플레이어.주문서함.size(); i++) {
                        if (플레이어.주문서함.get(i).소비아이템이름.contains(주문서능력) && 플레이어.주문서함.get(i).소비아이템이름.contains("주문서")) {
                            if (num == 주문서선택) {
                                주문서로장비강화하기(플레이어, 플레이어.주문서함.get(i),플레이어.내장비.get(장비선택-1));
                            } else {
                                num++;
                            }
                        }
                    }
                }
            }
        }
    }

    public void 주문서로장비강화하기(플레이어 플레이어,주문서 사용할주문서, 장비 장비) {
        Random ran = new Random();
        int 랜덤한값 = ran.nextInt(100) + 1;
        if (플레이어.보유골드 < 사용할주문서.강화비용) {
            System.out.println("보유골드가 부족하여 강화할 수 없습니다");
        }else if(사용할주문서.소비아이템개수==0) {
            System.out.println("강화할 수 있는 주문서가 없습니다");
        } else {
            if (랜덤한값 < 사용할주문서.강화확률) {
               if (사용할주문서.소비아이템이름.contains("공격력")) {
                    장비.희귀도 += 사용할주문서.소비아이템능력치;
                } else if (사용할주문서.소비아이템이름.contains("방어력")) {
                    장비.희귀도 += 사용할주문서.소비아이템능력치;
                } else {
                    장비.희귀도 += 사용할주문서.소비아이템능력치;
                }
                System.out.println("********강화에 성공하였습니다********");
                System.out.println("  강화한 장비의 능력치가 상승했습니다");
            } else {
                System.out.println("********강화에 실패하였습니다********");
            }
            사용할주문서.소비아이템개수--;
            플레이어.보유골드 -= 사용할주문서.강화비용;
        }
    }


    public void 사용할수있는주문서출력(플레이어 플레이어인벤,장비 강화할장비) {  //주문서를 출력할때 다운캐스팅을 통해서 등급변수도 같이 출력
        주문서존재 = 0;
        int num = 1;
        if (강화할장비.장비종류.equals("검") || 강화할장비.장비종류.equals("둔기") || 강화할장비.장비종류.equals("총")) {
            주문서능력="공격력";
            for (int i = 0; i < 플레이어인벤.주문서함.size(); i++) {
                    if (플레이어인벤.주문서함.get(i).소비아이템이름.contains("공격력")) {
                        if(플레이어인벤.주문서함.get(i).소비아이템개수!=0) {
                            System.out.print(num+". ");
                            플레이어인벤.주문서함.get(i).소비아이템출력();
                            System.out.println(" "+플레이어인벤.주문서함.get(i).소비아이템개수 +"개");
                            주문서존재++;num++;
                        }
                    }
                }
            } else if (강화할장비.장비종류.equals("갑옷")) {
            주문서능력="방어력";
                for (int i = 0; i < 플레이어인벤.주문서함.size(); i++) {
                    if (플레이어인벤.주문서함.get(i).소비아이템이름.contains("방어력")) {
                        if(플레이어인벤.주문서함.get(i).소비아이템개수!=0) {
                            System.out.println(num+". ");
                            플레이어인벤.주문서함.get(i).소비아이템출력();
                            System.out.println(" "+플레이어인벤.주문서함.get(i).소비아이템개수 +"개");
                            주문서존재++;num++;
                        }
                    }
                }
            } else {
            주문서능력="회피";
                for (int i = 0; i < 플레이어인벤.소비창.size(); i++) {
                    if (플레이어인벤.소비창.get(i).소비아이템이름.contains("회피")) {
                        if(플레이어인벤.주문서함.get(i).소비아이템개수!=0) {
                            System.out.println(num+". ");
                            플레이어인벤.주문서함.get(i).소비아이템출력();
                            System.out.println(" "+플레이어인벤.주문서함.get(i).소비아이템개수 +"개");
                            주문서존재++; num++;
                        }
                    }
                }
            }
            if(주문서존재!=0) {
                System.out.println();
                System.out.println("강화비용 - 초급 : 50G  중급 : 100G  고급 : 150G");
                System.out.println("어떤 주문서를 사용하시겠습니까?");
                System.out.println("나가려면 0을 입력하세요");
            }
    }

}

