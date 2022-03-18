package android.example.bodegaversionone

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// annotation to declare a Room Database with a table of our MyDataItem class
@Database(entities = arrayOf(Product::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
public abstract class ItemRoomDB : RoomDatabase() {

    // creating a function to hold the DAO
    abstract fun itemDAO(): ItemDAO

    private class ItemDatabaseCallback (
        private val scope : CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                database -> scope.launch {
                    var itemDAO = database.itemDAO()

                    // Deletes all content
                    itemDAO.deleteAll()

                    // adds sample item
                    var r = Rating(1, 5.0)
                    val p = Product("shoes", "red", 999999, "this.jpg", 100.0, r, "Awesome Red Shoes")
                    itemDAO.insert(p)
            }
            }
        }
    }

    companion object {
        // singleton design structure using our ItemRoomDB class
        @Volatile
        private var INSTANCE : ItemRoomDB? = null

        fun getDatabase(context: Context, applicationScope: CoroutineScope) : ItemRoomDB {
            // checking to see if not null and returning it
            // otherwise create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemRoomDB::class.java,
                    "item_database"
                )
                    .addCallback(ItemDatabaseCallback(applicationScope))
                    .build()
                INSTANCE = instance
                // return the instance
                instance
            }
        }
    }
}