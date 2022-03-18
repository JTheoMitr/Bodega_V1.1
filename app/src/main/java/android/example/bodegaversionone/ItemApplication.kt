package android.example.bodegaversionone

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ItemApplication : Application() {
    // lazy calls so DB is only instantiated when necessary
    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { ItemRoomDB.getDatabase(this, applicationScope) }
    val repository by lazy { ItemRepository(database.itemDAO())}
}