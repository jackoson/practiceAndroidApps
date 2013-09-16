package Newport.test.Nellie;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

public class test extends Activity{

	 public static final String PREFS_NAME = "MyPrefsFile";

	    @Override
	    protected void onCreate(Bundle state){
	       super.onCreate(state);

	       // Restore preferences
	       SharedPreferences settings = getPreferences(0);
	       SharedPreferences.Editor prefEditor = settings.edit();
	      
	       prefEditor.commit();  
	       
	    }

	    @Override
	    protected void onStop(){
	       super.onStop();

	      // We need an Editor object to make preference changes.
	      // All objects are from android.context.Context
	      SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	      SharedPreferences.Editor editor = settings.edit();
	    
	      // Commit the edits!
	      editor.commit();
	    }
}
