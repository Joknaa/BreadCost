import java.io.*;
import java.net.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Dumpy {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		int res=0;
		String ress;
		String op;

		@SuppressWarnings("resource")
		ServerSocket conn = new ServerSocket(7017);
		while(true) {
			Socket comm = conn.accept();
			BufferedReader entreeDepuisClient = new BufferedReader(new InputStreamReader(comm.getInputStream()));
			DataOutputStream sortieVersClient = new DataOutputStream(comm.getOutputStream());
			op = entreeDepuisClient.readLine();

			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("js");
			Object result = engine.eval(op);

			ress = result +"";
			ress = ress+'\n';

			//System.out.println(ress);
			sortieVersClient.writeBytes(ress);
		}
	}

}
