package com.AlbumApp.AlbumApplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long songId;
    @Column(length = 200)
    @Pattern(regexp = "^[\\w-. ]*$", message = "Song title should be alphanumeric. Can have -,. and spaces")
    private String title;
    @Column(nullable = false, scale = 2)
    private BigDecimal trackLength;
    @Pattern(regexp = "^[\\w-. ]*$", message = "Please enter a valid Genre")
    private String style;
    @NotNull(message = "Enter a valid Billboard")
    private double billBoard;
    @Column(length = 200, nullable = false)
    @NotEmpty(message = "Enter a valid certification")
    private String certification;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "albumId")
    @JsonBackReference
    private Album album;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "Singer_Id")
    private Singer singer;

    public Song() {

    }

    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public BigDecimal getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(BigDecimal trackLength) {
        this.trackLength = trackLength;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public double getBillBoard() {
        return billBoard;
    }

    public void setBillBoard(double billBoard) {
        this.billBoard = billBoard;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return getSongId() == song.getSongId() && Double.compare(song.getBillBoard(), getBillBoard()) == 0 && getTitle().equals(song.getTitle()) && getTrackLength().equals(song.getTrackLength()) && getStyle().equals(song.getStyle()) && getCertification().equals(song.getCertification()) && getAlbum().equals(song.getAlbum()) && getSinger().equals(song.getSinger());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSongId(), getTitle(), getTrackLength(), getStyle(), getBillBoard(), getCertification(), getAlbum(), getSinger());
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", title='" + title + '\'' +
                ", trackLength=" + trackLength +
                ", style='" + style + '\'' +
                ", billBoard=" + billBoard +
                ", certification='" + certification + '\'' +
                ", album=" + album +
                ", singer=" + singer +
                '}';
    }
}
