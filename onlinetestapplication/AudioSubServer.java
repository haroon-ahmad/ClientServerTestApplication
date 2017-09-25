/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinetestapplication;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Haroon Ahmad
 */
public class AudioSubServer extends Thread
{
    HashMap<String,String> hmap=new HashMap<String,String>();
    @Override
    public void run()
    {
        int AudioSubServerPort = 6666;
        hmap.put("math","C:\\Users\\Haroon Ahmad\\Documents\\NetBeansProjects\\onlinetestapplication\\src\\onlinetestapplication\\math.mp3");
        hmap.put("biology","C:\\Users\\Haroon Ahmad\\Documents\\NetBeansProjects\\onlinetestapplication\\src\\onlinetestapplication\\biology.mp3");
         hmap.put("physics","C:\\Users\\Haroon Ahmad\\Documents\\NetBeansProjects\\onlinetestapplication\\src\\onlinetestapplication\\physics.mp3");

          ServerSocket server_socket;
        try {
            server_socket = new ServerSocket(AudioSubServerPort);
            while (true)
            {
                try (Socket socket = server_socket.accept())
                {
                    InputStreamReader inputstreamreader = new InputStreamReader(socket.getInputStream());
                    BufferedReader bufferreader = new BufferedReader(inputstreamreader);
                    String subject = bufferreader.readLine();
                    System.out.println(subject);
                    String path=hmap.get(subject.trim().toLowerCase());
                    
                  FileInputStream file_input_stream = new FileInputStream(path);
                  byte[] buffer = new byte[file_input_stream.available()];
                  file_input_stream.read(buffer);
                  OutputStream outputstream = socket.getOutputStream();
                  ObjectOutputStream object_output_stream = new ObjectOutputStream(outputstream);
                  object_output_stream.writeObject(buffer); 
                  object_output_stream.close();
                  outputstream.flush();     
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error: "+e);
        }
       
        super.run(); //To change body of generated methods, choose Tools | Templates.
    }
}
    

