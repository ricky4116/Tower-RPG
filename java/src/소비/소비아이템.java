package 소비;

public class 소비아이템 {

    public String 소비아이템능력,등급,소비아이템이름;
    public int 소비아이템능력치, 소비아이템개수,소비아이템가격;
    public 소비아이템(String 등급,String 소비아이템이름, String 소비아이템능력, int 소비아이템능력치, int 소비아이템개수, int 소비아이템가격) {
        this.소비아이템이름=소비아이템이름;
        this.소비아이템능력=소비아이템능력;
        this.소비아이템능력치=소비아이템능력치;
        this.소비아이템개수=소비아이템개수;
        this.소비아이템가격=소비아이템가격;
        this.등급=등급;
    }

    public 소비아이템() {

    }

    public void 소비아이템출력(){
        System.out.print(등급+" "+소비아이템이름+" "+소비아이템능력+" +"+소비아이템능력치+" "+소비아이템가격+"G");
    }

}
