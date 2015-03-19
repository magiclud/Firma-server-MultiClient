package firma;


public class UruchomienieFirmy {

	public static void main(String[] args) {
		ServerListaZadan serverListaZadan = new ServerListaZadan(9000);
	//	ServerMagazyn serverMagazyn = new ServerMagazyn(3333);
		
		new Thread(serverListaZadan ).start();
		try {
			Thread.sleep(20 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Stopping Server Lista Zadan");
	
		
		
//		new Thread(serverMagazyn).start();
//		try {
//			Thread.sleep(50 * 1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//	
		serverListaZadan .stop();
		//serverMagazyn .stop();
	}
}
