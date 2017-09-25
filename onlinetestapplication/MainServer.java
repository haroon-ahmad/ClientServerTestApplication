/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinetestapplication;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Haroon Ahmad
 */
public class MainServer {
      public static void main(String[] args) throws Exception
    { 
        String ImageSubServerPort = "8080";
        String AudioSubServerPort = "6666"; 
        String VideoSubServerPort = "12346";     
        
       

        ImageSubServer img_sub_server = new ImageSubServer();
        img_sub_server.start();
        AudioSubServer audio_sub_server = new AudioSubServer();
        audio_sub_server.start();       
        VideoSubServer video_sub_server = new VideoSubServer();
        video_sub_server.start();
        
        DatagramSocket ds = new DatagramSocket(63456);
            //new ClientHandler(ds).start();
        
        while(true)
        {
            byte[] b = new byte [2048];
             DatagramPacket dp = new DatagramPacket(b,b.length);
              ds.receive(dp);
            String Message= new String (dp.getData());
       
            System.out.println(Message);
            if(Message.trim().equalsIgnoreCase("image"))
            {
                byte[] data = ImageSubServerPort.getBytes();
                DatagramPacket p = new DatagramPacket(data,data.length,dp.getAddress(),dp.getPort());
                ds.send(p);
            }
            else if(Message.trim().equalsIgnoreCase("audio"))
            {
               byte[] data = AudioSubServerPort.getBytes();
               DatagramPacket p = new DatagramPacket(data,data.length,dp.getAddress(),dp.getPort());
               ds.send(p);
          }
          else if(Message.trim().equalsIgnoreCase("video")){
               
            String aalu = VideoSubServerPort;
               byte[] data = aalu.getBytes();
               DatagramPacket p = new DatagramPacket(data,data.length,dp.getAddress(),dp.getPort());
               ds.send(p);
          }
          else if(Message!=null){
               byte[] data = "ERROR".getBytes();
               DatagramPacket p = new DatagramPacket(data,data.length,dp.getAddress(),dp.getPort());
               ds.send(p);
          }
          else if(Message.contains("-answer"))
          {
              Message=Message.replace('-', '\0');
              System.out.println("Answer ="+Message);
          }
           else {
               byte[] data = "ERROR".getBytes();
               DatagramPacket p = new DatagramPacket(data,data.length,dp.getAddress(),dp.getPort());
               ds.send(p);
            }
        }
           
         
    }
    
}
