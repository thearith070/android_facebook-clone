package thearithtech.me.facebook_clone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import thearithtech.me.facebook_clone.databinding.ActivityMainBinding
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private val storyAdapter: StoryAdapter by lazy { StoryAdapter() }
    private val feedAdapter: FeedAdapter by lazy { FeedAdapter() }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val json: String = loadJSONFromAsset()
        val items: List<Item> = Gson().fromJson(json, object : TypeToken<List<Item>>() {}.type)
        setupStoryList(items)
        setupFeedList(items)

    }

    private fun setupStoryList(list: List<Item>) {
        val l = arrayListOf<Item>()
        l.add(Item(profile = "", name = "", title = "", image = "", duration = ""))
        l.addAll(list.shuffled())
        binding.rvStory.adapter = storyAdapter
        storyAdapter.submitList(l)
    }

    private fun setupFeedList(list: List<Item>) {
        binding.rvFeed.adapter = feedAdapter
        feedAdapter.submitList(list.shuffled())
    }

    private fun loadJSONFromAsset(): String {
        var json = ""
        try {
            val inputStream: InputStream = assets.open("data.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json
    }


}