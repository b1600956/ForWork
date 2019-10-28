package com.example.forwork;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.DialogFragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MyContractsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contracts);
     //ActionBar supportActionBar = getSupportActionBar();
    }
/**
 @Override public boolean onCreateOptionsMenu(Menu menu) {
 // Inflate the menu; this adds items to the action bar if it is present.
 getMenuInflater().inflate(R.menu.menu_main, menu);
 MenuItem searchItem = menu.findItem(R.id.action_search);
 // Get the SearchView and set the searchable configuration
 SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
 SearchView menu_searchView = (SearchView) searchItem.getActionView();
 // Assumes current activity is the searchable activity
 menu_searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
 return true;
 }

 @Override public boolean onOptionsItemSelected(MenuItem item) {
 // Handle action bar item clicks here. The action bar will
 // automatically handle clicks on the Home/Up button, so long
 // as you specify a parent activity in AndroidManifest.xml.
 int id = item.getItemId();

 //noinspection SimplifiableIfStatement
 if (id == R.id.action_search) {
 item.collapseActionView();
 return true;
 } else if(id == R.id.action_filter){
 DialogFragment filterFragment = new FilterFragment();
 filterFragment.show(getSupportFragmentManager(), getResources().getString(R.string.filter_title));
 }
 return super.onOptionsItemSelected(item);
 }*/
}
