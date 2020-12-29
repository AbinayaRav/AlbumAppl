package com.AlbumApp.AlbumApplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long singerId;
    @Column(length = 200)
    @Pattern(regexp = "^[\\w ]*$", message = "Enter a valid name")
    @NotEmpty(message = "Name field can't be empty")
    private String singerName;
    @Min(value = 1, message = "Enter a valid age")
    @Max(value = 110, message = "Enter a valid age")
    private int age;
    @Column(nullable = false, length = 200)
    @Pattern(regexp = "^[\\w\\s]*$", message = "Enter a valid country")
    @NotEmpty(message = "Country field can't be empty")
    private String country;
    private Gender gender;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Enter a valid Debut Date")
    private Date debut;
    @Min(value = 0, message = "Invalid count")
    @Max(value = 1000000, message = "Invalid Count")
    private int awardsReceived;
    @NotEmpty(message = "Hobby field can't be empty")
    private String hobby;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinTable(name = "Album_Singer", joinColumns = {@JoinColumn(name = "singerId")}, inverseJoinColumns = {@JoinColumn(name = "albumId")})
    @JsonBackReference
    private Set<Album> listOfAlbums;

    public enum Gender {
        M, F
    }

    public Singer() {
    }

    public Singer(long singerId, String singerName, @Min(value = 1, message = "Enter a valid age") @Max(value = 110, message = "Enter a valid age") int age, @Pattern(regexp = "^[\\w\\s]*$", message = "Enter a valid country") @NotEmpty(message = "Country field can't be empty") String country, Gender gender, @NotNull(message = "Enter a valid Debut Date") Date debut, @Min(value = 0, message = "Invalid count") @Max(value = 1000000, message = "Invalid Count") int awardsReceived, @NotEmpty(message = "Hobby field can't be empty") String hobby) {
        this.singerId = singerId;
        this.singerName = singerName;
        this.age = age;
        this.country = country;
        this.gender = gender;
        this.debut = debut;
        this.awardsReceived = awardsReceived;
        this.hobby = hobby;
    }

    public long getSingerId() {
        return singerId;
    }

    public void setSingerId(long singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public int getAwardsReceived() {
        return awardsReceived;
    }

    public void setAwardsReceived(int awardsReceived) {
        this.awardsReceived = awardsReceived;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }


    public Set<Album> getListOfAlbums() {
        return listOfAlbums;
    }

    public void setListOfAlbums(Set<Album> listOfAlbums) {
        this.listOfAlbums = listOfAlbums;
    }

    @Override
    public String toString() {
        return singerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Singer)) return false;
        Singer singer = (Singer) o;
        return getSingerId() == singer.getSingerId() && getAge() == singer.getAge() && getAwardsReceived() == singer.getAwardsReceived() && getSingerName().equals(singer.getSingerName()) && getCountry().equals(singer.getCountry()) && getGender() == singer.getGender() && getDebut().equals(singer.getDebut()) && getHobby().equals(singer.getHobby()) && getListOfAlbums().equals(singer.getListOfAlbums());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSingerId(), getSingerName(), getAge(), getCountry(), getGender(), getDebut(), getAwardsReceived(), getHobby());
    }
}
