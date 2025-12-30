package 몬스터;

import 메인.플레이어;

import java.util.Random;

public class 일반몬스터 {


    public String 몬스터이름,플레이어상태;
    public int MonHp,MonHit,경험치,출혈횟수=0;

    public 일반몬스터 (String 몬스터이름,int MonHp,int MonHit, int 경험치){
        this.몬스터이름=몬스터이름;
        this.MonHp=MonHp;
        this.MonHit=MonHit;
        this.경험치=경험치;
    }

    public 일반몬스터(){

    }

    public synchronized void 공격하기(플레이어 플레이어) {
        Random 공격랜덤 = new Random();
        int 공격실패 =공격랜덤.nextInt(100);
        if (MonHp > 0 && 플레이어.현재체력 > 0) {
            if (공격실패 < 플레이어.회피 / 2) {
                플레이어상태 = "몬스터 공격 회피";
            } else {
                if(MonHit==1 ||(MonHit - (int) (플레이어.방어력 * 0.1) ) < 1 ){
                    플레이어.현재체력--;
                    플레이어상태 = "피해량 1";
                }else {
                        플레이어.현재체력 -= (MonHit - (int) (플레이어.방어력 * 0.1));
                        플레이어상태 = "피해량 " + (MonHit - (int) (플레이어.방어력 * 0.1));
                }
            }
            Thread 출혈쓰레드 = new Thread(() -> {
                if (플레이어.출혈데미지 != 0) {
                    플레이어.몬스터상태 = "출혈 -" + 플레이어.출혈데미지 / 2;
                    MonHp -= (플레이어.출혈데미지 / 2);
                    출혈횟수++;
                    if (출혈횟수 == 2) {
                        출혈횟수 = 0;
                        플레이어.출혈데미지 = 0;
                    }
                }
            });
            출혈쓰레드.start();
        }
    }

}
