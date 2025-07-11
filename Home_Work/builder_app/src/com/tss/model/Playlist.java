package com.tss.model;

public class Playlist {
    private final String playlistName;     
    private final String genre;            
    private final String description;      
    private final int numberOfSongs;       
    private final boolean isDownloaded;    

   
    private Playlist(Builder builder) {
        this.playlistName = builder.playlistName;
        this.genre = builder.genre;
        this.description = builder.description;
        this.numberOfSongs = builder.numberOfSongs;
        this.isDownloaded = builder.isDownloaded;
    }

    
    public void showDetails() {
        System.out.println("Playlist: " + playlistName);
        if (genre != null) System.out.println("Genre: " + genre);
        if (description != null) System.out.println("Description: " + description);
        if (numberOfSongs > 0) System.out.println("Songs: " + numberOfSongs);
        System.out.println("Downloaded: " + (isDownloaded ? "Yes" : "No"));
    }

   
    public static class Builder {
        private final String playlistName;
        private String genre;
        private String description;
        private int numberOfSongs;
        private boolean isDownloaded;

        public Builder(String playlistName) {
            this.playlistName = playlistName;
        }

        public Builder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder numberOfSongs(int count) {
            if (count < 0) {
                throw new IllegalArgumentException("Number of songs cannot be negative.");
            }
            this.numberOfSongs = count;
            return this;
        }

        public Builder isDownloaded(boolean downloaded) {
            this.isDownloaded = downloaded;
            return this;
        }

        public Playlist build() {
            return new Playlist(this);
        }
    }
}
