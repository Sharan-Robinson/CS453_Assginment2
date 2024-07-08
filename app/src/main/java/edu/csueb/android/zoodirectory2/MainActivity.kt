package edu.csueb.android.zoodirectory2

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.net.Uri
import androidx.appcompat.app.AlertDialog



class MainActivity : AppCompatActivity(), MyAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter
    private lateinit var itemList: List<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        itemList = generateList(5)
        myAdapter = MyAdapter(itemList, this)
        recyclerView.adapter = myAdapter
    }

    private fun generateList(size: Int): List<Item> {
        val list = ArrayList<Item>()
        val stringArray: ArrayList<String> =
            resources.getStringArray(R.array.animal_names).toList() as ArrayList<String>

        var images: IntArray =
            intArrayOf(R.mipmap.lion, R.mipmap.eagle, R.mipmap.hawk, R.mipmap.rhino, R.mipmap.tiger)

        for (i in 0 until size) {
            val item = Item(i, stringArray[i], images[i])
            list += item
        }

        return list
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun proceedToItemDetail(item: Item) {
        val stringArray: ArrayList<String> =
            resources.getStringArray(R.array.animal_desc).toList() as ArrayList<String>

        var images: IntArray =
            intArrayOf(R.drawable.lion, R.drawable.eagle, R.drawable.hawk, R.drawable.rhino, R.drawable.tiger)

        val intent = Intent(this, ItemDetailActivity::class.java).apply {
            putExtra(ItemDetailActivity.EXTRA_IMAGE_RES_ID, images[item.id])
            putExtra(ItemDetailActivity.EXTRA_DESCRIPTION, stringArray[item.id])
        }
        startActivity(intent)
    }

    private fun showConfirmationDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.apply {
            setTitle("Confirmation")
            setMessage("The animal is very scary. Are you sure you want to proceed?")
            setPositiveButton("Yes") { dialogInterface: DialogInterface, _: Int ->
                // Proceed as normal
                dialogInterface.dismiss()
                // Here you can handle any specific action on clicking Yes
                proceedToItemDetail(itemList.last()) // Proceed to detail of last item
            }
            setNegativeButton("No") { dialogInterface: DialogInterface, _: Int ->
                // Stay on the same activity, do nothing
                dialogInterface.dismiss()
            }
            create().show()
        }

        return
    }

    override fun onItemClick(item: Item) {
        if (item == itemList.lastOrNull()) {
            showConfirmationDialog()
        } else {
            proceedToItemDetail(item)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_uninstall -> {
                uninstallApp()
                true
            }
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun uninstallApp() {
        val packageName = packageName
        val intent = Intent(Intent.ACTION_DELETE)
        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
    }


}






