package dev.franciscohernandez.lamptracker.service.enumerables;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum LampSortField {
    MODEL("model"),
    TYPE("type"),
    STATUS("status");

    private final String databaseFieldName;
}