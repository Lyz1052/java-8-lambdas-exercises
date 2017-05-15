package com.insightfullogic.java8.exercises.chapter3;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.exercises.Exercises;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Question1 {
    public static int addUp(Stream<Integer> numbers) {
        return Exercises.replaceThisWithSolution();
    }

    static List<String> getNamesAndOrigins(List<Artist> artists){
        return artists.stream().flatMap(artist->Stream.of(artist.getName(),artist.getNationality())).collect(Collectors.toList());
    }

    static List<Album> getAlbumsWithAtMostThreeTracks(List<Album> albums){
        return albums.stream().filter(album -> album.getTrackList().size()<=3).collect(Collectors.toList());
    }
}
