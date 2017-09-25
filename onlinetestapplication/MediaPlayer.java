package onlinetestapplication;

import java.awt.BorderLayout;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
 
/**
*
* @author BUDDHIMA
*/
 
public class MediaPlayer extends javax.swing.JPanel {
 
public MediaPlayer(URL mediauUrl) {
 
initComponents();
 JFrame mediaTest = new JFrame( "Movie Player" );
 
 mediaTest.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
setLayout(new BorderLayout());
 
try {
 
Player mediaPlayer=Manager.createRealizedPlayer(new MediaLocator(mediauUrl));
 
Component video=mediaPlayer.getVisualComponent();
 
Component control=mediaPlayer.getControlPanelComponent();
 
if (video!=null) {
 
add(video, BorderLayout.CENTER);          // place the video component in the panel
 
}
 
add(control, BorderLayout.SOUTH);            // place the control in  panel
 
mediaPlayer.start();

mediaTest.add( this );
 
        mediaTest.setSize( 800, 700 ); // set the size of the player
 
        mediaTest.setLocationRelativeTo(null);
 
        mediaTest.setVisible( true );
 
} catch (Exception e) {
 
}
 
}

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

    
 
}