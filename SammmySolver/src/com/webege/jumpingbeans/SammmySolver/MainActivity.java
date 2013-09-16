package com.webege.jumpingbeans.SammmySolver;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;

public class MainActivity extends Activity implements OnClickListener{

	Button button,b1,b2,b3,b4;
	TextView input;
	TextView output;
	Solver solver;
	String inputString;
	Button b5,b6,b7,b8,b9,b0,ba,bm,be,bx,bxs,bb,bp,bsin,bcos;
	private Button btan;
	private Button bcossq;
	private Button btansq;
	private Button bsinsq;
	private Button bclear;
	Class ourClass;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		setup();
		solver = new Solver("ready");
		testnewclass();
		setlistners();
	
	}

	private void setlistners() {
		// TODO Auto-generated method stub
		button.setOnClickListener(this);
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
		be.setOnClickListener(this);
		bx.setOnClickListener(this);
		bxs.setOnClickListener(this);
		bp.setOnClickListener(this);
		bb.setOnClickListener(this);
		bsin.setOnClickListener(this);
		bcos.setOnClickListener(this);
		btan.setOnClickListener(this);
		bcossq.setOnClickListener(this);
		btansq.setOnClickListener(this);
		bsinsq.setOnClickListener(this);
		bclear.setOnClickListener(this);

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
		String tb = solver.output();
		output.setText(tb);

	}

	private void setup() {
		// TODO Auto-generated method stub
		button = (Button) findViewById(R.id.bs);
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
		be = (Button) findViewById(R.id.be);
		bx = (Button) findViewById(R.id.bx);
		bxs = (Button) findViewById(R.id.bxs);
		bp = (Button) findViewById(R.id.bp);
		bb = (Button) findViewById(R.id.bb);
		bcos = (Button) findViewById(R.id.bcos);
		bsin = (Button) findViewById(R.id.bsin);
		input = (TextView) findViewById(R.id.input);
		output = (TextView) findViewById(R.id.output);
		bcos = (Button) findViewById(R.id.bcos);
		bsin = (Button) findViewById(R.id.bsin);
		btan = (Button) findViewById(R.id.btan);
		bcossq = (Button) findViewById(R.id.bcossq);
		btansq = (Button) findViewById(R.id.btansq);
		bsinsq = (Button) findViewById(R.id.bsinsq);
		bclear = (Button) findViewById(R.id.bclear);
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
			
		case R.id.be:
			inputString = inputString.concat(" = ");
			input.setText(inputString);
			break;
			
		case R.id.bx:
			inputString = inputString.concat("x");
			input.setText(inputString);
			break;
			
		case R.id.bxs:
			inputString = inputString.concat("x"+Character.toString((char) 178));
			input.setText(inputString);
			break;
			
		case R.id.bp:
			inputString = inputString.concat(".");
			input.setText(inputString);
			break;
			
		case R.id.bb:
			if(inputString.length()>0){
				inputString = inputString.substring(0, inputString.length()-1);
				input.setText(inputString);
			}
			break;
			
		

		case R.id.bs:
			solver.input(inputString,getBaseContext());
			String tb = solver.solve(getApplicationContext());
			//output.setText(String.format("the value of x is %s", tb));
			output.setText(tb);
			break;
			
		case R.id.bclear:
			
			input.setText("");
			
			break;
			
		case R.id.bsin:
			inputString = inputString.concat("sinx");
			input.setText(inputString);
			break;
			
		case R.id.bsinsq:
			inputString = inputString.concat("sin"+Character.toString((char) 178)+"x");
			input.setText(inputString);
			break;
			
		case R.id.bcos:
			inputString = inputString.concat("cosx");
			input.setText(inputString);
			break;
			
		case R.id.bcossq:
			inputString = inputString.concat("cos"+Character.toString((char) 178)+"x");
			input.setText(inputString);
			break;
			
		case R.id.btan:
			inputString = inputString.concat("tanx");
			input.setText(inputString);
			break;
			
		case R.id.btansq:
			inputString = inputString.concat("tan"+Character.toString((char) 178)+"x");
			input.setText(inputString);
			break;

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater blowup = getMenuInflater();
		blowup.inflate(R.menu.basemenu, menu);
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		Class ourClass;
		try {
			ourClass = Class.forName("com.webege.jumpingbeans.SammmySolver.Prefs");
			Intent intent = new Intent(MainActivity.this,ourClass);
			startActivity(intent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
}
