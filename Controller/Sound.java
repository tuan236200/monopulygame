package Controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Sound {
    private boolean musicSwitch = false;
    private static Clip clip;
    private FloatControl fc;
    private float currentVolume = -10;
    private float previousVolume = 0;

    public Sound() {
        String path = "Sound/BGM.wav";
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (UnsupportedAudioFileException | IOException u) {
            u.printStackTrace();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void muteBGM() {
        if (!musicSwitch) {
            previousVolume = currentVolume;
            currentVolume = -80;
            fc.setValue(currentVolume);
            musicSwitch = true;
        } else if (musicSwitch) {
            currentVolume = previousVolume;
            fc.setValue(currentVolume);
            musicSwitch = false;
        }
    }

    public void playBGM() {
        clip.start();
        fc.setValue(-10);
        clip.loop(-1);
    }




    public void btnSFX(String path){
        Thread thread = new Thread(() -> {
            try {
                File click = new File(path);
                Clip clip = AudioSystem.getClip();
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(click);
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void setMusicSwitch(boolean musicSwitch) {
        this.musicSwitch = musicSwitch;
    }

    public boolean getMusicSwitch() {
        return musicSwitch;
    }
}
