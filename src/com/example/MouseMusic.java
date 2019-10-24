package com.example;

import org.jfugue.player.Player;

import java.awt.*;


public class MouseMusic implements Runnable{
    public Object lock = new Object();

    public MouseMusic(){

    }
    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                while (MouseChecker.m) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            Player player = new Player();

            // Read screen Dimensions and Mouse Position
            Point loc = MouseInfo.getPointerInfo().getLocation();
            // We need to know where we are at the screen
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            double width = screenSize.getWidth();
            double height = screenSize.getHeight();

            // Pitch = higher pitch the higher user click on the sreen
            //        = lower pitch the lower user click on the screen
            // Volume = right side of the screen is for higher volumes
            //          left side of the screen is for lower volumes
            int pitch = 127 - (int)(loc.y*127/height);
            int volume = (int)(loc.x*16383/width);


           // Pattern pattern = new Pattern("X[Volume]=" + volume + " [" + pitch + "]");
            // Since I am using jFugue v5 I, remove the brackets around note numbers
            //Brackets should be used in cases where the contents of t
            // he brackets is a string representation of a value that needs to be looked up
            // (e.g., I[Piano], T[Allegro])
            player.play("XVolume =" + volume + "[Violin] " + pitch + "[Violin]");

        }
    }
}
