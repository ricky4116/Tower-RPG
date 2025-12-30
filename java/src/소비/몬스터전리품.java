package 소비;

public class 몬스터전리품 extends 소비아이템{
    public String 전리품능력;
    public int 전리품능력치;
    public 몬스터전리품(String 등급,String 소비아이템이름 ,String 소비아이템능력, int 소비아이템능력치, int 소비아이템개수, int 소비아이템가격) {
        super(등급,소비아이템이름,소비아이템능력,소비아이템능력치,소비아이템개수,소비아이템가격);
    }

    public 몬스터전리품(){

    }

    @Override
    public void 소비아이템출력(){
        System.out.print(소비아이템이름+" "+소비아이템가격+"G");
    }


}
