package android.example.bodegaversionone

data class Rating(
    var count: Int,
    var rate: Double
) {
    override fun toString(): String {
        super.toString()
        return ""+ count + ","+rate
    }
}