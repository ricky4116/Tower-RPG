package 몬스터;

import 메인.플레이어;

import java.util.Random;

public class 보스몬스터 extends 엘리트몬스터 {


    public 보스몬스터 (String 몬스터이름,int MonHp,int MonHit,int 경험치) {
        super(몬스터이름,MonHp+40,MonHit+3,경험치+10);

    }

    public 보스몬스터(){

    }

    @Override
    public void 플레이어에게안좋은효과(플레이어 플레이어){
        super.플레이어에게안좋은효과(플레이어);
        Random 랜덤 = new Random();
        int 장비없애기랜덤 = 랜덤.nextInt(4);
        플레이어.장착한장비.remove(장비없애기랜덤);
    }



}
