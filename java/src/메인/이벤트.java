package 메인;

import java.util.Random;

public class 이벤트 {

    public void 이벤트선택(플레이어 플레이어){
        Random 이벤트고르기 = new Random();
        int 선택 = 이벤트고르기.nextInt(4);
        if(선택 == 0){
            돈주는이벤트(플레이어);
        }else if(선택 == 1){
            함정이벤트(플레이어);
        }else if(선택 == 2){
            체력회복이벤트(플레이어);
        }else{
            버프이벤트(플레이어);
        }
        System.out.println();
    }
    public void 돈주는이벤트(플레이어 플레이어){
        System.out.println("땅에 떨어져 있는 돈을 발견했습니다");
        Random 돈 = new Random();
        int 전리품돈 = 돈.nextInt(150);
        System.out.println("+"+전리품돈+"G");
        플레이어.보유골드+=전리품돈;
    }
    public void 함정이벤트(플레이어 플레이어){
        System.out.println("당신은 함정에 걸려들었습니다");
        Random 함정데미지 = new Random();
        int 함정 = 함정데미지.nextInt(10);
        System.out.println("-"+함정+"HP");
        플레이어.현재체력-=함정;
    }
    public void 체력회복이벤트(플레이어 플레이어){
        System.out.println("체력회복의 성소에 들어왔습니다");
        System.out.println("당신의 체력이 모두 회복됩니다.");
        System.out.println("+"+(플레이어.최대체력-플레이어.현재체력)+"HP");
        플레이어.현재체력=플레이어.최대체력;

    }
    public void 버프이벤트(플레이어 플레이어){
        System.out.println("랜덤한 버프가 당신에게 주어졌습니다");
        Random 버프 = new Random();
        int 랜덤버프 = 버프.nextInt(3)+1;
        if(랜덤버프==1){
            System.out.println();
            System.out.println("<버프> 공격력 증가");
            System.out.println("<효과> 이번 층에서 당신의 공격력이 5 증가됩니다");
            플레이어.공격력+=5;
        }else if(랜덤버프==2){
            System.out.println();
            System.out.println("<버프> 방어력 증가");
            System.out.println("<효과> 이번 층에서 당신의 방어력이 5 증가됩니다");
            플레이어.방어력+=5;
        }else {
            System.out.println();
            System.out.println("<버프> 회피 증가");
            System.out.println("<효과> 이번 층에서 당신의 회피가 2 증가됩니다");
            플레이어.회피+=2;
        }
    }

}
