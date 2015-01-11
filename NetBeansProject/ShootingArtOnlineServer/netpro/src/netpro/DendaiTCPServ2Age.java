import java.io.ObjectInputStream;	//　入出力関連パッケージを利用する
import java.io.ObjectOutputStream;
import java.net.ServerSocket;	//ネットワーク関連のパッケージを利用する
import java.net.Socket;
//　ユーティリティパッケージを利用する

public class DendaiTCPServ2Age{

	/*メイン・メソッド
	 * 接続要求のあったクライアントに対して接続を行い
	 * クライアントから送られる年齢を受信し、その年齢を元に
	 * 飲酒の可否を判定した結果をクライアントに対して送信する
	 */
	public static void main(String arg[]){
		try{
			/* 通信の準備をする */
			ServerSocket server =
				new ServerSocket(5002);		//ポート番号を指定し、クライアントとの接続の準備を行う
			Socket socket = server.accept();	//クライアントからの接続要求を待ち、
						 		// 要求があればソケットを取得し接続を行う 		
	 		/* 年齢を受信する */
	 		ObjectInputStream ois =
				new ObjectInputStream(socket.getInputStream());
			Integer data = (Integer)ois.readObject();//Integerクラスでキャスト。
			int age = data.intValue();

			/* 飲酒の可否を判定する */
			String message;
			if(age>=20){
				message = "Drink OK";
			}else{
				message = "Drink NO";
			}

			/* 判定結果をクライアントに送信する */
			ObjectOutputStream oos =
				new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(message);
			oos.flush();
			
			ois.close();
			oos.close();
			
			socket.close();
			server.close();

		}// エラーが発生したらエラーメッセージを表示してプログラムを終了する
		catch(Exception e){ System.out.println("エラーが発生したのでプログラムを終了します") ;}
	}
}