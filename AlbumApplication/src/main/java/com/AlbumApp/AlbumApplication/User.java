package com.AlbumApp.AlbumApplication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User  {
    @Id
    private Long id;
    @Column(unique = true)
    @NotNull
    private String userName;
    @NotNull
    private String password;

    public User(Long id, @NotNull String userName, @NotNull String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
}
