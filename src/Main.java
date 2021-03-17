import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import processing.core.PApplet;

public class Main extends PApplet{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main(Main.class.getName());
	}
	
	public int posX, posY;
	public int r,g,b;

	
	public void settings(){
		size(500,500);
	}
	
	public void setup() {
		
		posX = 255;
		posY = 255;
		r = 0;
		g = 0;
		b = 255;
			
			new Thread(

				() -> {
						{
							try {
								ServerSocket server = new ServerSocket(5000);

								
								System.out.println("Esperando...");
								Socket socket = server.accept();
								System.out.println("Conexión aceptada");

								// Importar de java.io
								InputStream is = socket.getInputStream();

								InputStreamReader isr = new InputStreamReader(is);

								BufferedReader reader = new BufferedReader(isr);

								while (true) {
									String line = reader.readLine();
									System.out.println(line);

									switch (line) {
									case "UP":
										posY -= 10;
										break;

									case "DOWN":
										posY += 10;
										break;

									case "LEFT":
										posX -= 10;
										break;

									case "RIGHT":
										posX += 10;
										break;

									case "COLOR":
										r = (int) random(0,255);
										g = (int) random(0,255);
										b = (int) random(0,255);
										break;

									default:
										break;
									}
								}

							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}

			).start();

	}

		
	
	
	public void draw() {
		background(8);
		fill(r, g, b);
		ellipse(posX,posY,50,50);
		
	}
}
