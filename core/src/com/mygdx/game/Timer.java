package com.mygdx.game;

import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;

import java.sql.Time;

public class Timer {

    int milliElapsed = 0;
    int secondsElapsed = 0;
    int minutesElapsed = 0;
    boolean running;

    public Timer(boolean run){
        running = run;
    }
    public void tick(){
        if (running == true){
            milliElapsed++;
        }
        if (milliElapsed == 60){
            secondsElapsed++;
            milliElapsed = 0;
        }
        if (secondsElapsed == 60){
            minutesElapsed++;
            secondsElapsed = 0;
        }
    }
    public void start(){
        running = true;
    }
    public void stop(){
        running = false;
    }
    public void restart(){
        secondsElapsed = 0;
        minutesElapsed = 0;
    }
    public void setTimeElapsed(int minsElapsed, int secElapsed){
        minutesElapsed = minsElapsed;
        secondsElapsed = secElapsed;
    }
    public String getTimeElapsed(){
        return String.format("%02d",minutesElapsed) + ":" + String.format("%02d",secondsElapsed);
    }
    public boolean running(){
        return running;
    }
    public int getTotalTime(){
        return (minutesElapsed * 60) + secondsElapsed;
    }
}
