package shootingartonlineserver;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import static shootingartonlineserver.StaticData.*;

public class MainServer extends Thread{
    private LinkedList<SocketChannel> channelList = new LinkedList<>();
    private Map<SocketChannel, ByteArrayOutputStream> bufferMap = new HashMap<>();
    private Selector selector = null;
    private Map<SocketChannel, PlayerBean> playerMap = new HashMap<>();
    
    public static void main(String[] args){
        new MainServer().start();
    }

    @Override
    public void run(){
        ServerSocketChannel serverSocketChannel = null;

        try {
            //指定ポートでSocketをバインド
            //ノンブロッキングモードでChannelを監視
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("サーバー起動");
            
            //通常は、OP_READ を監視しておく
            //srcChannel が readable になったらsrcChannel#read() してデータを バッファ(Buf) にため込む
            //宛先 destChannel の監視状態を OP_READ から OP_WRITE に変更する
            //OP_WRITE を監視している destChannel が writable になったらバッファの内容を destChannel#write() する
            //destChannel の監視状態を OP_WRITE から OP_READ に戻す
            while(selector.select() > 0) {
                Iterator<SelectionKey> keyIt = selector.selectedKeys().iterator();

                while(keyIt.hasNext()) {
                    SelectionKey key = keyIt.next();
                    keyIt.remove();

                    if (key.isAcceptable()) {
                        doAccept((ServerSocketChannel) key.channel());
                    } else if (key.isReadable()) {
                        doRead((SocketChannel) key.channel());
                    } else if (key.isWritable()) {
                        doWrite((SocketChannel) key.channel());
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("サーバー停止");
                serverSocketChannel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Serverにつなぎにきたクライアントが見つかった時の処理
    //Socketの状態はOP_READにしておく
    private void doAccept(ServerSocketChannel acceptServerChannel){
        try {
            SocketChannel channel = acceptServerChannel.accept();
            //ノンブロッキングモードにしておく※重要
            channel.configureBlocking(false);
            System.out.println("Accesptに入りました" + channel);

             //ChannelにOP_READで登録
            channel.register(selector, SelectionKey.OP_READ);
            channelList.add(channel);

            //プレイヤーを生成
            //プレイヤー数はHashMapで管理
            PlayerBean player = new PlayerBean();
            player.setPlayerNumber(channelList.size());
            playerMap.put(channel, player);

            String remoteAddr = channel.socket().getRemoteSocketAddress().toString();
            System.out.println("接続されました : " + remoteAddr);
//			System.out.println("現在の人数は" + playerMap.size() + "人です");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Socketに書き込まれた時の処理
    //Socketの状態はOP_WRITEにする
    private void doRead(SocketChannel channel) {
        try {
            String remoteAddr = channel.socket().getRemoteSocketAddress().toString();

            ByteBuffer buf = ByteBuffer.allocate(BUF_SIZE);
            Charset charset = Charset.forName("UTF-8");

            if (channel.read(buf) > 0) {
                buf.flip();

                PlayerBean player = playerMap.get(channel);

                String bufContent = charset.decode(buf).toString();
                String[] contents = bufContent.split("\n");
                for (String content : contents) {

					if(content.equals("macthing")){
//						System.out.println("受信しました playerNumber" + player.getPlayerNumber());
						continue;
					}

                    String[] zahyou = content.split(",");
                    player.setX(Integer.valueOf(zahyou[0]));
                    player.setY(Integer.valueOf(zahyou[1]));
                    player.setBulletType(Integer.valueOf(zahyou[2]));
//                    System.out.println("受信しました playerNumber" + player.getPlayerNumber() + " : " + remoteAddr +  " : " + content);
                }
                playerMap.put(channel,player);

                buf = charset.encode(CharBuffer.wrap(bufContent));

                byte[] bytes = new byte[buf.limit()];
                buf.get(bytes);

                //bufferMapから取り出し   
                ByteArrayOutputStream bout = bufferMap.get(channel);

                //はじめての場合は新規作成
                if (bout == null) {
                    bout = new ByteArrayOutputStream();
                    bufferMap.put(channel, bout);
                }
                bout.write(bytes);

                //宛先が書込可能になるのを監視する
                channel.register(selector, SelectionKey.OP_WRITE);

                if (bufContent.equals("logout")) {
                    logout(channel);
                }
            }
        } catch (Exception e) {
            //Socketが切断されたらログアウト
            logout(channel);
        }
    }

    //socketが書き込み可能状態になった時の処理
    //バッファの内容をchannel#write()する
    //writeしたあとは監視状態をOP_READにする
    private void doWrite(SocketChannel channel) {
        ByteArrayOutputStream bout = bufferMap.get(channel);

        if(bout != null) {
//            System.out.println("書込します");
            PlayerBean player = playerMap.get(channel);
            ByteBuffer buf = ByteBuffer.allocate(BUF_SIZE);
            Charset charset = Charset.forName("UTF-8");

            try {
                String bufContent = "";

				if(channelList.size() < 2){
					bufContent = "macthing" + "\n";
				}
				else{
                	for(SocketChannel allChannel : channelList){
                	    PlayerBean enemy = playerMap.get(allChannel);
                	    if(player.getPlayerNumber() != enemy.getPlayerNumber() ){
                	        bufContent = (int)enemy.getX() + "," + (int)enemy.getY() + "," + (int)enemy.getBulletType() + "\n";
                	    }
                	}
				}

                buf = charset.encode(CharBuffer.wrap(bufContent));

                int size = channel.write(buf);

//                System.out.println("送信サイズ : " + size + "/" + buf.limit());

                if (buf.hasRemaining()) {
                    //bbufの中を送信しきれなかった場合、残りのBufferMapに書き戻し
                    ByteArrayOutputStream rest = new ByteArrayOutputStream();
                    rest.write(buf.array(), buf.position(), buf.remaining());
                    bufferMap.put(channel, rest);

                    //宛先が書込可能になるのを監視
                    //宛先が切断された時の為に読込も監視
                    channel.register(selector, SelectionKey.OP_WRITE + SelectionKey.OP_READ);
                } else {
                    //bbufの送信がすべて完了したのでbufferMapから今回送信した分を削除
                    bufferMap.remove(channel);
                    //宛先が書込可能になるのを監視するのをやめる
                    channel.register(selector, SelectionKey.OP_READ);
                }
            } catch (Exception e) {
                e.printStackTrace();
                //Socketが切断されたらログアウト
                logout(channel);
            }
        }
    }

    //切断処理
    private void logout(SocketChannel channel) {
        String remoteAddr = channel.socket().getRemoteSocketAddress().toString();
        System.out.println("ログアウト : " + remoteAddr);

        try {
            //channel切断後playerMapからChannelを削除
                channel.finishConnect();
                channel.close();
                playerMap.remove(channel);

                if (channelList.remove(channel)) {
                    System.out.println("リストから" + channel + "を削除しました");
                } else {
                    System.out.println("リストから" + channel + "の削除を失敗しました");
                }
        } catch (Exception e) {
            System.out.println("channelの切断に失敗しました。");
            e.printStackTrace();
            e = null;

            return;
        }
        System.out.println("channelを切断しました。");
    }
}
