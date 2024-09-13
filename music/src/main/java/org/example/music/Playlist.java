package org.example.music;

import java.util.ArrayList;

public class Playlist extends ArrayList<Song> {

    public Song atSecond(int second){
        if(second < 0){
            throw new IndexOutOfBoundsException("czas jest ujemny" + second);
        } else if(second == 0){

        }
        int sum=0;
        for(Song song: this){
            sum += song.getDuration();
            if(sum >= second){
                return song;
            }
        }
        throw new IndexOutOfBoundsException("czas większy niż czas trwania piosenek");
    }
}
