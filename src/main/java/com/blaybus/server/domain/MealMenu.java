package com.blaybus.server.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealMenu {

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_type")
    private Meal type;

    @Column(name = "meal_menu", columnDefinition = "TEXT")
    private String menu;
}
