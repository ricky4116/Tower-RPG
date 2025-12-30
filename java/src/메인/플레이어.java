package 메인;

import 메인.직업.상급직업;
import 메인.직업.중급직업;
import 메인.직업.초급직업;
import 몬스터.일반몬스터;
import 소비.몬스터전리품;
import 소비.소비아이템;
import 소비.주문서;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public class 플레이어 {

    public String 몬스터상태;
    public int 공격력, 방어력, 현재체력, 회피, 레벨, 보유골드, 경험치, 레벨업필요경험치, 최대체력, 출혈데미지;
    public int 강한공격확률, 적당한공격확률, 약한공격확률,스킬선택,때리는방식=0;
    public int 스킬사용할때=0; //1이면 스킬사용
    public ArrayList<장비> 장착한장비 = new ArrayList<>();
    public ArrayList<소비아이템> 소비창 = new ArrayList<>();
    public ArrayList<주문서> 주문서함 = new ArrayList<>();
    public ArrayList<장비> 내장비 = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    Random 랜덤 = new Random();
    초급직업 직업= new 초급직업("무직",0,0);
    JFrame 스킬프레임 = new JFrame("스킬 사용");
    JPanel panel = new JPanel();
    public JLabel 타이틀 = new JLabel("사용 가능한 스킬");
    public JLabel 스킬1=new JLabel();
    public JLabel 스킬2=new JLabel();
    public JLabel 스킬3=new JLabel();
    public JLabel 스킬사용할때텍스트 = new JLabel(" ");
    public JLabel 스킬사용가능횟수 = new JLabel("스킬 사용가능횟수 : 0");
    public JLabel 스킬이름 = new JLabel();
    public JLabel 스킬사진 = new JLabel();

    플레이어(){

    }
    public void 기본스탯설정() {
        공격력 = 8;
        방어력 = 8;
        최대체력 = 200;
        현재체력 = 200;
        회피 = 2;
        레벨 = 25;
        보유골드 = 1000;
        경험치 = 0;
        레벨업필요경험치 = 60;
        출혈데미지 = 0;
        강한공격확률 = 20;
        적당한공격확률 = 50;
        약한공격확률 = 80;
    }

    public void 스킬사진선정(){
        if(스킬선택==1){
            던전.setImageLabelSize(스킬사진, "C:/Users/USER-PC/IdeaProjects/java/src/메인/스킬사진/회복스킬.png", 100, 125);
        }else if(직업.직업이름.contains("거너")){
            던전.setImageLabelSize(스킬사진, "C:/Users/USER-PC/IdeaProjects/java/src/메인/스킬사진/거너스킬.png", 100, 125);
        }else if(직업.직업이름.contains("검사")){
            던전.setImageLabelSize(스킬사진, "C:/Users/USER-PC/IdeaProjects/java/src/메인/스킬사진/검사스킬.png", 100, 125);
        }else if(직업.직업이름.contains("워해머")){
            던전.setImageLabelSize(스킬사진, "C:/Users/USER-PC/IdeaProjects/java/src/메인/스킬사진/워해머스킬.png", 100, 125);
        }
    }

    public synchronized void 스킬GUI생성() {

        스킬프레임.add(panel);
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

     if(스킬사용할때==0) {  //스킬 사용안할때 GUI
         SwingUtilities.invokeLater(() -> {
                     스킬사용가능횟수.setText("스킬 사용가능횟수 : " + 직업.스킬사용횟수);
                     타이틀.setText("사용 가능한 스킬");
                     스킬이름.setText("");
                     if (스킬사진 != null) {
                         스킬사진.setIcon(null);
                     }
                     스킬사용할때텍스트.setText("");
                    직업.스킬출력(this);
             layout.setHorizontalGroup(layout.createParallelGroup()
                     .addComponent(타이틀)
                     .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                             .addComponent(스킬1)
                             .addComponent(스킬2)
                             .addComponent(스킬3)
                             .addComponent(스킬사용가능횟수))
             );
             layout.setVerticalGroup(layout.createSequentialGroup()
                     .addComponent(타이틀)
                     .addComponent(스킬1)
                     .addComponent(스킬2)
                     .addComponent(스킬3)
                     .addComponent(스킬사용가능횟수)
             );
             스킬프레임.pack();   //자동크기설정
             스킬프레임.setVisible(true);
         });
    }else if(스킬사용할때==1){ //스킬 사용할때 GUI
         직업.스킬이름출력및사용텍스트(this,스킬선택);
         SwingUtilities.invokeLater(() -> {
                     타이틀.setText("");
                     스킬1.setText("");
                     스킬2.setText("");
                     스킬3.setText("");
                     스킬사용가능횟수.setText("");
         스킬사진선정();
             layout.setHorizontalGroup(layout.createParallelGroup()
                     .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                             .addComponent(스킬이름)
                             .addComponent(스킬사진)
                             .addComponent(스킬사용할때텍스트))
             );

             layout.setVerticalGroup(layout.createSequentialGroup()
                     .addComponent(스킬이름)
                     .addComponent(스킬사진)
                     .addComponent(스킬사용할때텍스트)
             );

             스킬프레임.pack();   //자동크기설정
             스킬프레임.setVisible(true);
         });
    }
    }

    public void 전직결정하기() {
        if (레벨 == 5) {
            일차전직();
        } else if (레벨 == 15) {
            이차전직();
        } else if (레벨 == 25) {
            최종전직();
        }
    }

    public void 일차전직() {  //일차전직 이전에는 무직, 일차 전직 후에 스킬과 모험가라는 명칭을 갖게 됨
        직업 = new 초급직업("초보 모험가", 4, 20);
        System.out.println(직업.직업이름 + "으로 전직하였습니다");
        직업.스킬출력(this);
    }

    public void 이차전직() {
        Scanner sc = new Scanner(System.in);
        System.out.println("어떤 직업을 선택하시겠습니까?");
        System.out.println("1.중급 거너");
        System.out.println("2.중급 검사");
        System.out.println("3.중급 워해머");
        int 전직선택 = sc.nextInt();
        if (전직선택 == 1) {
            직업 = new 중급직업("중급 거너", 5, 7);
        } else if (전직선택 == 2) {
            직업 = new 중급직업("중급 검사", 5, 7);
        } else {
            직업 = new 중급직업("중급 워해머", 5, 5);
        }
        System.out.println(직업.직업이름 + "으로 전직하였습니다");
        직업.스킬출력(this);
    }

    public void 최종전직() {
        Scanner sc = new Scanner(System.in);
        System.out.println("어떤 직업을 선택하시겠습니까?");
        System.out.println("1.상급 거너");
        System.out.println("2.상급 검사");
        System.out.println("3.상급 워해머");
        int 전직선택 = sc.nextInt();
        if (전직선택 == 1) {
            직업 = new 상급직업("상급 거너", 6, 15);
        } else if (전직선택 == 2) {
            직업 = new 상급직업("상급 검사", 6, 15);
        } else {
            직업 = new 상급직업("상급 워해머", 6, 7);
        }
        System.out.println(직업.직업이름 + "으로 전직하였습니다");
        직업.스킬출력(this);
    }

    public void 스킬사용(일반몬스터 몬스터) {
        System.out.println("어떤 스킬을 사용하시겠습니까?");
        if (현재체력 >0 && 몬스터.MonHp>0) {
            스킬선택 = sc.nextInt();
        }
        직업.스킬사용(this, 몬스터, 스킬선택);
        직업.스킬이름출력및사용텍스트(this,스킬선택);
        스킬사용할때=1;
    }

    public void 경험치측정() {
        if (레벨업필요경험치 <= 경험치) {
            경험치 = 경험치 - 레벨업필요경험치;
            레벨++;
            레벨업필요경험치 += 10;
            최대체력 += 10;
            현재체력 += 10;
            회피++;
            공격력++;
            방어력++;
            System.out.println("축하합니다 " + 레벨 + "레벨로 레벨업 하였습니다");
            System.out.println("공격력,방어력,회피가 1 증가하였습니다");
            System.out.println("체력이 10 증가하였습니다");
        }
    }

    public void 전투할때할수있는행동(일반몬스터 몬스터) {
        강한공격확률 = 20;
        적당한공격확률 = 50;
        약한공격확률 = 80;
        int 스킬쓰기이전공격력 = 공격력;
        while (몬스터.MonHp > 0 && 현재체력 > 0) {

                int 때리기이전체력 = 몬스터.MonHp;
                전투UI();
                System.out.println("무엇을 하시겠습니까?");
                때리는방식 = sc.nextInt();
                sc.nextLine();
                if (때리는방식 <= 3) {
                    if (때리는방식 == 1) {
                        강한공격하기(몬스터);
                    } else if (때리는방식 == 2) {
                        적당한공격하기(몬스터);
                    } else if (때리는방식 == 3) {
                        약한공격하기(몬스터);
                    }
                    if (때리기이전체력 == 몬스터.MonHp) {
                        System.out.println("공격에 실패하였습니다..");
                    } else {
                        System.out.println("공격에 성공하였습니다!!");
                    }
                } else if (때리는방식 == 4) {
                    if (레벨 < 5) {
                        System.out.println("아직은 스킬을 사용할 수 없습니다!!");
                    } else {
                        스킬사용(몬스터);
                        스킬사용할때=1;
                    }
                } else if (때리는방식 == 5) {
                    가방보기();
                }
            }
            공격력 = 스킬쓰기이전공격력;
        }




    public void 전투UI() {
        System.out.println("    **************    **************    **************");
        System.out.println("    *            *    *            *    *            *");
        System.out.println("    *            *    *            *    *            *");
        System.out.println("    *    "+강한공격확률+"%     *    *    "+적당한공격확률+"%     *    *    "+약한공격확률+"%     *");
        System.out.println("    *            *    *            *    *            *");
        System.out.println("    *            *    *            *    *            *");
        System.out.println("    **************    **************    **************");
        System.out.println("        "+공격력*2+"DMG            "+(int)(공격력*(1.5))+"DMG               "+공격력+"DMG");
        System.out.println("      1.강한공격         2.적당한공격         3.약한공격");
        System.out.println("      4.스킬을 사용한다   5.가방을 연다");
    }

    public synchronized void 강한공격하기(일반몬스터 몬스터) {
        int ran = 랜덤.nextInt(100) + 1;
        if (ran <= 강한공격확률) {
            몬스터.MonHp = 몬스터.MonHp - 공격력 * 2;
            몬스터상태 = "피해량 "+공격력 * 2;
        }
    }

    public synchronized void 적당한공격하기(일반몬스터 몬스터) {
        int ran = 랜덤.nextInt(100) + 1;
        if (ran <= 적당한공격확률) {
            몬스터.MonHp = 몬스터.MonHp - (int) (공격력 * 1.5);
            몬스터상태 = "피해량 "+(int) (공격력 * 1.5);
        }
    }

    public synchronized void 약한공격하기(일반몬스터 몬스터) {
        int ran = 랜덤.nextInt(100) + 1;
        if (ran <= 약한공격확률) {
            몬스터.MonHp = 몬스터.MonHp - 공격력;
            몬스터상태 = "피해량 "+공격력;
        }
    }


    public synchronized void 가방보기() {
        Scanner 플레이어선택 = new Scanner(System.in);
        while (true) {
            System.out.println("어떤 것을 보시겠습니까?");
            System.out.println("1. 내가 장착한 장비");
            System.out.println("2. 전체 장비");
            System.out.println("3. 소비아이템");
            System.out.println("4. 나가기");
            int 선택지 = 플레이어선택.nextInt();
            if (선택지 == 1) {
                장착한장비보기();
            } else if (선택지 == 2) {
                장착한장비보기();
                장비보기();
                장비장착();
            } else if (선택지 == 3) {
                소비아이템사용과정UI();
            } else if (선택지 == 4) {
                break;
            } else {
                System.out.println("다시 입력하세요");
            }
        }
    }

    ArrayList<장비> 임시장비 =new ArrayList<>();
    public void 장착한장비보기() {
        임시장비.addAll(장착한장비);
        System.out.println("내가 장착한 장비");
        System.out.println("_____________________________________________");
        while(!임시장비.isEmpty()) {
            for (int i = 0; i < 임시장비.size(); i++) {
                if (임시장비.get(i).장비종류.equals("검") || 임시장비.get(i).장비종류.equals("총") || 임시장비.get(i).장비종류.equals("둔기")) {
                        장비출력(임시장비.get(i));
                        임시장비.remove(임시장비.get(i));
                        System.out.println(); i=-1;
                } else if (임시장비.get(i).장비종류.equals("장갑")) {
                        장비출력(임시장비.get(i));
                        임시장비.remove(임시장비.get(i));
                        System.out.println();i=-1;
                } else if (임시장비.get(i).장비종류.equals("갑옷")) {
                        장비출력(임시장비.get(i));
                        임시장비.remove(임시장비.get(i));
                        System.out.println();i=-1;
                } else if (임시장비.get(i).장비종류.equals("신발")) {
                        장비출력(임시장비.get(i));
                        임시장비.remove(임시장비.get(i));
                        System.out.println();i=-1;
                }
            }
        }
        System.out.println("_____________________________________________");
        System.out.println();
    }


    public void 장비보기() {
        System.out.println("내가 가지고 있는 장비");
        System.out.println("_____________________________________________");
        for (int i = 0; i < 내장비.size(); i++) {
            System.out.print((i + 1) + ". ");
            장비출력(내장비.get(i));
            System.out.println(" " + 내장비.get(i).골드측정 + "G");
        }
        System.out.println("_____________________________________________");
        System.out.println();
    }

    public void 장비출력(장비 장비출력하기) {
        System.out.print(장비출력하기.희귀도표현 + " " + 장비출력하기.내구도표현 + " "
                + 장비출력하기.장비종류 + " " + 장비출력하기.희귀도능력치표현
                + " +" + 장비출력하기.희귀도 + " 내구도 " + 장비출력하기.내구도 + "  속성-" + 장비출력하기.속성);
    }

    public void 장비장착매커니즘(장비 입을장비) {
        임시장비.clear();
        if (입을장비.장비종류.equals("갑옷")) {
            for (장비 장비 : 장착한장비) {
                if (장비.장비종류.equals("갑옷")) {
                    내장비.add(장비);
                    임시장비.add(장비);
                }
            }
        } else if (입을장비.장비종류.equals("장갑")) {
            for (장비 장비 : 장착한장비) {
                if (장비.장비종류.equals("장갑")) {
                    내장비.add(장비);
                    임시장비.add(장비);
                }
            }
        } else if (입을장비.장비종류.equals("신발")) {
            for (장비 장비 : 장착한장비) {
                if (장비.장비종류.equals("신발")) {
                    내장비.add(장비);
                    임시장비.add(장비);
                }
            }
        } else {
            for (장비 장비 : 장착한장비) {
                if (장비.장비종류.equals("검") || 장비.장비종류.equals("총") || 장비.장비종류.equals("둔기")) {
                    내장비.add(장비);
                    임시장비.add(장비);
                }
            }
        }
        장착한장비.removeAll(임시장비);
        장착한장비.add(입을장비);
        내장비.remove(입을장비);
        임시장비.clear();
    }

    public void 장비장착() {
        while (true) {
            System.out.println("어떤 장비를 장착하시겠습니까?");
            System.out.println("0을 입력하시면 뒤로갑니다");
            int 장비장착선택 = sc.nextInt();
            if (장비장착선택 == 0) {
                break;
            } else {
                장비장착매커니즘(내장비.get(장비장착선택 - 1));
                장착한장비보기();
                장비보기();
                System.out.println("장비를 장착하였습니다!");
            }
        }
    }


    public void 장비적용() {
        for (메인.장비 장비 : 장착한장비) {
            if (장비.장비종류.equals("검") || 장비.장비종류.equals("총") || 장비.장비종류.equals("둔기")) {
                공격력 += 장비.희귀도;
            } else if (장비.장비종류.equals("갑옷")) {
                방어력 += 장비.희귀도;
            } else if (장비.장비종류.equals("신발") || 장비.장비종류.equals("장갑")) {
                회피 += 장비.희귀도;
            }
        }
    }


    public void 물약구매하기(소비아이템 구매할물약,int 구매할물약개수){
        if(보유골드-(구매할물약.소비아이템가격*구매할물약개수)<0){System.out.println("물약을 구매할 수 없습니다");}
        else {
            int 존재여부 = 0;
            int 아이템위치 = 0;
            for(int i=0;i<소비창.size();i++){
                if(소비창.get(i).등급.contains(구매할물약.등급) && 소비창.get(i).소비아이템이름.contains(구매할물약.소비아이템이름)){
                    존재여부 = 1;
                    아이템위치 = i;
                    break;
                }
            }
            if (존재여부 == 1) {
                소비창.get(아이템위치).소비아이템개수 += 구매할물약개수;
            } else {
                구매할물약.소비아이템개수 += 구매할물약개수;
                소비창.add(구매할물약);
            }
            보유골드-=구매할물약.소비아이템가격*구매할물약개수;
            System.out.println("******물약 구매를 완료하였습니다******");
            System.out.println();
        }
    }

    public void 주문서구매하기(주문서 구매할주문서,int 구매할주문서개수){  // 주문서함 Arraylist에 주문서를 추가
        if(보유골드-(구매할주문서.소비아이템가격*구매할주문서개수)<0){System.out.println("물약을 구매할 수 없습니다");}
        else {
            int 존재여부 = 0;
            int 아이템위치 = 0;
            for(int i=0;i<주문서함.size();i++){
                if(주문서함.get(i).등급.contains(구매할주문서.등급) && 주문서함.get(i).소비아이템이름.contains(구매할주문서.소비아이템이름)){
                    존재여부 = 1;
                    아이템위치 = i;
                    break;
                }
            }
            if (존재여부 == 1) {
                주문서함.get(아이템위치).소비아이템개수 += 구매할주문서개수;
            } else {
                구매할주문서.소비아이템개수 += 구매할주문서개수;
                주문서함.add(구매할주문서);
            }
            보유골드-=구매할주문서.소비아이템가격*구매할주문서개수;
            System.out.println("******주문서 구매를 완료하였습니다******");
            System.out.println();
        }
    }

    public void 전리품얻기(몬스터전리품 전리품){
        System.out.println(전리품.소비아이템이름+"을 1개 얻었습니다");
        System.out.println();
        int 존재여부=0;
        int 아이템위치=0;
        for(int i=0;i<소비창.size();i++){
            if(소비창.get(i).소비아이템이름.contains(전리품.소비아이템이름)){
                존재여부=1;
                아이템위치=i;
                break;
            }
        }
        if(존재여부==1) {
            소비창.get(아이템위치).소비아이템개수++;
        }
        else {
                소비창.add(new 몬스터전리품("","<전리품> "+전리품.소비아이템이름,전리품.전리품능력,전리품.전리품능력치, 전리품.소비아이템개수, 전리품.소비아이템가격));
            }
        }


    public void 장비구매하기(장비 구매한장비){
        if(보유골드-구매한장비.골드측정<0) {System.out.println("장비를 구매할 수 없습니다");}
        else {
            내장비.add(구매한장비);
            보유골드-=구매한장비.골드측정;
            System.out.println("******장비구매를 완료하였습니다******");
            System.out.println();}
    }

    public void 소비아이템보기() {
        System.out.println("내가 가지고 있는 소비아이템                  " + 보유골드 + "G");
        System.out.println("_____________________________________________");
        for (int i = 소비창.size() - 1; i >= 0; i--) {  //아이템의 개수가 0이면 지우기
            if (소비창.get(i).소비아이템개수 == 0) {
                소비창.remove(i);
            }
        }
        for (소비.소비아이템 소비아이템 : 소비창) {
            if (소비아이템.소비아이템개수 != 0) {
                if (소비아이템.등급.equals("전리품")) {
                    몬스터전리품 몬스터전리품 = (몬스터전리품) 소비아이템;
                    몬스터전리품.소비아이템출력();
                } else {
                    소비아이템.소비아이템출력();
                }
                System.out.println(" " + 소비아이템.소비아이템개수 + "개 ");
            }
        }
        for (소비.주문서 주문서 : 주문서함) {
            주문서.소비아이템출력();
            System.out.println(" " + 주문서.소비아이템개수 + "개 ");
        }
        System.out.println("_____________________________________________");

    }


    public void 소비아이템사용과정UI() {
        소비아이템보기();
        Scanner sc = new Scanner(System.in);
        System.out.println("소비아이템을 사용하시겠습니까?");
        System.out.println("1.네              2.아니요");
        int 소비사용 = sc.nextInt();
        if (소비사용 == 1) {
            System.out.println("어떤 소비아이템을 사용하시겠습니까?");
            int 어떤아이템사용 = sc.nextInt();
            while (어떤아이템사용 == 0 || 어떤아이템사용 > 소비창.size()) {
                System.out.println("다시 입력하세요");
                어떤아이템사용 = sc.nextInt();
            }if (소비창.get(어떤아이템사용 - 1).소비아이템이름.contains("물약")) {
                물약사용(소비창.get(어떤아이템사용-1));
            }else {
                System.out.println("사용할 수 없는 소비아이템입니다");
            }
        }
    }

    public void 물약사용(소비아이템 사용할물약){
        if(사용할물약.소비아이템능력.contains("공격력")){
            공격력+=사용할물약.소비아이템능력치;
          //  System.out.println(사용할물약.등급+" 공격력의 물약을 사용하였습니다");
        }else if(사용할물약.소비아이템능력.contains("방어력")){
            방어력+=사용할물약.소비아이템능력치;
          //  System.out.println(사용할물약.등급+" 방어력의 물약을 사용하였습니다");
        }else if(사용할물약.소비아이템능력.contains("회피")){
            회피+=사용할물약.소비아이템능력치;
           // System.out.println(사용할물약.등급+" 회피의 물약을 사용하였습니다");
        }else{

            //물약의 능력치 1/4만큼 2초 간격으로 회복
            Thread 물약사용 = new Thread(() -> {
                int 물약나누는횟수=0;
                if(현재체력+(사용할물약.소비아이템능력치)>최대체력){
                    try {
                        while (현재체력+(사용할물약.소비아이템능력치)<최대체력) {
                            if(현재체력+(사용할물약.소비아이템능력치/4)>최대체력){
                               현재체력=최대체력;
                            }else {
                                현재체력 += 사용할물약.소비아이템능력치 / 4;
                            }
                            물약나누는횟수++;
                            if (물약나누는횟수==4){
                                break;
                            }
                            Thread.sleep(2000);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    try {
                        while (현재체력 < 현재체력 + (사용할물약.소비아이템능력치)) {
                            현재체력+=사용할물약.소비아이템능력치/4;
                            Thread.sleep(2000);
                            물약나누는횟수++;
                            if (물약나누는횟수==4){
                                break;
                            }
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
               // System.out.println(사용할물약.등급+" 체력의 물약을 사용하였습니다");
            });
            물약사용.start();


        }
        사용할물약.소비아이템개수--;
    }



    public void 플레이어스탯() {
        System.out.println("직업 : " + 직업.직업이름);
        System.out.println("레벨 : " + 레벨 + "                 경험치 : " + 경험치 + "/" + 레벨업필요경험치);
        System.out.println("체력 : " + 현재체력 + "/" + 최대체력);
        System.out.println("공격력 : " + 공격력);
        System.out.println("방어력 : " + 방어력);
        System.out.println("회피 : " + 회피);
        System.out.println("보유골드 : " + 보유골드);
        System.out.println();
    }


}