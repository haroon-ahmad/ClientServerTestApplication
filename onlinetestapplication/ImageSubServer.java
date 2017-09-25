/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinetestapplication;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Haroon Ahmad
 */
public class ImageSubServer extends Thread{

    int width = 800;    //width of the image
    int height = 600;   //height of the image
    String file_path = "C:\\Users\\Haroon Ahmad\\Documents\\NetBeansProjects\\onlinetestapplication\\src\\onlinetestapplication\\test.jpg";//image file path
    HashMap<String,String> hmap=new HashMap<String,String>();
    int ImageSubServerPort = 8080;
    @Override
    public void run() 
    {    
        File image_file = null;
        BufferedImage image = null;
        ServerSocket server_socket;
        int length = 1024;
        byte[] bytes = new byte[length];
         hmap.put("math","C:\\Users\\Haroon Ahmad\\Documents\\NetBeansProjects\\onlinetestapplication\\src\\onlinetestapplication\\math.jpg");
         hmap.put("biology","C:\\Users\\Haroon Ahmad\\Documents\\NetBeansProjects\\onlinetestapplication\\src\\onlinetestapplication\\Biology.jpg");
         hmap.put("physics","C:\\Users\\Haroon Ahmad\\Documents\\NetBeansProjects\\onlinetestapplication\\src\\onlinetestapplication\\physics.jpg");
        try 
        {
            server_socket= new ServerSocket(ImageSubServerPort);
            while (true) 
            {
                try (Socket socket = server_socket.accept())
                {
                    InputStreamReader inputstreamreader = new InputStreamReader(socket.getInputStream());
                    BufferedReader bufferreader = new BufferedReader(inputstreamreader);
                    String subject = bufferreader.readLine();
                    System.out.println(subject);
                    String path=hmap.get(subject.trim().toLowerCase());
                    try{
                           image_file = new File(path); 
                           image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);//initialize a buffered image to send to client
                            image = ImageIO.read(image_file);//Read Image
                            System.out.println("Reading complete.");
                        }
                    catch(IOException e)
                    {
                        System.out.println("Error: "+e);
                    }
                    OutputStream outputstream = socket.getOutputStream();
                    ImageIO.write(image,"JPG",outputstream);
                    outputstream.flush();
                } 
                catch (IOException e) 
                {
                    System.out.println("Error: "+e);
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