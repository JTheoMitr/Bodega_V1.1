package android.example.bodegaversionone


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemDAO {

    // a query function that will return our items by alphabetized titles
    @Query("SELECT * FROM item_table ORDER BY title ASC")
    fun getAlphabetizedItems(): Flow<List<Product>>

    // query function to insert a new item of data
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(data: Product)

    // query function to delete table
    @Query("DELETE FROM item_table")
    suspend fun deleteAll()

    // a play/test function to try out grabbing items by category
    @Query("SELECT * FROM item_table ORDER BY category ASC")
     fun getAllByCategory() : Flow<List<Product>>

}