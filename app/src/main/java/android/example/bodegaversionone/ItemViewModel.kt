package android.example.bodegaversionone

import androidx.lifecycle.*
import kotlinx.coroutines.launch


class ItemViewModel(private val repository: ItemRepository) : ViewModel() {

    // uses LiveData and caching on allItems
    // puts the observer on the data to only update when changed
    // Repository is now separated from UI through the ViewModel MVVM
    val allItems : LiveData<List<Product>> = repository.allItems.asLiveData()

    // launches coroutine to insert the data without blocking
    fun insert(product: Product) =  viewModelScope.launch {
        repository.insert(product)
    }

    fun insertAll(products : ArrayList<Product>){
            viewModelScope.launch {
                for(product in products){

                    repository.insert(product)
            }

        }
    }
}

class ItemViewModelFactory(private val repository: ItemRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

// DEPRECATED: TO BE DELETED
/*

class ItemViewModelFactory(private val repository: ItemRepository) : ViewModelProvider.Factory {
    override fun <ItemViewModel : ViewModel> create(modelClass: Class<ItemViewModel>) : ItemViewModel {
        if(modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            @Suppress()
            return ItemViewModel(repository)  as
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
*/

