package edu.csueb.android.zoodirectory2


import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView

class ItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val imageView: ImageView = findViewById(R.id.detailImageView)
        val textView: TextView = findViewById(R.id.detailTextView)

        val imageResId = intent.getIntExtra(EXTRA_IMAGE_RES_ID, 0)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION)

        imageView.setImageResource(imageResId)
        textView.text = description
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    companion object {
        const val EXTRA_IMAGE_RES_ID = "com.example.yourappname.IMAGE_RES_ID"
        const val EXTRA_DESCRIPTION = "com.example.yourappname.DESCRIPTION"
    }
}
