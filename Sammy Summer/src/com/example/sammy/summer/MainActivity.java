package com.example.sammy.summer;






import com.example.sammy.summer.MainActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;

public class MainActivity extends Activity implements OnClickListener, OnItemSelectedListener{

	Button button,b1,b2,b3,b4;
	TextView input;
	TextView output;
	
	String inputString ="";
	Button b5,b6,b7,b8,b9,b0,ba,bm,be,bx,bxs,bb,bpo,bsin,bcos,bc,bo,bn,bp,bt,bd,bf,bncr,bpi,bmd,bre;
	private Button btan;
	private Button bcossq;
	private Button btansq;
	private Button bsinsq;
	private Button bclear;
	ToggleButton tb;
	Spinner s;
	Class ourClass;
	String Equation = "";
	int uplim = -1543;
	int lowlim = -1543;
	boolean sumb = true;
	int position = 3;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		setup();
		
		testnewclass();
		setlistners();
	
	}

	private void setlistners() {
		// TODO Auto-generated method stub
		
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		b5.setOnClickListener(this);
		b6.setOnClickListener(this);
		b7.setOnClickListener(this);
		b8.setOnClickListener(this);
		b9.setOnClickListener(this);
		b0.setOnClickListener(this);
		ba.setOnClickListener(this);
		bm.setOnClickListener(this);
		bn.setOnClickListener(this);
		bpo.setOnClickListener(this);
		bx.setOnClickListener(this);
		bxs.setOnClickListener(this);
		be.setOnClickListener(this);
		bd.setOnClickListener(this);
		bt.setOnClickListener(this);
		bcos.setOnClickListener(this);
		btan.setOnClickListener(this);
		bsin.setOnClickListener(this);
		bpi.setOnClickListener(this);
		bre.setOnClickListener(this);
		bmd.setOnClickListener(this);
		bb.setOnClickListener(this);
		bf.setOnClickListener(this);
		//bclear.setOnClickListener(this);
		bc.setOnClickListener(this);
		bo.setOnClickListener(this);
		s.setOnItemSelectedListener(this);
		bncr.setOnClickListener(this);
		/*
		tb.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(tb.isChecked()){
					sumb = true;
				}else{
					sumb = false;
				}
			}
		});*/

		input.setOnTouchListener(new View.OnTouchListener(){

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				input.setText("");
				return false;
			}
			
			
		});
		
	}

	private void testnewclass() {
		// TODO Auto-generated method stub
		
	}

	private void setup() {
		// TODO Auto-generated method stub
		
		b1 = (Button) findViewById(R.id.b1);
		b2 = (Button) findViewById(R.id.b2);
		b3 = (Button) findViewById(R.id.b3);
		b4 = (Button) findViewById(R.id.b4);
		b5 = (Button) findViewById(R.id.b5);
		b6 = (Button) findViewById(R.id.b6);
		b7 = (Button) findViewById(R.id.b7);
		b8 = (Button) findViewById(R.id.b8);
		b9 = (Button) findViewById(R.id.b9);
		b0 = (Button) findViewById(R.id.b0);
		ba = (Button) findViewById(R.id.ba);
		bm = (Button) findViewById(R.id.bm);
		bo = (Button) findViewById(R.id.bo);
		bc = (Button) findViewById(R.id.bc);
		bx = (Button) findViewById(R.id.bx);
		bxs = (Button) findViewById(R.id.bxs);
		bpo = (Button) findViewById(R.id.bpo);
		bb = (Button) findViewById(R.id.bb);
		bn = (Button) findViewById(R.id.bn);
		bre = (Button) findViewById(R.id.bre);
		bt = (Button) findViewById(R.id.bt);
		bd = (Button) findViewById(R.id.bd);
		be = (Button) findViewById(R.id.be);
		bf = (Button) findViewById(R.id.bf);
		bpi = (Button) findViewById(R.id.bpi);
		bmd = (Button) findViewById(R.id.bmd);
		bcos = (Button) findViewById(R.id.bcos);
		btan = (Button) findViewById(R.id.btan);
		bsin = (Button) findViewById(R.id.bsin);
		bncr = (Button) findViewById(R.id.bncr);
		input = (TextView) findViewById(R.id.input);
		output = (TextView) findViewById(R.id.output);
		//tb = (ToggleButton) findViewById(R.id.toggleButton1);
		s = (Spinner) findViewById(R.id.spinner1);
		s.setSelection(2);
		//bclear = (Button) findViewById(R.id.bclear);
		
		
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		inputString = input.getText().toString();

		switch (arg0.getId()) {
		case R.id.b1:
			inputString = inputString.concat("1");
			input.setText(inputString);
			break;

		case R.id.b2:
			inputString = inputString.concat("2");
			input.setText(inputString);
			break;

		case R.id.b3:
			inputString = inputString.concat("3");
			input.setText(inputString);
			break;

		case R.id.b4:
			inputString = inputString.concat("4");
			input.setText(inputString);
			break;
		
		case R.id.b5:
			inputString = inputString.concat("5");
			input.setText(inputString);
			break;
			
		case R.id.b6:
			inputString = inputString.concat("6");
			input.setText(inputString);
			break;
			
		case R.id.b7:
			inputString = inputString.concat("7");
			input.setText(inputString);
			break;
		
		case R.id.b8:
			inputString = inputString.concat("8");
			input.setText(inputString);
			break;
			
		case R.id.b9:
			inputString = inputString.concat("9");
			input.setText(inputString);
			break;
			
		case R.id.b0:
			inputString = inputString.concat("0");
			input.setText(inputString);
			break;
			
		case R.id.bm:
			inputString = inputString.concat(" - ");
			input.setText(inputString);
			break;
			
		case R.id.ba:
			inputString = inputString.concat(" + ");
			input.setText(inputString);
			break;
			
		case R.id.bd:
			inputString = inputString.concat(" / ");
			input.setText(inputString);
			break;
			
		case R.id.bt:
			inputString = inputString.concat(" * ");
			input.setText(inputString);
			break;
			
		case R.id.be:
			inputString = inputString.concat("e");
			input.setText(inputString);
			break;	
			
		case R.id.bo:
			inputString = inputString.concat(" ( ");
			input.setText(inputString);
			break;
			
		case R.id.bsin:
			inputString = inputString.concat(" sin( ");
			input.setText(inputString);
			break;
			
		case R.id.bcos:
			inputString = inputString.concat(" cos( ");
			input.setText(inputString);
			break;
			
		case R.id.btan:
			inputString = inputString.concat(" tan( ");
			input.setText(inputString);
			break;
			
		case R.id.bc:
			inputString = inputString.concat(" ) ");
			input.setText(inputString);
			break;	
			
		case R.id.bmd:
			inputString = inputString.concat("-");
			input.setText(inputString);
			break;	
			
		case R.id.bpi:
			inputString = inputString.concat(" "+Character.toString((char) 960)+" ");
			input.setText(inputString);
			break;	
			
		case R.id.bx:
			inputString = inputString.concat("x");
			input.setText(inputString);
			break;
			
		case R.id.bxs:
			inputString = inputString.concat(" ^ ");
			input.setText(inputString);
			break;
			
		case R.id.bncr:
			inputString = inputString.concat(" C ");
			input.setText(inputString);
			break;
			
		case R.id.bpo:
			inputString = inputString.concat(".");
			input.setText(inputString);
			break;
			
		case R.id.bre:
			Class ourClass;
			try {
				ourClass = Class.forName("com.example.sammy.summer.MainActivity");
				Intent intent = new Intent(MainActivity.this,ourClass);
				startActivity(intent);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case R.id.bf:
			inputString = inputString.concat(" ! ");
			input.setText(inputString);
			break;
			
		case R.id.bb:
			
			if(inputString.length()>0){
				inputString = inputString.substring(0, inputString.length()-1);
				input.setText(inputString);
			}
			break;
			
		
			
		case R.id.bn:
			
			
			
			
			if(Equation.length()<1){
				Equation = inputString;
				input.setText("");
				output.setText(inputString);
				
				if(position == 2){
					Log.i("d", "not summed");
					Summer sum = new Summer(Equation, lowlim, uplim, position);
					double answer = sum.getAnswer();
					//output.setText(Double.toString(answer));
					String temp = output.getText().toString();
					Log.i("d", Double.toString(answer));
					temp = temp.concat("\ngives "+Double.toString(answer));
					output.setText(temp);
				}else{
					input.setHint("Type Lower Limit");
				}
				
				
			}else if (lowlim == -1543 && position != 2){
				lowlim = Integer.parseInt(inputString);
				input.setText("");
				
				String temp = output.getText().toString();
				
				temp = temp.concat("\nfrom "+inputString);
				output.setText(temp);
				input.setHint("Type Upper Limit");
			}else if (uplim == -1543 && position != 2){
				
				uplim = Integer.parseInt(inputString);
				
				
				Summer sum = new Summer(Equation, lowlim, uplim, position);
				double answer = sum.getAnswer();
				//output.setText(Double.toString(answer));
				String temp = output.getText().toString();
				temp = temp.concat("\nto "+inputString);
				temp = temp.concat("\n to give "+Double.toString(answer));
				output.setText(temp);
			}else{
				output.setText("");
				input.setText("");
			}
			
			
			
			break;
			
		

		
			
			
		
			
		

		}

	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		position = s.getSelectedItemPosition();
		
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
