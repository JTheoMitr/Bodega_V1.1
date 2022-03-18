package android.example.bodegaversionone

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Product(

    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)
{
    @PrimaryKey(autoGenerate = true)
    var pid: Int? = null
}
