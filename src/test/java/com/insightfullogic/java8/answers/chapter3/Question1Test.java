package com.insightfullogic.java8.answers.chapter3;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.insightfullogic.java8.examples.chapter1.SampleData.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class Question1Test {

    @Test
    public void addsEmptyList() {
        int result = com.insightfullogic.java8.answers.chapter3.Question1.addUp(Stream.empty());
        assertEquals(0, result);
    }

    @Test
    public void addsListWithValues() {
        int result = com.insightfullogic.java8.answers.chapter3.Question1.addUp(Stream.of(1, 3, -2));
        assertEquals(2, result);
    }

    @Test
    public void extractsNamesAndOriginsOfArtists() {
//        List<String> namesAndOrigins = com.insightfullogic.java8.answers.chapter3.Question1.getNamesAndOrigins(SampleData.getThreeArtists());
        List<String> namesAndOrigins = getNamesAndOrigins(SampleData.getThreeArtists());
        assertEquals(asList("John Coltrane", "US", "John Lennon", "UK", "The Beatles", "UK"), namesAndOrigins);
    }

    static List<String> getNamesAndOrigins(List<Artist> artists){
        return artists.stream().flatMap(artist->Stream.of(artist.getName(),artist.getNationality())).collect(Collectors.toList());
    }

    static List<Album> getAlbumsWithAtMostThreeTracks(List<Album> albums){
        return albums.stream().filter(album -> album.getTrackList().size()<=3).collect(Collectors.toList());
    }
    @Test
    public void findsShortAlbums() {
        List<Album> input = asList(manyTrackAlbum, sampleShortAlbum, aLoveSupreme);
//        List<Album> result = Question1.getAlbumsWithAtMostThreeTracks(input);
        List<Album> result = getAlbumsWithAtMostThreeTracks(input);
        assertEquals(asList(sampleShortAlbum, aLoveSupreme), result);
    }

}
