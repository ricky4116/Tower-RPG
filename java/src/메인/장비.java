package 메인;

import java.util.Random;

public class 장비 {
    public int 희귀도;
    public int 내구도;
    public int 골드측정;
    public String 장비종류;
    String 희귀도표현,내구도표현,희귀도능력치표현,속성;
    Random ran = new Random();
    속성 속성생성 = new 속성();

    장비(String 장비종류){
        내구도 = 40+ran.nextInt(61);
        희귀도 = 랜덤설정()+ran.nextInt(5);
        희귀도표현 = 희귀도명칭설정();
        내구도표현 = 내구도명칭설정();
        골드측정 = 골드측정();
        if(장비종류.contains("무기")){
            랜덤무기종류선정();
            속성=속성생성.get속성();
        }else if(장비종류.contains("방어구")){
            랜덤방어구종류선정();
            속성="없음";
        }
    }

    public int 랜덤설정() {
        Random 희귀도랜덤 = new Random();
        int 희귀 = 희귀도랜덤.nextInt(100) + 1;
        if (희귀 <= 2) {
            return 20;
        } else if (희귀 <= 10) {
            return 15;
        } else if (희귀 <= 42) {
            return 10;
        } else return 5;
    }

    public String 희귀도명칭설정(){
        if(희귀도>=5 && 희귀도<10){
            return  "<Common>";
        }else if(희귀도>=10 && 희귀도<15){
            return "<Rare>";
        }else if(희귀도>=15 && 희귀도<20){
            return "<Epic>";
        }else if(희귀도 >=20){
            return  "<Hero>";
        }
        return "Error";
    }

    public String 내구도명칭설정(){
        if(내구도>=40 && 내구도<60){
            return "평범한";
        }else if(내구도>=60 && 내구도<80){
            return "쓸만한";
        }else if(내구도>=80 && 내구도<100){
            return "깨끗한";
        }else if(내구도 ==100){
            return "완벽한";
        }
        return "Error";
    }

    public void 랜덤무기종류선정(){
        int 랜덤무기선정= ran.nextInt(3);
        if(랜덤무기선정 ==0){
            장비종류= "총";
            희귀도능력치표현="공격력";
        }else if(랜덤무기선정 ==1){
            장비종류= "둔기";
            희귀도능력치표현="공격력";
        }else {
            장비종류= "검";
            희귀도능력치표현="공격력";
        }
    }

    public void 랜덤방어구종류선정() {
        int 랜덤방어구선정 = ran.nextInt(3);
         if (랜덤방어구선정 == 0) {
            장비종류 = "장갑";
            희귀도능력치표현 = "회피";
        } else if (랜덤방어구선정 == 1) {
            장비종류 = "갑옷";
            희귀도능력치표현 = "방어력";
        } else{
            장비종류 = "신발";
            희귀도능력치표현 = "회피";
        }
    }

    public int 골드측정(){
        if(희귀도표현 .equals("<Common>")){
            return 150+내구도;
        }else if(희귀도표현.equals("<Rare>")){
            return 250+내구도;
        }else if(희귀도표현.equals("<Epic>")){
            return 350+내구도;
        }else if(희귀도표현.equals("<Hero>")){
            return 450+내구도;
        }
        return 0;
    }




}
