package com.example.telnet.client;

import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryActivity extends Activity
{
	private ListView mListCommands;
	private LayoutInflater mInflater;
	final public static String RESULT_COMMAND = "RESULT_COMMAND";
	
	private Vector<CommandItem> mData;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.history);

        mInflater = (LayoutInflater) getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        mData = new Vector<CommandItem>();
       /* 
        mListCommands = (ListView)findViewById(R.id.list);
        mListCommands.setClickable(true); */
        /*mListCommands.setOnItemClickListener(new AdapterView.OnItemClickListener()
        { 
            @Override 
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) 
            {
            	if(position < mData.size())
            	{
	            	CommandItem callsign = mData.elementAt(position);
	            	if(callsign != null)
	            	{
	            		getIntent().putExtra(RESULT_COMMAND, callsign.mCommand);
						setResult(RESULT_OK, getIntent());
						finish();
	            	}
            	}
            } 
        }); */

        DoHistory();
    }

    public void DoHistory()
    {
		SharedPreferences settings = getSharedPreferences("TinyTelnet", 0);
		// load history list
		String strHistory = settings.getString("history", "");
		if(!strHistory.equals(""))
		{
			if(strHistory.indexOf(",") != -1)
			{
				String[] strHistoryCalls = strHistory.split(",");
				int nHistoryLen = strHistoryCalls.length;
				if(nHistoryLen > 0)
				{
					String strHistoryItem = "";
					for (int i=0; i<nHistoryLen; i++)
					{
						strHistoryItem = strHistoryCalls[i];
						if(!strHistoryItem.equals(""))
						{
							CommandItem callsign = new CommandItem(strHistoryItem);
				        	mData.add(callsign);
						}
					}
				}
			}
			else
			{
				CommandItem callsign = new CommandItem(strHistory);
	        	mData.add(callsign);
			}

	    	//MyHistoryAdapter adapter = new MyHistoryAdapter(HistoryActivity.this, R.layout.history, R.layout.historyitem, mData);
	    //	mListCommands.setAdapter(adapter);
		}
    }

	//////////////////////////////////
	private class CommandItem 
	{
	    protected String mCommand;
	    
	    CommandItem(String strCommand)
	    {
	    	mCommand = strCommand;
	    }
	}

	//////////////////////////////////
	private class ViewHolder 
	{
	    private View mRow;
	    private ImageView mImageViewDelete = null; 
	    private TextView mTextViewCallsign = null;
	    
	    public ViewHolder(View row)
	    {
	        mRow = row;
	    }
	    
	   
	}

	//////////////////////////////////
	private class MyHistoryAdapter extends ArrayAdapter<CommandItem>
	{
		 public MyHistoryAdapter(Context context, int resource, int textViewResourceId, List<CommandItem> objects)
		 {
			 super(context, resource, textViewResourceId, objects);
		 }

		 @Override
		 public View getView(int position, View convertView, ViewGroup parent)
		 {
			   final int nIndex = position;
			   
		       ViewHolder holder = null;
		       ImageView mImageViewDelete = null; 
		       TextView mTextViewCallsign = null;

		       CommandItem rowData = getItem(position);
		       if(null == convertView)
		       {
		           /* convertView = mInflater.inflate(R.layout.historyitem, null);
		            holder = new ViewHolder(convertView);
		            convertView.setTag(holder);*/
		       }
		       
		       holder = (ViewHolder) convertView.getTag();

		    /*   mTextViewCallsign = holder.getTextViewCallsign();
		       mTextViewCallsign.setText(rowData.mCommand);*/
		       
		       if(position % 2 == 0)
		    	   convertView.setBackgroundColor(0x77A5A5A5);
		       else
		    	   convertView.setBackgroundColor(0x77E0E0E0);

		      /* mImageViewDelete = holder.getImageViewDelete();
		       mImageViewDelete.setOnClickListener(new OnClickListener()*/
		      // {
		    	   /*public void onClick(View v)
		    	   {
		    		   CommandItem callsign = getItem(nIndex);
		    		   remove(callsign);
		    		   
		    		   String strCallsign = callsign.mCommand;
		    		   if(!strCallsign.equals(""))
		    		   {
		    				SharedPreferences settings = getSharedPreferences("TinyClient", 0);
		    				SharedPreferences.Editor editor = settings.edit();

		    				// load history list
		    				String strHistory = settings.getString("history", "");
		    				if(!strHistory.equals(""))
		    				{
		    					if(strHistory.indexOf(",") != -1)
		    					{
		    						String strNewHistory = "";
		    						
		    						String[] strHistoryCalls = strHistory.split(",");
		    						int nHistoryLen = strHistoryCalls.length;
		    						if(nHistoryLen > 0)
		    						{
		    							String strHistoryItem = "";
		    							for (int i=0; i<nHistoryLen; i++)
		    							{
		    								strHistoryItem = strHistoryCalls[i];
		    								if(!strHistoryItem.equals("") && !strHistoryItem.equals(strCallsign))
		    								{
		    									if(!strNewHistory.equals(""))
		    										strNewHistory += ",";
		    									strNewHistory += strHistoryItem;
		    								}
		    							}
		    						}
		    						
		    						if(!strNewHistory.equals(""))
		    							editor.putString("history", strNewHistory);
		    					}
		    					else
	    							editor.putString("history", "");
		    				}

		    				editor.commit();
		    		   }
		    	   }
		       }
		       );
*/
		       return convertView;
		 }
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
		if(mData.size() > 0)
		{
	        MenuInflater inflater = getMenuInflater();
	        //inflater.inflate(R.menu.history, menu);
		}
 
		return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch (item.getItemId())
        {
            /*case R.id.clearhistory:
            	if(mData.size() > 0)
            	    ConfirmDeleteMessageBox("Are you sure you want to clear history?");
                break;*/
        }
        return true;
    }
    
    private void ConfirmDeleteMessageBox(String strText)
    {
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("TinyTelnet");
        alertDialog.setMessage(strText);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() 
        {
            public void onClick(DialogInterface dialog, int id) 
            {
    			SharedPreferences settings = getSharedPreferences("TinyTelnet", 0);
    			SharedPreferences.Editor editor = settings.edit();
    			editor.putString("history", "");
    			editor.commit();

    			mData.clear();
    			
    			MyHistoryAdapter adapter = (MyHistoryAdapter)mListCommands.getAdapter();
    			adapter.clear();
            }
        });
        
        alertDialog.setButton2("Cancel", new DialogInterface.OnClickListener() 
        {
            public void onClick(DialogInterface dialog, int id) 
            {
            	return;
            }
        });
        
        alertDialog.show();
    }
}