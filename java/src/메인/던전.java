package 메인;

import 몬스터.보스몬스터;
import 몬스터.엘리트몬스터;
import 몬스터.일반몬스터;
import 소비.몬스터전리품;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

public class 던전 {
    public static int floor = 9; //현재 층을 나타내는 변수
    public int inum = 0, jnum = 0;
    public int sector;
    public String 속성;
    JFrame 전투프레임 = new JFrame("전투");
    JPanel panel = new JPanel();
    String 몬스터상태가져오기 = null;
    String 플레이어상태가져오기 =null;

    JLabel 직업사진 = new JLabel();
    JLabel 몬스터사진 = new JLabel();
    JLabel 플레이어직업 = new JLabel();
    JLabel 몬스터이름 = new JLabel();
    JLabel VS텍스트 = new JLabel();
    JLabel 전투 = new JLabel();
    JLabel 플레이어체력 = new JLabel();
    JLabel 몬스터체력 = new JLabel();
    JLabel 플레이어공격력 = new JLabel();
    JLabel 몬스터공격력 = new JLabel();
    JLabel 플레이어방어력 = new JLabel();
    JLabel 플레이어상태 =new JLabel("플레이어상태");
    JLabel 몬스터상태=new JLabel("몬스터상태");
    일반몬스터 일반몬스터 = new 일반몬스터();
    엘리트몬스터 엘리트몬스터 = new 엘리트몬스터();
    보스몬스터 보스몬스터 = new 보스몬스터();
    속성 속성생성 = new 속성();
    몬스터전리품 전리품 = new 몬스터전리품();

    public void 던전입장(플레이어 플레이어) {


        이벤트 event = new 이벤트();
        int[] 둘중선택 = new int[15];
        Random rd = new Random();
        int k;
        속성 = 속성생성.get속성();
        System.out.println(속성 + " 던전에 입장하였습니다");
        System.out.println("현재 층수 : " + floor + "층");
        몇개섹터인지정하기();
        for (int i = 0; i < sector; i++) {
            k = rd.nextInt(sector / 2) + 1; //이벤트와 몬스터중에 랜덤해서 선택

            if (k == 1) {
                if (inum >= ((sector / 2) + 1)) {
                    jnum++;
                    k = 2;
                } else {
                    inum++;
                }
            } else {
                jnum++;
            }
            둘중선택[i] = k;
        }
        속성생성.속성비교(플레이어, this);
        for (int i = 0; i < sector; i++) {
            if (둘중선택[i] == 1) {
                System.out.println();
                System.out.println("이벤트가 발생했습니다!");
                event.이벤트선택(플레이어);
            } else {
                몬스터등장();
                System.out.println(일반몬스터.몬스터이름 + "이(가) 등장하였습니다!!");
                플레이어vs몬스터(플레이어, 일반몬스터);
                플레이어.전리품얻기(전리품);
                플레이어.스킬사용할때=0;
                플레이어.출혈데미지=0;
                플레이어.몬스터상태=null;
                if (플레이어.레벨 >= 5) {
                    플레이어.직업.스킬리셋();
                }
            }
            던전한섹터가끝나면나오는UI(플레이어);
        }
        if (floor % 5 == 0) {
            System.out.println(보스몬스터.몬스터이름 + "이(가) 등장하였습니다!!");
            보스몬스터.플레이어에게안좋은효과(플레이어);
            플레이어vs몬스터(플레이어, 보스몬스터);
        } else {
            System.out.println(엘리트몬스터.몬스터이름 + "이(가) 등장하였습니다!!");
            엘리트몬스터.플레이어에게안좋은효과(플레이어);
            플레이어vs몬스터(플레이어, 엘리트몬스터);
        }
        플레이어.스킬사용할때=0;
        플레이어.출혈데미지=0;
        플레이어.몬스터상태=null;
        플레이어.직업.스킬리셋();
        System.out.println(floor + "층을 클리어했습니다");
        floor++;
        플레이어.장비적용();
        플레이어.현재체력 = 플레이어.최대체력;
    }


    public void 던전한섹터가끝나면나오는UI(플레이어 플레이어) {
        Scanner 섹터끝선택 = new Scanner(System.in);
        while (true) {
            System.out.println("어떤 행동을 취하시겠습니까?");
            System.out.println("1. 계속 전진한다");
            System.out.println("2. 내 스탯을 확인한다");
            System.out.println("3. 인벤토리에 들어간다");
            int 선택지 = 섹터끝선택.nextInt();
            if (선택지 == 2) {
                플레이어.플레이어스탯();
            } else if (선택지 == 3) {
                플레이어.가방보기();
            } else if (선택지 == 1) {
                break;
            } else {
                System.out.println("다시 입력하세요");
            }
        }

    }

    public void 몇개섹터인지정하기() {
        if (floor <= 5) {   //sector가 몇개인지 정하는 조건문
            sector = 5;
        } else if (floor <= 10) {
            sector = 10;
        } else {
            sector = 15;
        }
    }

    public void 몬스터등장() {
        if (던전.floor <= 5) {
            일반몬스터 = new 일반몬스터("슬라임", 35, 8, 30);
            setImageLabelSize(몬스터사진, "C:/Users/USER-PC/IdeaProjects/java/src/몬스터/슬라임.jpg", 100, 125);
        } else if (던전.floor <= 10) {
            일반몬스터 = new 일반몬스터("고블린", 70, 10, 50);
            setImageLabelSize(몬스터사진, "C:/Users/USER-PC/IdeaProjects/java/src/몬스터/고블린.jpeg", 100, 125);
        } else if (던전.floor <= 15) {
            일반몬스터 = new 일반몬스터("드워프", 105, 12, 80);
            setImageLabelSize(몬스터사진, "C:/Users/USER-PC/IdeaProjects/java/src/몬스터/드워프.jpeg", 100, 125);
        } else {
            일반몬스터 = new 일반몬스터("검은사제들", 140, 14, 100);
            setImageLabelSize(몬스터사진, "C:/Users/USER-PC/IdeaProjects/java/src/몬스터/검은사제들.jpg", 100, 125);
        }
        엘리트몬스터 = new 엘리트몬스터("엘리트 " + 일반몬스터.몬스터이름, 일반몬스터.MonHp + 20, 일반몬스터.MonHit + 2, 일반몬스터.경험치 + 10);
        보스몬스터 = new 보스몬스터("보스 " + 엘리트몬스터.몬스터이름, 엘리트몬스터.MonHp + 20, 엘리트몬스터.MonHit + 2, 엘리트몬스터.경험치 + 10);
        전리품 = new 몬스터전리품("전리품", 일반몬스터.몬스터이름 + "의 정수", "없음", 0, 1, 50 * (floor / 5) + 50);
    }


    public void 직업사진설정(플레이어 플레이어){
        if(플레이어.직업.직업이름.contains("거너")){
            setImageLabelSize(직업사진, "C:/Users/USER-PC/IdeaProjects/java/src/메인/플레이어사진/거너.jpg", 100, 125);
        }else if(플레이어.직업.직업이름.contains("검사")){
            setImageLabelSize(직업사진, "C:/Users/USER-PC/IdeaProjects/java/src/메인/플레이어사진/검사.jpeg", 100, 125);
        }else if(플레이어.직업.직업이름.contains("워해머")){
            setImageLabelSize(직업사진, "C:/Users/USER-PC/IdeaProjects/java/src/메인/플레이어사진/워해머.jpg", 100, 125);
        }else{
            setImageLabelSize(직업사진, "C:/Users/USER-PC/IdeaProjects/java/src/메인/플레이어사진/초보모험가.png", 100, 125);
        }
    }

    public void 플레이어vs몬스터(플레이어 플레이어, 일반몬스터 몬스터) { //main 쓰레드에서 플레이어가 공격하는 것을 하고
        if(플레이어체력==null) {
            GUI기본설정(플레이어, 몬스터);
        }
            Thread 몬스터쓰레드 = new Thread(() -> {
                while (몬스터.MonHp > 0 && 플레이어.현재체력 > 0) {
                    try {
                        Thread.sleep(5000);
                        몬스터.공격하기(플레이어);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

            });

            Thread 전투GUI쓰레드 = new Thread(() -> {
                while (몬스터.MonHp > 0 && 플레이어.현재체력 > 0) {
                    전투GUI생성(플레이어, 몬스터);
                   몬스터상태가져오기 = 몬스터상태.getText();
                    플레이어상태가져오기 = 플레이어상태.getText();
                    if(몬스터상태가져오기!=null){
                        try {
                            Thread.sleep(2000);
                            몬스터상태.setText("");
                            플레이어.몬스터상태=null;
                            몬스터상태가져오기=null;
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if(플레이어상태가져오기!=null){
                        try {
                            Thread.sleep(1500);
                            플레이어상태.setText("");
                            몬스터.플레이어상태=null;
                            플레이어상태가져오기=null;
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                전투프레임.dispose();
            });

        Thread 스킬GUI쓰레드 = new Thread(() -> {
            while (몬스터.MonHp > 0 && 플레이어.현재체력 > 0 &&플레이어.직업.스킬사용횟수!=0) {
                if (플레이어.스킬사용할때 == 1) {
                    플레이어.스킬GUI생성();
                    플레이어.스킬사용할때 = 0;
                } else {
                    플레이어.스킬GUI생성();
                }
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            플레이어.스킬프레임.dispose();
        });

        스킬GUI쓰레드.start();
        몬스터쓰레드.start();
        전투GUI쓰레드.start();
        while (몬스터.MonHp > 0 && 플레이어.현재체력 > 0) {
            플레이어.전투할때할수있는행동(일반몬스터);
        }
        if (플레이어.현재체력 <= 0) {
            System.out.println("당신은 죽었습니다. OTL");
            System.out.println("다시 처음부터 던전에 도전하세요");
            던전.floor = 21;
        } else {
            System.out.println("축하합니다 " + 몬스터.몬스터이름 + "을 처치하였습니다!!");
            System.out.println("경험치를" + 몬스터.경험치 + "얻었습니다");
            플레이어.경험치 += 몬스터.경험치;
            플레이어.경험치측정();
            System.out.println();
        }

    }

    public synchronized void GUI기본설정(플레이어 플레이어,일반몬스터 몬스터){
        플레이어직업.setText("        " + 플레이어.직업.직업이름);
        몬스터이름.setText("          " + 몬스터.몬스터이름);
        VS텍스트.setText("vs");
        전투.setText("전투시작");
        플레이어체력 .setText("체력 : " + 플레이어.현재체력);
        몬스터체력.setText("체력 : " + 몬스터.MonHp);
        플레이어공격력.setText("공격력 : " + 플레이어.공격력);
        몬스터공격력.setText("공격력 : " + 몬스터.MonHit);
        플레이어방어력.setText("방어력 : "+플레이어.방어력);
    }


    public synchronized void 전투GUI생성(플레이어 플레이어, 일반몬스터 몬스터) {
        전투프레임.add(panel);
        SwingUtilities.invokeLater(() -> {
                    플레이어직업.setText("        " + 플레이어.직업.직업이름);
                    몬스터이름.setText("          " + 몬스터.몬스터이름);
                    VS텍스트.setText("vs");
                    전투.setText("전투시작");
                    플레이어체력.setText("체력 : " + 플레이어.현재체력);
                    몬스터체력.setText("체력 : " + 몬스터.MonHp);
                    if(몬스터.MonHit<0) {
                        몬스터공격력.setText("공격력 : 1");
                        몬스터.MonHit = 1;
                    }else if(몬스터.MonHit>0) {
                        몬스터공격력.setText("공격력 : " + 몬스터.MonHit);
                    }
                    플레이어공격력.setText("공격력 : " + 플레이어.공격력);

                    플레이어방어력.setText("방어력 : " + 플레이어.방어력);
                    몬스터상태.setText(플레이어.몬스터상태);
                    플레이어상태.setText(몬스터.플레이어상태);
                    플레이어상태.setForeground(Color.RED);
                    몬스터상태.setForeground(Color.RED);
                });
        직업사진설정(플레이어);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        SwingUtilities.invokeLater(() -> {
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(직업사진)
                        .addComponent(플레이어직업)
                        .addComponent(플레이어체력)
                        .addComponent(플레이어공격력)
                        .addComponent(플레이어방어력)
                        .addComponent(플레이어상태))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(전투)
                        .addComponent(VS텍스트))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(몬스터사진)
                        .addComponent(몬스터이름)
                        .addComponent(몬스터체력)
                        .addComponent(몬스터공격력)
                        .addComponent(몬스터상태))
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(전투)
                .addComponent(VS텍스트)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(직업사진)
                        .addComponent(몬스터사진))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(플레이어직업)
                        .addComponent(몬스터이름))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(플레이어체력)
                        .addComponent(몬스터체력))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(플레이어공격력)
                        .addComponent(몬스터공격력))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(플레이어방어력)
                        .addComponent(몬스터상태))
                        .addComponent(플레이어상태)
        );
            전투프레임.setSize(285, 350);   //자동크기설정
            전투프레임.setVisible(true);
        });
    }



    public static void setImageLabelSize(JLabel label, String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(resizeImage(imagePath, width, height)));
        label.setIcon(icon);
    }

    public static Image resizeImage(String 파일위치, int 너비, int 높이) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(파일위치));
            Image resizedImage = originalImage.getScaledInstance(너비, 높이, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage).getImage();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}









