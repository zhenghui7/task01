package myapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

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
            // InputStreamReader isr = new InputStreamReader(ois);
            // BufferedReader br = new BufferedReader(isr);

            OutputStream os = sock.getOutputStream();
            // DataOutputStream dos = new DataOutputStream(os);
            ObjectOutputStream oos = new ObjectOutputStream(os);


            String response = ois.readUTF();
            System.out.println(response);

            String[] serverInput = response.split(",");
            
            float [] floatsInput = new float[serverInput.length];

                for (int i = 0; i < serverInput.length; i++) {
                    floatsInput[i] = Float.parseFloat(serverInput[i]);
                }
                
                float sum = 0f;
                float mean = 0f;

                for (float f : floatsInput) {
                    sum += f;                   
                }

                mean = sum / floatsInput.length;

            System.out.println("Sum of floats: " + sum);
            System.out.println("Mean calculation: " + mean);

            // to calculate the standard deviation
            float calculationPart = 0f;

            for (float a : floatsInput) {
                float eachDiff = a - mean;
                calculationPart = calculationPart + (eachDiff * eachDiff);
            }

            float var = calculationPart / floatsInput.length;
            float standDev = (float) Math.sqrt(var);

            System.out.println("Standard deviation: " + standDev);

           String name = "Lim Zheng Hui";
           String email = "lim_z_hui@hotmail.com";

            // to return name in NRIC
            oos.writeUTF("Received name: " + name);

            // to return email add
            oos.writeUTF("Received email: " + email);

            // to return mean calculated
            oos.writeFloat(mean);

            // to return standard deviation
            oos.writeFloat(standDev);

        
            
            is.close();
            ois.close();
            sock.close();

            os.close();
            oos.close();

        }catch(UnknownHostException e){
            e.printStackTrace();
        }catch(NumberFormatException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    
}

}

