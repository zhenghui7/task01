package myapp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

//entry point

public static void main(String[] args) {
    
    String[] arrSplit = args[0].split(":");
        System.out.println(arrSplit[0]);
        System.out.println(arrSplit[1]);
    
        try{
            //client side to request connection to matching port
            Socket sock = new Socket(arrSplit[0], Integer.parseInt(arrSplit[1]));
            
            InputStream is = sock.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            InputStreamReader isr = new InputStreamReader(ois);
            BufferedReader br = new BufferedReader(isr);

            OutputStream os = sock.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            ObjectOutputStream oos = new ObjectOutputStream(dos);


            String line;

            // while ((line = br.readLine()) != null) {
            //     String[] cols = line.split(",");
            //     System.out.println();
            
            // }
             
        //

        // to return name in NRIC
        dos.writeUTF("Received name: Lim Zheng Hui");

        // to return email add
        dos.writeUTF("Received email: lim_z_hui@hotmail.com");

        // to return mean calculated
        dos.writeUTF("return calculated value");

        // to return standard deviation
        dos.writeUTF("asdfas");

      
            
            is.close();
            os.close();
            sock.close();

        }catch(UnknownHostException e){
            e.printStackTrace();
        }catch(NumberFormatException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

