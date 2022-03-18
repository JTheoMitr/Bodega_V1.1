package android.example.bodegaversionone

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Item(val data: Product) {

    // creating an SQL table for Room
    @Entity(tableName = "item_table")
    // adding keys to look up items
    private class Item(
                        @PrimaryKey(autoGenerate = true) val title: String,
                        @ColumnInfo(name = "category") val category: String
                        )
}