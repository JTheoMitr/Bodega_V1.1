package android.example.bodegaversionone

import androidx.room.TypeConverter

object Converters {
    @TypeConverter
    @JvmStatic
    fun convertRatingToString(rating: Rating): String {
        return rating.toString()
    }



    @TypeConverter
    @JvmStatic
    fun convertStringToRating(rating: String): Rating {
        var convertedRating: Rating = Rating(0,0.00)
       var result = rating.split(",")
        convertedRating.count = Integer.parseInt(result[0])
        convertedRating.rate = result[1].toDouble()

        return convertedRating
    }
}