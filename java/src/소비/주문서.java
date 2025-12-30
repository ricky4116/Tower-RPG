package 소비;

public class 주문서 extends 소비아이템{


    public int 강화확률,강화비용;
    public 주문서(String 등급,String 소비아이템이름,String 소비아이템능력, int 소비아이템능력치, int 소비아이템개수, int 소비아이템가격) {
        super(등급,소비아이템이름,소비아이템능력,소비아이템능력치,소비아이템개수,소비아이템가격);
        주문서강화확률및비용설정();
    }

    @Override
    public void 소비아이템출력(){
       super.소비아이템출력();
    }

    public void 주문서강화확률및비용설정(){
        if(등급.equals("초급")){
            강화확률=40;
            강화비용=50;
        } else if(등급.equals("중급")){
            강화확률=20;
            강화비용=100;
        }else {
            강화확률=7;
            강화비용=150;
        }
    }

}



