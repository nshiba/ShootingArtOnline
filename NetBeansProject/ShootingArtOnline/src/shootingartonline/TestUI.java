package shootingartonline;

import static game.Global.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestUI {
    private SocketClient socket = null;

    public TestUI(){
        System.out.println("コンストラクタ");
        this.socket = new SocketClient() {
            @Override
            protected void onMassage(String str) {
                String[] massages = str.split(",");
                setEnemyX(Integer.valueOf(massages[0]));
                setEnemyY(Integer.valueOf(massages[1]));
            }
        };

        this.socket.start();
    }

    public static void main(String[] args){
        new TestUI().run();
    }

    public void run(){
//        PlayerBean player = new PlayerBean();
//        player.setX(100);
//        player.setY(100);
//        player.setBulletType(0);
//        player.setHp(100);


        BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {                
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(TestUI.class.getName()).log(Level.SEVERE, null, ex);
//            }
            try {
                String line = keyin.readLine() + "\n";
                socket.write(line);
            } catch (IOException ex) {
                Logger.getLogger(TestUI.class.getName()).log(Level.SEVERE, null, ex);
            }
//            socket.write(playerinfoToString(player));
        }
    }

    public static String playerinfoToString(PlayerBean player){
        String str = player.getPlayerNumber() + "," + player.getX() + "," + player.getY() + "," + player.getBulletType() + "," + player.getHp() + "\n";
        return str;
    }
}
