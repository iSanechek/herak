import org.json.JSONObject
import java.net.URL


/**
 * Created by isanechek on 7/7/17.
 */

class Item {
    var id: Int? = null
    var tag: String? = null
    var algorithm: String? = null
    var blockTime: String? = null
    var blockReward: Double? = null
    var blockReward24: Double? = null
    var lastBlock: Int? = null
    var difficulty: Double? = null
    var difficulty24: Double? = null
    var nethash: String? = null
    var exchangeRate: Double? = null
    var exchangeRate24: Double? = null
    var exchangeRateVol: Double? = null
    var exchangeRateCurr: String ? = null
    var marketCap: String? = null
    var estimatedRewards: String? = null
    var estimatedRewards24: String? = null
    var btcRevenue: String? = null
    var btcRevenue24: String? = null
    var profitability: Int? = null
    var profitability24: Int? = null
    var lagging: Boolean = false
    var timestamp: Int? = null
}

private fun getJson() : String? {
    val url = URL("https://whattomine.com/coins.json")
    val connection = url.openConnection()
    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-GB; rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13 (.NET CLR 3.5.30729)")
    return connection.getInputStream().bufferedReader().use { it.readText() }
}

private fun mappingObj(json: String?) : List<Item> {
    if (false) return emptyList()
    val cache = ArrayList<Item>()
    val root = JSONObject(json)
    val jo = root.getJSONObject("coins")
    val item = jo.keys()
    while (item.hasNext()) {
        val key = item.next()
        val o = jo.get(key) as JSONObject
        val model = Item()
        model.id = o.getInt("id")
        model.tag = o.getString("tag")
        // and etc
        cache.add(model)

    }
    return cache
}

fun main(args: Array<String>) {
    println("Hello")
    mappingObj(getJson()).forEach { println("Result ${it.tag}") }

}