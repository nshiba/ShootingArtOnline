package shootingartonline;

import game.Global;
import static game.Global.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketInput implements Runnable {
    private SocketClient socket = null;

//    public static void main(String[] args){
//        new TestUI().run();
//    }

    @Override
    public void run(){
		System.out.println("run of Input");

        this.socket = new SocketClient() {
            @Override
            protected void onMassage(String str) {
                System.out.println(str);
                String[] massages = str.split(",");
                setEnemyX(Integer.valueOf(massages[0]));
                setEnemyY(Integer.valueOf(massages[1]));
                setEnemyBulletNum(Integer.valueOf(massages[2]));
            }
        };
        this.socket.start();
    }
}