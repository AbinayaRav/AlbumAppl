package com.AlbumApp.AlbumApplication.repository;

import com.AlbumApp.AlbumApplication.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SingerRepository extends JpaRepository<Singer, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM album_singer WHERE singer_id = ?", nativeQuery = true)
    void deleteAlbumSingerAssociation(Long id);
}
