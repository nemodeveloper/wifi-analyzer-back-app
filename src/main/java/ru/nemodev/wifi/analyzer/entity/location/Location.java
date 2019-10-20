package ru.nemodev.wifi.analyzer.entity.location;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nemodev.wifi.analyzer.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "LOCATIONS")
public class Location extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

}
