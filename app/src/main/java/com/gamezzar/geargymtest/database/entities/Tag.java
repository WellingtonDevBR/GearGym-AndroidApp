package com.gamezzar.geargymtest.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Equipment.class,
        parentColumns = "UID",
        childColumns = "EquipmentId",
        onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = "EquipmentId")})
public class Tag {
    @PrimaryKey(autoGenerate = true)
    public Integer UID;
    public Integer EquipmentId;
    public String Name;
}
