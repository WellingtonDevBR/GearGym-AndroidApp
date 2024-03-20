package com.gamezzar.geargymtest.database.models;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.gamezzar.geargymtest.database.entities.BodyPart;
import com.gamezzar.geargymtest.database.entities.Equipment;
import com.gamezzar.geargymtest.database.entities.EquipmentBodyPart;
import com.gamezzar.geargymtest.database.entities.Video;

import java.util.List;

public class EquipmentWithVideosAndBodyPart {
    @Embedded
    public Equipment equipment;

    @Relation(entity = Video.class, parentColumn = "UID", entityColumn = "EquipmentId")
    public List<Video> videos;

    @Relation(entity = BodyPart.class, parentColumn = "UID", entityColumn = "BodyPartId", associateBy = @Junction(value = EquipmentBodyPart.class, parentColumn = "EquipmentId", entityColumn = "BodyPartId"))
    public List<BodyPart> bodyParts;
}