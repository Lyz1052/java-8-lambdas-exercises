package com.insightfullogic.java8.streams;

import com.insightfullogic.java8.music.Album;
import com.insightfullogic.java8.music.Artist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Decisions {

    public static class Imperative {
        // BEGIN origins_of_bands_meth_imp
        public Set<String> originsOfBands(Album album) {
            Set<String> nationalities = new HashSet<>();
            for (Artist artist : album.getMusicianList()) {
                if (artist.getName().startsWith("The")) {
                    String nationality = artist.getNationality();
                    nationalities.add(nationality);
                }
            }
            return nationalities;
        }
        // END origins_of_bands_meth_imp
    }

    public Set<String> originsOfBands(Album album) {
        // BEGIN origins_of_bands
Set<String> origins = album.getMusicians()
                           .filter(artist -> artist.getName().startsWith("The"))
                           .map(artist -> artist.getNationality())
                           .collect(toSet());
        // END origins_of_bands
        return origins;
    }

    public Set<String> originsOfBandsMisuse(Album album) {
        // BEGIN misuse
List<Artist> musicians = album.getMusicians()
                              .collect(toList());

List<Artist> bands = musicians.stream()
                              .filter(artist -> artist.getName().startsWith("The"))
                              .collect(toList());

Set<String> origins = bands.stream()
                           .map(artist -> artist.getNationality())
                           .collect(toSet());
        // END misuse
        return origins;
    }

}