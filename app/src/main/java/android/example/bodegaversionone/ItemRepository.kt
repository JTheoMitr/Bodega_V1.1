package android.example.bodegaversionone

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// declares the DAO as private in the constructor. Pass the DAO instead of entire DB
// observer design structure
class ItemRepository(private val itemDAO: ItemDAO) {

    // Room moves all queries to a separate thread
    // Flow will notify the observer when the data changes
    val allItems : Flow<List<Product>> = itemDAO.getAlphabetizedItems()

    // Room automatically handles threads
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(item: Product) {
        itemDAO.insert(item)
    }

}