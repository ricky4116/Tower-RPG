package 메인;

public class 튜토리얼 implements Runnable{

    @Override
    public void run() {
        try {
            게임아이콘();
            Thread.sleep(400);
            장비설명();
            Thread.sleep(400);
            전직관련설명();
            Thread.sleep(400);
            은신처설명();
            Thread.sleep(400);
            전투시스템설명();
            Thread.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void 게임아이콘(){
       System.out.println( "████████╗ ██████╗ ██╗    ██╗███████╗██████╗     ██████╗ ██████╗  ██████╗");
       System.out.println( "╚══██╔══╝██╔═══██╗██║    ██║██╔════╝██╔══██╗    ██╔══██╗██╔══██╗██╔════╝");
       System.out.println( "   ██║   ██║   ██║██║ █╗ ██║█████╗  ██████╔╝    ██████╔╝██████╔╝██║  ███╗");
       System.out.println( "   ██║   ██║   ██║██║███╗██║██╔══╝  ██╔══██╗    ██╔══██╗██╔═══╝ ██║   ██║");
       System.out.println( "   ██║   ╚██████╔╝╚███╔███╔╝███████╗██║  ██║    ██║  ██║██║     ╚██████╔╝");
       System.out.println( "   ╚═╝    ╚═════╝  ╚══╝╚══╝ ╚══════╝╚═╝  ╚═╝    ╚═╝  ╚═╝╚═╝      ╚═════╝");

    }


    public void 장비설명(){
        System.out.println("________장비 관련 설명________");
        System.out.println("둔기류 : 도끼, 망치, 몽둥이");
        System.out.println("검 : 단검, 장검, 쌍검");
        System.out.println("화기 : 권총, 기관단총, 샷건, 소총");
    }
    public void 은신처설명(){
        System.out.println("________은신처 관련 설명________");
        System.out.println("도박장 : 벌어드린 골드를 불릴 수 있다");
        System.out.println("장비 상점 : 장비를 사고 팔수 있다");
        System.out.println("소비 상점 : 포션과 주문서를 사거나 몬스터를 사냥한 뒤 얻은 전리품을 팔 수 있다");
    }

    public void 전직관련설명(){
        System.out.println("________전직 관련 설명________");
        System.out.println("초급 직업, 중급 직업, 고급 직업으로 레벨이 오르면 전직할 수 있다");
        System.out.println("각 직업마다 고유의 특성을 가지고 있는 스킬들을 보유하고 있다");
    }
    public void 전투시스템설명(){
        System.out.println("___________________전투 시스템 설명___________________");
        System.out.println("    **************    **************    **************");
        System.out.println("    *            *    *            *    *            *");
        System.out.println("    *            *    *            *    *            *");
        System.out.println("    *    XX%     *    *    XX%     *    *    XX%     *");
        System.out.println("    *            *    *            *    *            *");
        System.out.println("    *            *    *            *    *            *");
        System.out.println("    **************    **************    **************");
        System.out.println("플레이어는 각각 세가지의 확률로 몬스터를 때릴 수 있습니다");
        System.out.println("High Risk High Return이니 주의하세요!");
        System.out.println();
    }


}



/*    public void 문자(){
        System.out.println( "  ██████╗ █████╗ ███████╗███████╗    ██████╗ ██████╗  ██████╗ ");
        System.out.println( " ██╔════╝██╔══██╗██╔════╝██╔════╝    ██╔══██╗██╔══██╗██╔════╝ ");
        System.out.println( " ██║     ███████║█████╗  █████╗      ██████╔╝██████╔╝██║  ███╗");
        System.out.println( " ██║     ██╔══██║██╔══╝  ██╔══╝      ██╔══██╗██╔═══╝ ██║   ██║");
        System.out.println( " ╚██████╗██║  ██║██║     ███████╗    ██║  ██║██║     ╚██████╔╝");
        System.out.println( "  ╚═════╝╚═╝  ╚═╝╚═╝     ╚══════╝    ╚═╝  ╚═╝╚═╝      ╚═════╝");
    }*/