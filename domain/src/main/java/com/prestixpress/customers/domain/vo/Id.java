package com.prestixpress.customers.domain.vo;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
public class Id {
    private final UUID id;
    private Id(UUID id) {
        this.id = id;
    }

    public static Id conId(String id) {
        return new Id(UUID.fromString(id));
    }

    public static Id sinId() {
        return new Id(UUID.randomUUID());
    }
}
