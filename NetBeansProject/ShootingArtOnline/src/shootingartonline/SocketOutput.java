package shootingartonline;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import static game.Global.*;
import java.io.OutputStream;
import java.net.InetAddress;
import static shootingartonline.StaticData.*;

/**
 *
 * @author shiba
 */
public class SocketOutput implements Runnable{

	private String HOST = "localhost";
	private Socket socket = null;

	public SocketOutput(Socket socket){
		this.socket = socket;
//		try {
////			System.out.println("接続するIPを入力してください");
////            String host = keyin.readLine();
//			InetSocketAddress socketAddress = new InetSocketAddress(HOST,PORT);
//			
//			socket = new Socket();
//			// タイムアウトは10秒(10000msec)
//			socket.connect(socketAddress, 10000);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void run() {
		System.out.println("run of Output");
//		// 接続先の情報を入れるInetAddress型のinadrを用意する。
//		InetAddress inadr;
//		// inadrにソケットの接続先アドレスを入れ、nullである場合には
//		// 接続失敗と判断する。
//		// nullでなければ、接続確立している。
//		if ((inadr = socket.getInetAddress()) != null) {
//			System.out.println("Connect to " + inadr);
//		} else {
//			System.out.println("Connection failed.");
//			return;
//		}

        while (true) {                
		//マッチング処理
			String line;
			if(getMatch() == false){
				 line = "macthing" + "\n";
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					Logger.getLogger(SocketInput.class.getName()).log(Level.SEVERE, null, ex);
				}
			}////////////////マッチング完了
			else{
            	String bullet = "0";
            	if(getMyBulletFire()){
            	    bullet = String.valueOf(getMyBulletNum());
            	}
            	line = String.valueOf(getX()) + "," + String.valueOf(getX()) + "," + bullet + "\n";
			}

			//書き込み
			try {
				OutputStream out = socket.getOutputStream();
				out.write(line.getBytes("UTF-8"));
				out.flush();

				Thread.sleep(10);
				
			} catch (IOException ex) {
				Logger.getLogger(SocketOutput.class.getName()).log(Level.SEVERE, null, ex);
				System.out.println("エラーが発生したので終了します");
				try {
					socket.close();
				} catch (IOException ex1) {
					Logger.getLogger(SocketOutput.class.getName()).log(Level.SEVERE, null, ex1);
				}
				return;
			} catch (InterruptedException ex) {
				Logger.getLogger(SocketOutput.class.getName()).log(Level.SEVERE, null, ex);
			}
        }
	}
}
