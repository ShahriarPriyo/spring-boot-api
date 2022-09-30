package com.springapi.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    @NotEmpty
    private String title;

    @NotNull
    @NotBlank
    @NotEmpty
    private String description;

    @ManyToOne
    private Users author;

    private LocalDateTime created_At=LocalDateTime.now();

    public Story(int id,String title,String description){
        this.id=id;
        this.title=title;
        this.description=description;
    }

    @Override
    public int hashCode() {
        int hash=7;
        return id*hash + title.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        Story other = (Story) obj;
        if (other.id == this.id&& Objects.equals(other.title, this.title)) return true;
        return false;
    }

}
