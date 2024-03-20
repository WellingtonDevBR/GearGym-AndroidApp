package com.gamezzar.geargymtest.database.entities;

import androidx.room.Entity;

@Entity(primaryKeys = {"EquipmentId", "BodyPartId"})
public class EquipmentBodyPart {
    public Integer EquipmentId;
    public Integer BodyPartId;
}