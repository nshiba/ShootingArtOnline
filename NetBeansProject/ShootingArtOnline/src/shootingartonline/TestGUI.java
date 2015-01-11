package shootingartonline;

/**
 *
 * @author shiba
 */
public class TestGUI {
	public static void main(String[] args) {
        SocketInput input = new SocketInput();
        Thread t1 = new Thread(input);
        t1.start();
//		SocketOutput output = new SocketOutput();
//		Thread t2 = new Thread(output);
//		t2.start();
	}
}
