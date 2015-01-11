import java.io.InputStream;
import java.net.ServerSocket; //ネットワーク関連のパッケージを利用する
import java.net.Socket;
//　入出力関連パッケージを利用する
//　ユーティリティパッケージを利用する

public class DendaiTCPServ1 {

	/*
	 * メイン・メソッド 接続要求のあったクライアントに対して接続を行い クライアントから送られるメッセージを受信する。
	 */
	public static void main(String arg[]) {
		try {
			/* 通信の準備をする */
			ServerSocket server = new ServerSocket(5001); // ポート番号を指定し、クライアントとの接続の準備を行う

			System.out.println("Server is waiting connection at 5001 port");
			Socket socket = server.accept(); // クライアントからの接続要求を待ち、
			// 要求があればソケットを取得し接続を行う
			System.out.println("connected");

			/* 受信する */
			InputStream ins = socket.getInputStream();

			final int SIZE = 255;
			byte[] b = new byte[SIZE];
			ins.read(b);

			String message = new String(b);

			System.out.println("Server:received message is >" + message);

			ins.close();
			socket.close();

			server.close();

		}// エラーが発生したらエラーメッセージを表示してプログラムを終了する
		catch (Exception e) {
			System.out.println("エラーが発生したのでプログラムを終了します");
		}
	}
}