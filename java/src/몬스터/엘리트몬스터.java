package 몬스터;

import 메인.플레이어;
import java.util.Random;

public class 엘리트몬스터 extends 일반몬스터 {
    int 디버프;
    public 엘리트몬스터(String 몬스터이름,int MonHp,int MonHit, int 경험치) {
        super(몬스터이름,MonHp,MonHit,경험치);
    }

    public 엘리트몬스터(){

    }
    public void 플레이어에게안좋은효과(플레이어 플레이어){
        Random 랜덤 = new Random();
        int 디버프랜덤 = 랜덤.nextInt(5);
        플레이어.공격력-=디버프+디버프랜덤;
        플레이어.현재체력-=디버프+디버프랜덤;
        System.out.println(몬스터이름+"의 효과로 플레이어의 공격력이 감소했습니다");
        System.out.println(몬스터이름+"의 효과로 플레이어의 체력이 감소했습니다");
    }

}
