import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws Exception {
		String a;
		String b;
		String op;
		String recu;
		Scanner sc = new Scanner(System.in);

		System.out.print("Entrer l'operation : \t");
		op = sc.nextLine();

		Socket sok = new Socket("localhost", 7017);
		PrintWriter sortieVersserv  = new PrintWriter(sok.getOutputStream());
		
		BufferedReader entreeDepuisserv = new BufferedReader(new InputStreamReader(sok.getInputStream()));

		sortieVersserv.println(op);
		sortieVersserv.flush();
		
		recu = entreeDepuisserv.readLine();
		System.out.println("SERVER : " + op + " = " + recu);
		sok.close();
		
		
	}

}

