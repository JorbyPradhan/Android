package com.example.myanmarlottery

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_alert.*


@Suppress("DEPRECATION", "UNSAFE_CALL_ON_PARTIALLY_DEFINED_RESOURCE")
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //View bottomSheet = coordinatorLayout.findViewById(R.id.bottom_sheet);
        //  bottomSheetBehavior = BottomSheetBehavior.from<LinearLayout>(persistent_bottom_sheet)
        bottomSheetBehavior = from(bottom_sheet)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = ContextCompat.getColor(this,R.color.colorGray)
        }
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()*/
            if (bottomSheetBehavior.state != STATE_EXPANDED) {
                bottomSheetBehavior.state = STATE_EXPANDED
            } else {
                bottomSheetBehavior.state = STATE_COLLAPSED
            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_alert, R.id.nav_create), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        bottomSheetBehavior.setBottomSheetCallback(object: BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState){
                    STATE_HIDDEN ->fab.visibility = View.GONE
                    STATE_EXPANDED ->
                        fab.visibility = View.GONE
                    STATE_COLLAPSED ->
                        fab.visibility = View.VISIBLE

                    STATE_DRAGGING -> Log.i("Bottom","Drag")
                    STATE_HALF_EXPANDED -> {
                        Log.i("Bottom","HALF")
                    }
                    STATE_SETTLING -> {
                        Log.i("Bottom","Settling")
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

        })
        submitOk.setOnClickListener {
            validation()
        }
        send_order.setOnClickListener {
            val intent = Intent(this@MainActivity, MyService::class.java)
            intent.action = MUSIC_SERVICE_ACTION_START
            startService(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.search, menu)
        return true
        /*val searchViewItem = menu.findItem(R.id.app_bar_search)
        val searchView = MenuItemCompat.getActionView(searchViewItem) as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               //search data
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)*/
    }
/* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
             /*   if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }*/
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }  */
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    @SuppressLint("SetTextI18n")
    private fun validation() {
        val numEnd = pinView_end.text.toString()
        var finalNumber : String ?= null
        val numFirst  = pinView_first.text.toString()
        if (numEnd.isEmpty() && numFirst.isEmpty()){
            send_order.visibility = View.GONE
            Toast.makeText(this, "Fill Number First", Toast.LENGTH_SHORT).show()
            return
        }
        if(numEnd.isEmpty()){
            finalNumber = "${numFirst}နှင့်စ"
            send_order.visibility = View.VISIBLE
        }else{
            send_order.visibility = View.VISIBLE
            val num = numEnd.reversed()
            if(numFirst.isEmpty()){
                finalNumber = "${numEnd}နှင့်ဆုံး"
            }else{
                if(numFirst.length == 3 && numEnd.length == 3) {
                    finalNumber = "$numFirst$numEnd"
                }
                if(numEnd.length != 3) {
                    finalNumber = "${numFirst}နှင့်စ၍__${numEnd}နှင့်ဆုံး"
                }
            }
        }
        dis_num.text = """Your number is$finalNumber """
    }
}