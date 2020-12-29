package com.AlbumApp.AlbumApplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long albumId;
    @Column(length = 100)
    @NotEmpty(message = "Album Name field can never be empty")
    @Pattern(regexp = "^[a-zA-Z0-9-.&, ]*$", message = "Album name should be alphanumeric. Can have spaces, . and -")
    private String albumName;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Enter a valid release Date")
    private Date releaseDate;
    @NotNull(message = "required Valid album length")
    @Column(scale = 2)
    private BigDecimal albumLength;
    @NotEmpty(message = "Enter a valid record label")
    @Pattern(regexp = "^[a-zA-Z0-9-.&, ]*$", message = "Record Label should be alphanumeric. Can have spaces,.& and -")
    private String recordLabel;
    @Column(nullable = false, name = "Album_Price", scale = 2)
    private BigDecimal price;
    @Min(0)
    @Max(value = 10, message = "Enter a value between 1 and 10")
    private int rating;
    @NotNull
    @Pattern(regexp = "^[\\w\\s.-]*$", message = "Producer Name should be alphanumeric.")
    private String producer;
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Song> listOfSongs;
    @ManyToMany(mappedBy = "listOfAlbums", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JsonIgnore
    private Set<Singer> listOfSingers;


    public Album() {
    }

    public Album(long albumId, String albumName, Date releaseDate, BigDecimal albumLength, String recordLabel, BigDecimal price, int rating, String producer) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.releaseDate = releaseDate;
        this.albumLength = albumLength;
        this.recordLabel = recordLabel;
        this.price = price;
        this.rating = rating;
        this.producer = producer;
    }

/*    @PreRemove
    public void sendMessageWhenRemovingAlbumWithSongs() {
        if (!this.listOfSongs.isEmpty()) {
            throw new RuntimeException("Cannot delete Album that has songs!!!!!!");
        }
    }*/

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public BigDecimal getAlbumLength() {
        return albumLength;
    }

    public void setAlbumLength(BigDecimal albumLength) {
        this.albumLength = albumLength;
    }

    public String getRecordLabel() {
        return recordLabel;
    }

    public void setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public List<Song> getListOfSongs() {
        return listOfSongs;
    }

    public void setListOfSongs(List<Song> songs) {
        songs.forEach(song -> song.setAlbum(this));
        this.listOfSongs = songs;

    }


    @Override
    public String toString() {
        return albumName;

    }


    public Set<Singer> getListOfSingers() {
        return listOfSingers;
    }

    public void setListOfSingers(Set<Singer> listOfSingers) {
        listOfSingers.stream().forEach(singer -> singer.getListOfAlbums().add(this)); //Set doesn't allow duplicates, so no "already exist" needed
        this.listOfSingers = listOfSingers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;
        Album album = (Album) o;
        return getAlbumId() == album.getAlbumId() && getRating() == album.getRating() && getAlbumName().equals(album.getAlbumName()) && getReleaseDate().equals(album.getReleaseDate()) && getAlbumLength().equals(album.getAlbumLength()) && getRecordLabel().equals(album.getRecordLabel()) && getPrice().equals(album.getPrice()) && getProducer().equals(album.getProducer()) && getListOfSongs().equals(album.getListOfSongs()) && getListOfSingers().equals(album.getListOfSingers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAlbumId(), getAlbumName(), getReleaseDate(), getAlbumLength(), getRecordLabel(), getPrice(), getRating(), getProducer(), getListOfSongs(), getListOfSingers());
    }
}