package com.example;

import org.jfugue.*;

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

            player.play("X[Volume]=" + volume + " [" + pitch + "]");

        }
    }
}
