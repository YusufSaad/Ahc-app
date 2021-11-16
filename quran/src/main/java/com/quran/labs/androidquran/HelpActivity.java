package com.quran.labs.androidquran;

import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.quran.labs.androidquran.ui.QuranActionBarActivity;

public class HelpActivity extends QuranActionBarActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    setContentView(R.layout.help);

    TextView helpText = findViewById(R.id.txtHelp);
    helpText.setText(Html.fromHtml(getString(R.string.help)));
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return false;
  }
}
