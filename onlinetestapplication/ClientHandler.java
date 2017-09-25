/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinetestapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Haroon Ahmad
 */
public class ClientHandler extends Thread{
    DatagramSocket socket;
    ClientHandler(DatagramSocket socket)
    {
        this.socket=socket;
    }

    @Override
    public void run() 
    {
        String ImageSubServerPort = "8080";
        String AudioSubServerPort = "6666"; 
        String VideoSubServerPort = "12346";     
        
        
        while(true)
        {
            byte[] bytes = new byte [2048];
             DatagramPacket packet = new DatagramPacket(bytes,bytes.length);
            try {
                socket.receive(packet);
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            String type= new String (packet.getData());
       
            System.out.println(type);
            if(type.trim().equalsIgnoreCase("image"))
            {
                byte[] data = ImageSubServerPort.getBytes();
                DatagramPacket packetout = new DatagramPacket(data,data.length,packet.getAddress(),packet.getPort());
                try {
                    socket.send(packetout);
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(type.trim().equalsIgnoreCase("audio"))
            {
                byte[] data = AudioSubServerPort.getBytes();
               DatagramPacket packetout = new DatagramPacket(data,data.length,packet.getAddress(),packet.getPort());
                try {
                    socket.send(packetout);
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
          }
          else if(type.trim().equalsIgnoreCase("video")){
            
               byte[] data = VideoSubServerPort.getBytes();
               DatagramPacket packetout = new DatagramPacket(data,data.length,packet.getAddress(),packet.getPort());
                try {
                    socket.send(packetout);
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          else{
               byte[] data = "Something went Wrong".getBytes();
               DatagramPacket packetout = new DatagramPacket(data,data.length,packet.getAddress(),packet.getPort());
                try {
                    socket.send(packetout);
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
}
