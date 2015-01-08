package shootingartonline;

import static game.Global.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestUI implements Runnable {
    private SocketClient socket = null;

//    public TestUI(){
//        System.out.println("コンストラクタ");
//        this.socket = new SocketClient() {
//            @Override
//            protected void onMassage(String str) {
//                String[] massages = str.split(",");
//                setEnemyX(Integer.valueOf(massages[0]));
//                setEnemyY(Integer.valueOf(massages[1]));
//            }
//        };
//        this.socket.start();
//    }

    public static void main(String[] args){
        System.out.println("kitakitakita");
        new TestUI().run();
    }

    @Override
    public void run(){
        System.out.println("kitakitakita");
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

///////////////////////////////////////////////////////////

        BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {                
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