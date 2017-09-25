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
public class VideoSubServer extends Thread{

    HashMap<String,String> hmap=new HashMap<String,String>();
    @Override
    public void run()
    {
        int VideoSubServerPort = 12346;    
        hmap.put("math","C:\\Users\\Haroon Ahmad\\Documents\\NetBeansProjects\\onlinetestapplication\\src\\onlinetestapplication\\math.mp4");
        hmap.put("biology","C:\\Users\\Haroon Ahmad\\Documents\\NetBeansProjects\\onlinetestapplication\\src\\onlinetestapplication\\biology.flv");
        hmap.put("physics","C:\\Users\\Haroon Ahmad\\Documents\\NetBeansProjects\\onlinetestapplication\\src\\onlinetestapplication\\physics.mp4");

        ServerSocket server_socket;
        int size = 1024;
        byte[] bytes = new byte[size];
        try 
        {
            server_socket = new ServerSocket(VideoSubServerPort);
            while (true)
            {
                try (Socket socket = server_socket.accept()) 
                {
                    InputStreamReader inputstreamreader = new InputStreamReader(socket.getInputStream());
                    BufferedReader bufferreader = new BufferedReader(inputstreamreader);
                    String subject = bufferreader.readLine();
                    System.out.println(subject);
                    String path=hmap.get(subject.trim().toLowerCase());
                    
                    OutputStream outputstream = socket.getOutputStream();
                    BufferedInputStream bufferinputstream = new BufferedInputStream(new FileInputStream(path));
                    while (true) 
                    {
                        int a = bufferinputstream.read(bytes, 0, bytes.length);
                        if (a <=0) 
                        {
                            break;
                        }
                        outputstream.write(bytes, 0, bytes.length);
                        outputstream.flush();
                    }   
                }
            }
        }
        catch (IOException e) 
        {
            System.out.println("Error: "+e);
        }
       
        super.run(); 
    }
}
