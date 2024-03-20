package com.gamezzar.geargymtest.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;

@Entity(primaryKeys = {"EquipmentId", "BodyPartId"},
        indices = {
                @Index(value = "EquipmentId"),
                @Index(value = "BodyPartId")
        })
public class EquipmentBodyPart {
    @NonNull
    public Integer EquipmentId;
    @NonNull
    public Integer BodyPartId;
}