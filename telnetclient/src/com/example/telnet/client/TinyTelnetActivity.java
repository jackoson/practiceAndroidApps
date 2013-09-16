package com.example.telnet.client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.ClipboardManager;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class TinyTelnetActivity extends Activity
{
    public static int REQUEST_OPEN = 0;
    public static int REQUEST_HISTORY = 1; 
    final Context context = this;
    private boolean mIsConnected = false;
    private String mStrHost = "";
    private String mStrPort = "";
    
    Handler mHandler = null;
    Socket mSocket = null;
    Thread mThread = null;
    TextView mTextViewContent;
    ScrollView mScrollViewContent;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.mHandler = new Handler();
        this.mTextViewContent = (TextView)findViewById(R.id.textViewContent);
        this.mScrollViewContent = (ScrollView)findViewById(R.id.scrollViewContent);
        this.mScrollViewContent.setSmoothScrollingEnabled(true);
        
        registerForContextMenu(mTextViewContent);

        Button btnEnter = (Button) findViewById(R.id.buttonEnter);
        btnEnter.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0) 
            {
            	PostCommand();
            } 
        });
        
        ConnectDialog();
     }
    
    private void PostCommand()
    {
    	if(mSocket != null && mIsConnected)
    	{
	        EditText edCmdText = (EditText) findViewById(R.id.editCmdText);
	        String strCommand = edCmdText.getText().toString();
	        if(!strCommand.equals(""))
	        {
	            SharedPreferences settings = getSharedPreferences("TinyTelnet", 0);
	            SharedPreferences.Editor editor = settings.edit();

	            // load history list
	            String strHistory = settings.getString("history", "");
	            if(!strHistory.equals(""))
	            {
	                if(strHistory.indexOf(",") != -1)
	                {
	                    String strNewHistory = strCommand;
	                    
	                    String[] strHistoryCalls = strHistory.split(",");
	                    int nHistoryLen = strHistoryCalls.length;
	                    if(nHistoryLen > 0)
	                    {
	                        String strHistoryItem = "";
	                        for (int i=0; i<nHistoryLen; i++)
	                        {
	                            strHistoryItem = strHistoryCalls[i];
	                            if(!strHistoryItem.equals("") && !strHistoryItem.equals(strCommand))
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
	                {
	                    // if saved call in history is different
	                    if(!strHistory.equals(strCommand))
	                        editor.putString("history", strCommand + "," + strHistory);
	                }
	            }
	            else
	                editor.putString("history", strCommand);
	            
	            editor.commit();
	        }

            try 
            {
				OutputStream streamOutput = mSocket.getOutputStream();
				
				strCommand += "\n";
		        try
		        {
		           	byte[] arrayOutput = strCommand.getBytes("ASCII");
		            int nLen = arrayOutput.length;
		            streamOutput.write(arrayOutput, 0, nLen);
		        }
		        catch (Exception e0)
		        {
	    			Handler handlerException = TinyTelnetActivity.this.mHandler;
					final String strMessage = "Error while sending to server:\r\n" + e0.getMessage();
	    			Runnable rExceptionThread = new Runnable()
	    			{
	    				public void run()
	    				{
	    					Toast.makeText(context, strMessage, 2000).show();
	    				}
	    			};

	    			handlerException.post(rExceptionThread);
		        }
			}
            catch (IOException e1) 
            {
				e1.printStackTrace();
			}
	            
            edCmdText.setText("");
    	}
    	else
    	{
    		Toast.makeText(context, "Please connect to the server first", 2000).show();
    	}
    }
    
    private void ConnectDialog()
    {
    	LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.connect, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setView(promptsView);

		final EditText edIP = (EditText) promptsView.findViewById(R.id.editTextIP);
		final EditText edPort = (EditText) promptsView.findViewById(R.id.editTextPort);

	    SharedPreferences settings = getSharedPreferences("TinyTelnet", 0);
	    String strOldHost = settings.getString("server-ip", "");
	    String strOldPort = settings.getString("server-port", "23");
	    
	    edIP.setText(strOldHost);
	    edPort.setText(strOldPort);

		// set dialog message
		alertDialogBuilder.setCancelable(true);
		
		alertDialogBuilder.setPositiveButton("Connect",
			  new DialogInterface.OnClickListener() 
		{
			  public void onClick(DialogInterface dialog,int id) 
			  {
				   // get user input and set it to result
                   // edit text
				   String strHost = edIP.getText().toString();
				   String strPort = edPort.getText().toString();
				   
				   if(!strHost.equals("") && !strPort.equals(""))
				   {
					    SharedPreferences settings = getSharedPreferences("TinyTelnet", 0);
		    			SharedPreferences.Editor editor = settings.edit();
		    			editor.putString("server-ip", strHost);
		    			editor.putString("server-port", strPort);
		    			editor.commit();
                        
		    			//InetAddress serverAddr = InetAddress.getByName(serverIpAddress);
		    			mStrHost = strHost;
		    			mStrPort = strPort;
		    			
		    			if(mThread != null)
		    			{
		    				mThread.stop();
		    				mThread = null;
		    			}

		    			mThread = new Thread(new ClientThread());
		    			mThread.start();
				   }
			  }
        });
        
        alertDialogBuilder.setNegativeButton("Cancel", 
        		new DialogInterface.OnClickListener() 
        {
			    public void onClick(DialogInterface dialog,int id) 
			    {
			    	dialog.cancel();
			    }
        });

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) 
    {
    	super.onCreateContextMenu(menu, v, menuInfo); 

    	if (v.getId() == R.id.textViewContent) 
    	{
    		menu.setHeaderTitle("Actions");
    		menu.add(Menu.NONE, v.getId(), 0, "Clear Window"); 
    	}
    }
    
    @Override
	public boolean onContextItemSelected(MenuItem item)
	{
		int menuItemIndex = item.getItemId();
		if(menuItemIndex == R.id.textViewContent) // clear window
		{
			TextView textContent = (TextView) findViewById(R.id.textViewContent);
	        textContent.setText("");
		}

		return true;
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
       // inflater.inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        TextView textContent = (TextView) findViewById(R.id.textViewContent);
        String strText = textContent.getText().toString();
        
        switch (item.getItemId())
        {
            case R.id.connect:
            	if(!mIsConnected)
            		ConnectDialog();
            	else
            		Toast.makeText(context, "You are connected already", 2000).show();
                break;
            /*case R.id.history:
               Intent iHistory = new Intent(TinyTelnetActivity.this, HistoryActivity.class);
                startActivityForResult(iHistory, REQUEST_HISTORY); 
                break;*/
            case R.id.disconnect:
            	if(mIsConnected)
            	{
					mIsConnected = false;
					try 
					{
						mSocket.close();
					}
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					
					mSocket = null;
            	}
            	else
            		Toast.makeText(context, "You are not connected to server", 2000).show();
                break;
            case R.id.copy:
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                clipboard.setText(strText);
                break;
            case R.id.save:
                Intent iFileDialog = new Intent(TinyTelnetActivity.this, FileDialog.class);
                iFileDialog.putExtra(FileDialog.START_PATH, "/sdcard");
                iFileDialog.putExtra(FileDialog.SELECTION_MODE, SelectionMode.MODE_CREATE);
                startActivityForResult(iFileDialog, REQUEST_OPEN);
                break;
            case R.id.about:
                Intent iAbout = new Intent(TinyTelnetActivity.this, AboutActivity.class);
                startActivity(iAbout); 
                break;
        }
        return true;
    }
   
    public synchronized void onActivityResult(final int requestCode, int resultCode, final Intent data)
    {
        if (resultCode == Activity.RESULT_OK)
        {
            if (requestCode == REQUEST_OPEN)
            {
                String strFilePath = data.getStringExtra(FileDialog.RESULT_PATH);
                if(!strFilePath.equals(""))
                {
                    File logFile = new File(strFilePath); 
                    if (!logFile.exists()) 
                    { 
                        try 
                        { 
                            logFile.createNewFile(); 
                        }  
                        catch (IOException e) 
                        { 
                            Toast msg = Toast.makeText(TinyTelnetActivity.this, "Can't open selected file", 1000);
                            msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
                            msg.show();

                            e.printStackTrace();
                        } 
                    } 
   
                    try 
                    { 
                        TextView textContent = (TextView) findViewById(R.id.textViewContent);
                        String strText = textContent.getText().toString();

                        //BufferedWriter for performance, true to set append to file flag 
                        BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));  
                        buf.append(strText); 
                        buf.newLine(); 
                        buf.close();
                        
                        Toast msg = Toast.makeText(TinyTelnetActivity.this, "Saved", 1000);
                        msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
                        msg.show();
                    } 
                    catch (IOException e) 
                    { 
                        Toast msg = Toast.makeText(TinyTelnetActivity.this, "Can't write to selected file", 1000);
                        msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
                        msg.show();

                        e.printStackTrace(); 
                    } 
                }
            }
            else if (requestCode == REQUEST_HISTORY)
            {
                String strCallsign = data.getStringExtra(HistoryActivity.RESULT_COMMAND);
                if(!strCallsign.equals(""))
                {
                	EditText edCmdText = (EditText) findViewById(R.id.editCmdText);
                	edCmdText.setText(strCallsign);
                    
                	PostCommand();
                }
            }
        }
        else if (resultCode == Activity.RESULT_CANCELED)
        {
            // file not selected
        }
    }

    protected void onDestroy()
    {
    	super.onDestroy();

    	if(mIsConnected)
    	{
    		mIsConnected = false;
  
	    	try
	    	{
	    		if(this.mThread != null)
	    		{
	    			Thread threadHelper = this.mThread; 
	    			this.mThread = null;
	    			threadHelper.interrupt();
	    		}
	    	}
	    	catch (Exception e1)
	    	{
	    	}
    	}
    }

    public class ClientThread implements Runnable 
    {
    	public void run() 
    	{
    		
    		//setting prot number
	    	int nPort = 23;
	    	try
	    	{ 
	    	    nPort = Integer.parseInt(mStrPort); 
	    	}
	    	catch(NumberFormatException nfe)
	    	{
	    		nPort = 23;
	    	}
	    	
    		try 
    		{
    			//make a normal socket
    			Socket socket = new Socket(mStrHost, nPort);
		    	mSocket = socket;
	        
		    	//attach an input streeam to the socket
		    	InputStream streamInput = mSocket.getInputStream();
		    	mIsConnected = true;
		    	
		    	
		    	
		    	byte[] arrayOfByte = new byte[10000];
		    	while (mIsConnected)
		    	{
		    		int j = 0;
		    		try{
		    			int i = arrayOfByte.length;
		    			//get the text from the input stream. -1 if nothing there
		    			j = streamInput.read(arrayOfByte, 0, i);
		    			if (j == -1)
		    			{
		    				throw new Exception("Error while reading socket.");
		    			}
		    		}
		    		catch (Exception e0)
		    		{//error handling
		    			Handler handlerException = TinyTelnetActivity.this.mHandler;
		    			String strException = e0.getMessage();
    					final String strMessage = "Error while receiving from server:\r\n" + strException;
		    			Runnable rExceptionThread = new Runnable()
		    			{
		    				public void run()
		    				{
		    					Toast.makeText(context, strMessage, 3000).show();
		    				}
		    			};

		    			handlerException.post(rExceptionThread);
		    			
		    			if(strException.indexOf("reset") != -1 || strException.indexOf("rejected") != -1)
		    			{
		    				mIsConnected = false;
							try 
							{
								mSocket.close();
							}
							catch (IOException e1) 
							{
								e1.printStackTrace();
							}
		    				mSocket = null;
			    			break;
		    			}
		    		}
		    		
		    		//if no letters
		    		if (j == 0)
		    			continue;
		    		//else
		    		//gets rid of new lines
		    		final String strData = new String(arrayOfByte, 0, j).replace("\r", "");
		    		
		    		Handler localHandler2 = TinyTelnetActivity.this.mHandler;
		    		
		    		//adds new input to screen
		    		Runnable local2 = new Runnable()
		    		{
		    			public void run()
		    			{
		    				StringBuilder localStringBuilder1 = new StringBuilder();
		    				CharSequence localCharSequence = TinyTelnetActivity.this.mTextViewContent.getText();
		    				localStringBuilder1.append(localCharSequence);
		    				localStringBuilder1.append(strData);
		    				TinyTelnetActivity.this.mTextViewContent.setText(localStringBuilder1.toString());
		    				TinyTelnetActivity.this.mScrollViewContent.requestLayout();
		    				
		    				Handler localHandler = TinyTelnetActivity.this.mHandler;
		    				Runnable local1 = new Runnable()
		    				{
		    					public void run()
		    					{
		    						ScrollView localScrollView = TinyTelnetActivity.this.mScrollViewContent;
		    						int i = TinyTelnetActivity.this.mTextViewContent.getHeight();
		    						localScrollView.smoothScrollTo(0, i);
		    					}
		    				};

		    				localHandler.post(local1);
		    			}
		    		};
		    		
		    		localHandler2.post(local2);
		    	}
	
    			socket.close();
    			//mSocket = null;
   			}
    		catch (Exception e0) 
    		{
    			mIsConnected = false;

    			Handler handlerException = TinyTelnetActivity.this.mHandler;
    			String strException = e0.getMessage();
    			if(strException == null)
    				strException = "Connection closed";
    			else
    				strException = "Cannot connect to the server:\r\n" + strException;
    			
				final String strMessage = strException;
    			Runnable rExceptionThread = new Runnable()
    			{
    				public void run()
    				{
    					Toast.makeText(context, strMessage, 2000).show();
    				}
    			};

    			handlerException.post(rExceptionThread);
            }
    	}
    }
}