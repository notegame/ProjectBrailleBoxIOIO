package com.projectbrailleboxioio;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import com.projectbrailleboxioio.R;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import android.net.Uri;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;


public class TTS implements TextToSpeech.OnInitListener, OnUtteranceCompletedListener{
	  private static TTS uniqInstance;
	  
	  private MediaPlayer mediaPlayer; 
	  
	  private TextToSpeech mTts;
	  
	  private Context context;
	  
	  private boolean saving = false;
	  
	  private MediaPlayer mp;
	  
	  private TTS() {
		  System.out.println("Create TTS");
	  }
	  
	  public void speechGoogle(String Text,String Language)
	  {
			mediaPlayer = new MediaPlayer();
			//String url = Uri.parse("http://translate.google.com/translate_tts?tl="+Language+"&q="+Text.replace(" ", "-")).toString();
			String url = Uri.parse("http://buysteamgame.com/translate/"+Language+"/"+Text.replace(" ", "-")+"/speech.mp3").toString();
			
			try {
				mediaPlayer = new MediaPlayer();
				mediaPlayer.setVolume(1, 1);
			    mediaPlayer.setDataSource(url);
			    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
				    @Override
				    public void onPrepared(MediaPlayer mp) {
				    mediaPlayer.start();
				    }
			    });
			    mediaPlayer.prepare();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  
	  public void setTTS(Context context)
	  {
		  this.context = context;
		  
		  mTts = new TextToSpeech(context,
		            this  // TextToSpeech.OnInitListener
		  );
	  }
	  
	  public boolean isSpeaking()
	  {
		  return mTts.isSpeaking();
		  /*
		  try{
			  if(mp.isPlaying() || saving)
			  {
				  return true;
			  }else{
				  return false;
			  }
		  }catch (Exception e) {
			  return false;
		}*/
	  }
	  
	  public void speech(String Text)
	  {
		// Select a random hello.
        mTts.speak(Text,
            TextToSpeech.QUEUE_FLUSH,  // Drop all pending entries in the playback queue.
            null);
	    /*saving = true;
        save(Text);
        play();*/
	  }

	 public static synchronized TTS getInstance() {
	    if (uniqInstance == null) {
	      uniqInstance = new TTS();
	    }
	    return uniqInstance;
	  }
	 
	 public void save(String Text)
	 {
		 HashMap<String, String> myHashRender = new HashMap<String, String>();
		 myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "SAVE");
	      

		 String exStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath();
		 File appTmpPath = new File(exStoragePath + "/sounds/");
		 appTmpPath.mkdirs();
		 String tempFilename = "tmpaudio.wav";
		 
		 String tempDestFile = appTmpPath.getAbsolutePath() + "/" + tempFilename;
		 
		 File file = new File(tempDestFile);
		 file.delete();
		 
		 mTts.synthesizeToFile(Text, myHashRender, tempDestFile);
	 }
	 
	 public void play()
	 {
		String exStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath();
		File appTmpPath = new File(exStoragePath + "/sounds/");
		appTmpPath.mkdirs();
		String tempFilename = "tmpaudio.wav";
		String tempDestFile = appTmpPath.getAbsolutePath() + "/" + tempFilename;
		
		
		try{
			mp = MediaPlayer.create(context, Uri.fromFile(new File(tempDestFile)));
			mp.start();
			
			while(mp.isPlaying())
			{
				
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		saving = false;
		
	 }
	 
	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		// status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
        if (status == TextToSpeech.SUCCESS) {
            // Set preferred language to US english.
            // Note that a language may not be available, and the result will indicate this.
        	mTts.setOnUtteranceCompletedListener(this);
            int result = mTts.setLanguage(Locale.US);
            // Try this someday for some interesting results.
            // int result mTts.setLanguage(Locale.FRANCE);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED) {
            } else {
            	// Select a random hello.
                speech(context.getResources().getString(R.string.say_ready));
            }
        }
	}
	
	// It's callback
	public void onUtteranceCompleted(String utteranceId) {
	   if(utteranceId.equals("SAVE"))
	   {
		   play();
	   }
	}

}
