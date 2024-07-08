package edu.csueb.android.zoodirectory2
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast


class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val callButton: Button = findViewById(R.id.callButton)
        callButton.setOnClickListener {
            dialPhoneNumber("1234567890") // Replace with your actual phone number
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            // Handle case where no dialer app is available
            Toast.makeText(this, "No dialer app available", Toast.LENGTH_SHORT).show()
        }
    }


}
