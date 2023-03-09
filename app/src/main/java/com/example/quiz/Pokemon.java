package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "pokemons")
public class Pokemon implements Comparable{
        @PrimaryKey(autoGenerate = true)
        @NonNull
        private int ID;
        private String name;
        @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
        private byte[] bitmap;
        @Ignore
        private boolean marked_for_delete = false;
        public Pokemon(String name, byte[] bitmap) {
            this.name = name;
            this.bitmap = bitmap;
        }

        public boolean isMarked_for_delete() {
            return marked_for_delete;
        }

        public void setMarked_for_delete(boolean marked_for_delete) {
            this.marked_for_delete = marked_for_delete;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public byte[] getBitmap() {
            return bitmap;
        }

        public void setBitmap(byte[] bitmap) {
            this.bitmap = bitmap;
        }

        @Override
        public int compareTo(Object o) {
            Pokemon a = (Pokemon) o;
            return name.compareTo(a.getName());
        }
}

