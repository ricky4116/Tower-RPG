package 메인;

import java.awt.*;

import javax.swing.*;

class 시간타이머 extends JLabel implements Runnable {
    int width = 75, height = 75;
    int x = 200, y = 150;
    int second=1;

    public 시간타이머() {
        setOpaque(true);
        setBounds(x, y, width, height);
        setForeground(Color.BLUE);
        setFont(new Font("맑은고딕", Font.PLAIN, 30));
        setHorizontalAlignment(JLabel.CENTER);

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                if (second > 0) {
                    if(second<60){
                        setText("<html>"+((second/720)+1)+"일차<br>"+second+"초"); //HTML 허용하게 하고 이를 통해서 한줄 띄우기
                    }else {
                        setText("<html>"+((second/720)+1)+"일차<br>"+second / 60 + "분" + second % 60 + "초");
                    }
                    second ++;
                } else {
                    //System.out.println("종료");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}