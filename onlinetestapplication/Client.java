/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinetestapplication;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import static java.lang.System.exit;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.media.Player;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 *
 * @author Haroon Ahmad
 */
public class Client
{
   
    public static void main(String[]args) throws Exception
    {
        String request="video";
    //communicating with main server
        Socket socket = new Socket("localhost", 12345);
        PrintStream PS = new PrintStream(socket.getOutputStream());
        PS.println(request);  
          InputStreamReader IR = new InputStreamReader(socket.getInputStream());
          BufferedReader BR = new BufferedReader(IR);
          String Message = BR.readLine();
          System.out.println(Message);
          socket.close();
          
          int socknum = Integer.parseInt(Message);
     //communicating with image server
     String TO_SAVE_PATH =  "C:\\Users\\Haroon Ahmad\\Documents\\NetBeansProjects\\onlinetestapplication\\src\\onlinetestapplication\\";
     if("image".equals(request)){
         TO_SAVE_PATH += "test.jpg";
     }
     else if("audio".equals(request)){
         TO_SAVE_PATH += "test.wav";
     }
     else if("video".equals(request)){
         TO_SAVE_PATH += "test.mp4";
     }
     else{
         exit(0);
     }
   //  String TO_SAVE_PATH =  "C:\\Users\\zaing\\Desktop\\client\\test.jpg";
       Socket sock = new Socket("127.0.0.1", socknum);
       
        byte[] mybytearray = new byte[1024];
        if(request.equals("image"))
        {
            InputStream is = sock.getInputStream();
            BufferedImage img=ImageIO.read(ImageIO.createImageInputStream(is));
            JFrame frame = new JFrame();
            frame.getContentPane().add(new JLabel(new ImageIcon(img)));
            frame.pack();
            frame.setVisible(true);
        }
        else if(request.equals("audio"))
        {
            byte[] data = null;
            ObjectInputStream oInputStream = new ObjectInputStream(sock.getInputStream());
            data = (byte[])oInputStream.readObject();
            FileOutputStream fOutputStream = new FileOutputStream(TO_SAVE_PATH);
            fOutputStream.write(data);
        }
        else if(request.equals("video"))
        {
            FileOutputStream fos = new FileOutputStream(TO_SAVE_PATH);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            InputStream is=sock.getInputStream();
            int bytesRead = is.read(mybytearray, 0, mybytearray.length);
            while(bytesRead != -1 )
            {
                bos.write(mybytearray, 0, bytesRead);
                bytesRead = is.read(mybytearray, 0, mybytearray.length);
            }
            bos.close();
        }
        
        
        sock.close();
      
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+TO_SAVE_PATH);
        
    }
}
