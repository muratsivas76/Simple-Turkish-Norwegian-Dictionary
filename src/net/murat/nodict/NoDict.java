package net.murat.nodict;

import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoDict extends Activity {
  
  private EditText word_tv = null;
  private Button find_button = null;
  private Button exit_button = null;
  private EditText result_tv = null;
  
  // For compile
  TRNOWord nww = null;
  
  private HashMap<String, TRNOWord> wordMap = null;
  
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(R.layout.main);
    
    word_tv = (EditText) findViewById(R.id.word_tv); 
    result_tv = (EditText) findViewById(R.id.result_tv);
    
    // Load HashMap
    loadHashMap();
    
    InputMethodManager immx = null;
    try {
        immx = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    } catch (Exception e) {
        immx = null;
    }
    
    final InputMethodManager imm = immx;
    
    find_button = (Button) findViewById(R.id.find_button);
    find_button.setOnClickListener(new View.OnClickListener() {
        private void hideTeclado(View view) {
            if (imm == null) return;      
            try {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            } catch (Exception e) {
            }
        }
        
        public void onClick(View view) {
            String str = word_tv.getText().toString();
            if (str == null) {
                hideTeclado(view);
                return;
            }
            
            if (str.length() < 2) {
                hideTeclado(view);
                return;
            }
            
            str = str.trim();
            
            boolean error = false;

            if (error) {
                result_tv.setText("\nUn Error Ocurred!");
                hideTeclado(view);
                return;
            }
            
            // SEARCH
            String result = searchWords(str);
            result_tv.setText(result);
    	  
    	    hideTeclado(view);
        }
    });
    
    exit_button = (Button) findViewById(R.id.exit_button);
    exit_button.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            onDestroy();
        }
    });
  }
            
  @SuppressWarnings("unchecked")
  private void loadHashMap() {
      try {
          InputStream is = getAssets().open("hashmap.dat");
          ObjectInputStream ois = new ObjectInputStream(is);
          wordMap = (HashMap<String, TRNOWord>) ois.readObject();
          ois.close();
      } catch (Exception e) {
          e.printStackTrace();
          wordMap = new HashMap<>();
      }
  }
  
  private String searchWords(String query) {
      if (wordMap == null || wordMap.isEmpty()) {
          return "Could not load dictionary!";
      }
      
      List<TRNOWord> results = new ArrayList<>();
      String lowerQuery = query.toLowerCase();
      
      // Search in HashMap
      for (TRNOWord word : wordMap.values()) {
          if (word.getTR().toLowerCase().startsWith(lowerQuery) || 
              word.getNO().toLowerCase().startsWith(lowerQuery)) {
              
              // Do not add same word again
              if (!results.contains(word)) {
                  results.add(word);
                  if (results.size() >= 10) break;
              }
          }
      }
      
      if (results.isEmpty()) {
          return "Could not find: " + query;
      }
      
      // Formatting results
      StringBuilder sb = new StringBuilder();
      for (TRNOWord word : results) {
          sb.append(word.getTR())
            .append(" - ")
            .append(word.getNO())
            .append("\n\n");
      }
      
      return sb.toString();
  }
  
  public void onResume() {
    super.onResume();
  }
  
  public void onPause() {
    super.onPause();
  }
  
  protected void onDestroy() {
    super.onDestroy();
    System.exit(0);
  }
  
}
