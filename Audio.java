package com.company;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Created by Administrator on 2017-06-21.
 */
public class Audio {


    public void play(String s) {
        for (int i = 0; i < 1; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        File file = new File(s);
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(file));
                        clip.start();
                        Thread.sleep(clip.getMicrosecondLength());

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }.start();
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}