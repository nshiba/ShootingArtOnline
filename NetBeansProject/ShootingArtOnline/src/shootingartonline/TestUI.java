package shootingartonline;

import game.Global;
import static game.Global.*;

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
                System.out.println(str);
                String[] massages = str.split(",");
                setEnemyX(Integer.valueOf(massages[0]));
                setEnemyY(Integer.valueOf(massages[1]));
                setEnemyBulletNum(Integer.valueOf(massages[2]));
            }
        };
        this.socket.start();

///////////////////////////////////////////////////////////

        while (true) {                
            String bullet = "0";
            if(Global.getMyBulletFire()){
                bullet = String.valueOf(Global.getMyBulletNum());
            }
            String line = String.valueOf(Global.getX()) + "," + String.valueOf(Global.getX()) + "," + bullet + "\n";
            socket.write(line);

//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(TestUI.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }
}