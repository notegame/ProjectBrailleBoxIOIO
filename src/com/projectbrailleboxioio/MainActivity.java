package com.projectbrailleboxioio;

import braillesLib.BrailleDB;

import com.projectbrailleboxioio.R;
import com.projectbrailleboxioio.IOInput;

import fulltext.TextSplit;

import ioio.lib.api.DigitalInput;
import ioio.lib.api.DigitalOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOActivity;
import ioiohelper.Mode;
import ioiohelper.button.IOIOButton;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends IOIOActivity {

	public EditText Text;
	public EditText Text_bitString;
	public CheckBox checkbox1;
	public TextView textView1;
	public Thread th;
	public Handler mChildHandler;
	
	public Mode mode;
	public TTS tts;
	public BrailleDB brailleDB;
	
	private IOIOButton btn_mode;
	
	public Context context;
	public Activity activity;
	
	private int mode_count = 0;
	
	private MediaPlayer mp_;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.context = this;
		this.activity = this;
		
		btn_mode = new IOIOButton();
		
		checkbox1 = (CheckBox) findViewById(R.id.checkBox1);
		
		//set TTS
		tts = TTS.getInstance();
		tts.setTTS(this);
		
		
		TextSplit.getInstance().init(this);
		
		//load braille file
		brailleDB = BrailleDB.getInstance();
		brailleDB.LoadAllFile(this);
		
		set_test();

		mode = new SpellMode(this,this);
		
	}
	
	class Looper extends BaseIOIOLooper {
		/** The on-board LED. */
		private DigitalOutput led_;
		private DigitalInput inputB11,inputB12,inputB13,inputB14,inputB15,inputB16;
		private DigitalInput inputB21,inputB22,inputB23,inputB24,inputB25,inputB26;
		private DigitalInput inputB31,inputB32,inputB33,inputB34,inputB35,inputB36;
		private DigitalInput inputC1,inputC2,inputC3,inputC4,inputC5,inputC6;

		@Override
		protected void setup() throws ConnectionLostException {
			led_ = ioio_.openDigitalOutput(0, true);
			
			inputB11 = ioio_.openDigitalInput(1, DigitalInput.Spec.Mode.PULL_UP);
			inputB12 = ioio_.openDigitalInput(2, DigitalInput.Spec.Mode.PULL_UP);
			inputB13 = ioio_.openDigitalInput(3, DigitalInput.Spec.Mode.PULL_UP);
			inputB14 = ioio_.openDigitalInput(4, DigitalInput.Spec.Mode.PULL_UP);
			inputB15 = ioio_.openDigitalInput(5, DigitalInput.Spec.Mode.PULL_UP);
			inputB16 = ioio_.openDigitalInput(6, DigitalInput.Spec.Mode.PULL_UP);
			
			inputB21 = ioio_.openDigitalInput(7, DigitalInput.Spec.Mode.PULL_UP);
			inputB22 = ioio_.openDigitalInput(8, DigitalInput.Spec.Mode.PULL_UP);
			inputB23 = ioio_.openDigitalInput(9, DigitalInput.Spec.Mode.PULL_UP);
			inputB24 = ioio_.openDigitalInput(10, DigitalInput.Spec.Mode.PULL_UP);
			inputB25 = ioio_.openDigitalInput(11, DigitalInput.Spec.Mode.PULL_UP);
			inputB26 = ioio_.openDigitalInput(12, DigitalInput.Spec.Mode.PULL_UP);
			
			inputB31 = ioio_.openDigitalInput(13, DigitalInput.Spec.Mode.PULL_UP);
			inputB32 = ioio_.openDigitalInput(14, DigitalInput.Spec.Mode.PULL_UP);
			inputB33 = ioio_.openDigitalInput(15, DigitalInput.Spec.Mode.PULL_UP);
			inputB34 = ioio_.openDigitalInput(16, DigitalInput.Spec.Mode.PULL_UP);
			inputB35 = ioio_.openDigitalInput(17, DigitalInput.Spec.Mode.PULL_UP);
			inputB36 = ioio_.openDigitalInput(18, DigitalInput.Spec.Mode.PULL_UP);
			
			inputC1 = ioio_.openDigitalInput(31, DigitalInput.Spec.Mode.PULL_UP);
			inputC2 = ioio_.openDigitalInput(32, DigitalInput.Spec.Mode.PULL_UP);
			inputC3 = ioio_.openDigitalInput(33, DigitalInput.Spec.Mode.PULL_UP);
			inputC4 = ioio_.openDigitalInput(34, DigitalInput.Spec.Mode.PULL_UP);
			inputC5 = ioio_.openDigitalInput(35, DigitalInput.Spec.Mode.PULL_UP);
			inputC6 = ioio_.openDigitalInput(36, DigitalInput.Spec.Mode.PULL_UP);
			
		}

		
		@Override
		public void loop() throws ConnectionLostException {
			
			try {
				if(checkbox1.isChecked())
				{
					IOInput.input11 = !inputB11.read();
					IOInput.input12 = !inputB12.read();
					IOInput.input13 = !inputB13.read();
					IOInput.input14 = !inputB14.read();
					IOInput.input15 = !inputB15.read();
					IOInput.input16 = !inputB16.read();
					
					IOInput.input21 = !inputB21.read();
					IOInput.input22 = !inputB22.read();
					IOInput.input23 = !inputB23.read();
					IOInput.input24 = !inputB24.read();
					IOInput.input25 = !inputB25.read();
					IOInput.input26 = !inputB26.read();
					
					IOInput.input31 = !inputB31.read();
					IOInput.input32 = !inputB32.read();
					IOInput.input33 = !inputB33.read();
					IOInput.input34 = !inputB34.read();
					IOInput.input35 = !inputB35.read();
					IOInput.input36 = !inputB36.read();
					
					IOInput.enter 	= !inputC1.read();
					IOInput.add 	= !inputC2.read();
					IOInput.del 	= !inputC3.read();
					IOInput.clear 	= !inputC4.read();
					IOInput.lang 	= !inputC5.read();
					IOInput.mode 	= !inputC6.read();
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//System.out.println("IOInput.input1>>"+IOInput.input1);
			
			
			runOnUiThread(new Runnable() {        
			    @Override
			    public void run() 
				{ 
			    	mode.loop();
			    	
			    	if(btn_mode.onKeyPress(IOInput.mode))
					{
			    		mode_count++;
			    		
			    		if(mode_count>1) mode_count = 0;
			    		
			    		if(mode_count == 0)
			    		{
			    			tts.getInstance().speech("");
			    			mp_ = MediaPlayer.create(context, R.raw.mode_spell);
			    			mp_.start();
			    			mode = new SpellMode(context,activity);
			    		}else
		    			if(mode_count == 1)
			    		{
			    			mode = new ExampleMode(context,activity);
			    		}
			    		
			    		
					}
			    	
				}
			});
			
			/*runOnUiThread(new Runnable() {            
			    @Override
			    public void run() 
				{ 
			    	try {
			    		String text = in1.read() ? "True" : "False";
						textView1.setText(text);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ConnectionLostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});*/
			
		}
	}
	
	private void set_test(){
		
		/*
		//---------------------------------------------------------
		Button button1 = (Button) findViewById(R.id.button2);
		button1.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.input11 = true;
            	}else{
            		IOInput.input11 = false;
            	}
                return false;
            }
        });
		Button button2 = (Button) findViewById(R.id.button3);
		button2.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.input12 = true;
            	}else{
            		IOInput.input12 = false;
            	}
                return false;
            }
        });
		Button button3 = (Button) findViewById(R.id.button4);
		button3.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.input13 = true;
            	}else{
            		IOInput.input13 = false;
            	}
                return false;
            }
        });
		Button button4 = (Button) findViewById(R.id.button5);
		button4.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.input14 = true;
            	}else{
            		IOInput.input14 = false;
            	}
                return false;
            }
        });
		Button button5 = (Button) findViewById(R.id.button6);
		button5.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.input15 = true;
            	}else{
            		IOInput.input15 = false;
            	}
                return false;
            }
        });
		Button button6 = (Button) findViewById(R.id.button7);
		button6.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.input16 = true;
            	}else{
            		IOInput.input16 = false;
            	}
                return false;
            }
        });
		
		//---------------------------------------------------------
		Button button21 = (Button) findViewById(R.id.button8);
		button21.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.input21 = true;
            	}else{
            		IOInput.input21 = false;
            	}
                return false;
            }
        });
		Button button22 = (Button) findViewById(R.id.button9);
		button22.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.input22 = true;
            	}else{
            		IOInput.input22 = false;
            	}
                return false;
            }
        });
		Button button23 = (Button) findViewById(R.id.button10);
		button23.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.input23 = true;
            	}else{
            		IOInput.input23 = false;
            	}
                return false;
            }
        });
		Button button24 = (Button) findViewById(R.id.button11);
		button24.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.input24 = true;
            	}else{
            		IOInput.input24 = false;
            	}
                return false;
            }
        });
		Button button25 = (Button) findViewById(R.id.button12);
		button25.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.input25 = true;
            	}else{
            		IOInput.input25 = false;
            	}
                return false;
            }
        });
		Button button26 = (Button) findViewById(R.id.button13);
		button26.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.input26 = true;
            	}else{
            		IOInput.input26 = false;
            	}
                return false;
            }
        });
		
		//---------------------------------------------------------
		Button button31 = (Button) findViewById(R.id.button14);
		button31.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.input31 = true;
            	}else{
            		IOInput.input31 = false;
            	}
                return false;
            }
        });
		Button button32 = (Button) findViewById(R.id.button15);
		button32.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.input32 = true;
            	}else{
            		IOInput.input32 = false;
            	}
                return false;
            }
        });
		Button button33 = (Button) findViewById(R.id.button16);
		button33.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.input33 = true;
            	}else{
            		IOInput.input33 = false;
            	}
                return false;
            }
        });
		Button button34 = (Button) findViewById(R.id.button17);
		button34.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.input34 = true;
            	}else{
            		IOInput.input34 = false;
            	}
                return false;
            }
        });
		Button button35 = (Button) findViewById(R.id.button18);
		button35.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.input35 = true;
            	}else{
            		IOInput.input35 = false;
            	}
                return false;
            }
        });
		Button button36 = (Button) findViewById(R.id.button19);
		button36.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.input36 = true;
            	}else{
            		IOInput.input36 = false;
            	}
                return false;
            }
        });
		
		//---------------------------------------------------
		Button enter = (Button) findViewById(R.id.button1);
		enter.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.enter = true;
            	}else{
            		IOInput.enter = false;
            	}
                return false;
            }
        });
		Button add = (Button) findViewById(R.id.Button01);
		add.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.add = true;
            	}else{
            		IOInput.add = false;
            	}
                return false;
            }
        });
		Button del = (Button) findViewById(R.id.Button02);
		del.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.del = true;
            	}else{
            		IOInput.del = false;
            	}
                return false;
            }
        });
		Button clear = (Button) findViewById(R.id.Button03);
		clear.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.clear = true;
            	}else{
            		IOInput.clear = false;
            	}
                return false;
            }
        });
		
		Button switchlang = (Button) findViewById(R.id.Button04);
		switchlang.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.lang = true;
            	}else{
            		IOInput.lang = false;
            	}
            	 
                return false;
               
            }
        });
		
		Button switchMode = (Button) findViewById(R.id.Button05);
		switchMode.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	
            	if(event.getAction()  == 2)
            	{
            		IOInput.mode = true;
            	}else{
            		IOInput.mode = false;
            	}
            	 
                return false;
               
            }
        });
		*/
	}
	
	
	@Override
	protected IOIOLooper createIOIOLooper() {
		return new Looper();
	}

}
