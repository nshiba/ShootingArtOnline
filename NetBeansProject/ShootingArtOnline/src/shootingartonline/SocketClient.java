package shootingartonline;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;

import static shootingartonline.StaticData.*;
import static game.Global.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class SocketClient extends Thread {
	private String HOST = "localhost";
	
	private static Charset charset = Charset.forName("UTF-8");
	private Socket socket = null;
	
	public SocketClient(){

		setMatch(false);

		try {
//			System.out.println("接続するIPを入力してください");
//            String host = keyin.readLine();
			InetSocketAddress socketAddress = new InetSocketAddress(HOST,PORT);
			
			socket = new Socket();
			// タイムアウトは10秒(10000msec)
			socket.connect(socketAddress, 10000);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		// 接続先の情報を入れるInetAddress型のinadrを用意する。
		InetAddress inadr;
		// inadrにソケットの接続先アドレスを入れ、nullである場合には
		// 接続失敗と判断する。
		// nullでなければ、接続確立している。
		if ((inadr = socket.getInetAddress()) != null) {
			System.out.println("Connect to " + inadr);
		} else {
			System.out.println("Connection failed.");
			return;
		}
		
		byte[] buf = new byte[BUF_SIZE];
		int size;
		try {
			while ((size = socket.getInputStream().read(buf))> 0) {
				String strToken = new String(buf, 0, size, "UTF-8");
				String[] massages = strToken.split("\n");
				for (String message : massages) {
					System.out.println(message);
					if(message.equals("logout")){
//						logout();
						break;
					}
					if(message.equals("macthing")){
						continue;
					}
					if(getMatch() == false){
						setMatch(true);
					}
					System.out.println("onMessage");
					onMassage(message);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("エラーが発生したので終了します");
			close();
		}
	}
	
	protected abstract void onMassage(String str);
	
	//ログアウト処理
	//サーバ側にログアウトすることを通知
	private void close(){
		try {
			socket.close();
		} catch (IOException ex) {
			Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
//	private void logout() {
//		String str = "logout";
//		try {
//			socket.getOutputStream().write(str.getBytes("UTF-8"));
//			socket.close();
//			System.out.println("ログアウトしました。");
//			
//		} catch (Exception e) {
//			try {
//				socket.close();
//				e.printStackTrace();
//				
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		}
//	}
}