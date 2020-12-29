package com.AlbumApp.AlbumApplication.repository;

import com.AlbumApp.AlbumApplication.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    @Query(value = "SELECT * FROM song WHERE song_id=:custom", nativeQuery = true)
    Song Q2(@Param(value = "custom") String s);

    List<Song> findByAlbumAlbumId(Long albumId);
    List<Song> findBySingerSingerId(Long singerId);
    Optional<Song> findBySongIdAndSingerSingerId(Long songId, Long singerId);

}
