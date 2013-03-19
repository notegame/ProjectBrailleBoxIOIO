package ioiohelper;

import android.content.Context;
import braillesLib.BrailleDB;
import com.projectbrailleboxioio.TTS;

public class Mode extends Object{
	
	public TTS tts;
	public Context context;
	public BrailleDB brailleDB;
	
	public Mode(Context context) {
		
		this.context = context;
		tts = TTS.getInstance();
		
		brailleDB = BrailleDB.getInstance();
	}
	
	public void loop()
	{
		
	}
}
