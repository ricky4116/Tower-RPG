package 메인;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class 속성 {

    public String 속성;
    public 속성(){
        랜덤으로속성설정();
    }
    public void 랜덤으로속성설정(){
        Random ran = new Random();
        int 랜덤 = ran.nextInt(4);
        if(랜덤==1){
            속성 = "물";
        }else if(랜덤==2){
            속성 = "불";
        }else if(랜덤==3){
            속성 = "땅";
        }else {
            속성 ="나무";
        }
    }  //물>불>땅>나무

    public String get속성(){
        return 속성;
    }

    public void 속성비교(플레이어 플레이어,던전 던전){
        ArrayList<String> 속성순위 = new ArrayList<>(Arrays.asList("물", "불", "땅", "나무","물"));
        for(int i=0;i<플레이어.장착한장비.size();i++){
            if(!플레이어.장착한장비.get(i).속성.equals("없음")) {
                if (i == 0) {
                    if (속성순위.indexOf(플레이어.장착한장비.get(i + 1).속성) == 속성순위.indexOf(던전.속성)) {
                        플레이어.공격력 -= 2;
                        플레이어.방어력 -= 2;
                    } else if (속성순위.indexOf(플레이어.장착한장비.get(플레이어.장착한장비.size()-1).속성) == 속성순위.indexOf(던전.속성)) {
                        플레이어.공격력 += 2;
                        플레이어.방어력 += 2;
                    }
                    break;
                }else{
                    if (속성순위.indexOf(플레이어.장착한장비.get(i + 1).속성) == 속성순위.indexOf(던전.속성)) {
                        플레이어.공격력 -= 2;
                        플레이어.방어력 -= 2;
                    } else if (속성순위.indexOf(플레이어.장착한장비.get(i-1).속성) == 속성순위.indexOf(던전.속성)) {
                        플레이어.공격력 += 2;
                        플레이어.방어력 += 2;
                    }
                }
            }
        }
        System.out.println("던전과 무기의 속성에 따라서 공격력과 방어력이 변화되었습니다");
        System.out.println();
    }

}
