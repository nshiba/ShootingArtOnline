package shootingartonline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;

import static shootingartonline.StaticData.*;

public abstract class SocketClient extends Thread {
    private String HOST = "localhost";

    private static Charset charset = Charset.forName("UTF-8");
    private Socket socket = null;

    public SocketClient(){
        BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("接続するIPを入力してください");
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

            String remoteAddr = socket.getRemoteSocketAddress().toString();

            while ((size = socket.getInputStream().read(buf))> 0) {                
                String strToken = new String(buf, 0, size, "UTF-8");
                String[] massages = strToken.split("\n");
                for (String message : massages) {
                if(message.equals("logout")){
                    logout();
                    break;
                }
//                    System.out.println(message);
                    onMassage(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logout();
        }
    }

    public void write(String str){
        try {
            socket.getOutputStream().write(str.getBytes("UTF-8"));
        } catch (IOException ex) {
            ex.printStackTrace();
            logout();
        }
    }

    protected abstract void onMassage(String str);
    
    //ログアウト処理
    //サーバ側にログアウトすることを通知
    private void logout() {
        String str = "logout";
        try {
            socket.getOutputStream().write(str.getBytes("UTF-8"));
            socket.close();
            System.out.println("ログアウトしました。");

        } catch (Exception e) {
            try {
                socket.close();
                e.printStackTrace();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
