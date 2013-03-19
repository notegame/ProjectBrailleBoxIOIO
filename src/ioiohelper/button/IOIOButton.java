package ioiohelper.button;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.projectbrailleboxioio.IOInput;

public class IOIOButton {
	
	private Boolean press = false;
	private Boolean return_press_value = false;
	private Boolean return_hold_value = false;
	private Boolean return_hold_once_value = false;
	private int press_count;
	private int hold_count;
	private int hold_once_count;
	
	public Boolean onKeyPress(Boolean IOInput)
	{
		if(IOInput)
		{
			press_count = 0;
			press = true;
		}else{
			if(press == true)
			{
				return_press_value = true;
				press = false;
			}
			if(press_count > 0)
			{
				return_press_value = false;
			}
			press_count++;
		}
		return return_press_value;
	}
	
	public Boolean onKeyUp(Boolean IOInput)
	{
		return !IOInput;
	}
	
	public Boolean onKeyDown(Boolean IOInput)
	{
		return IOInput;
	}
	
	public Boolean onKeyDown(Boolean IOInput,int sec)
	{
		if(!IOInput) {
			hold_count = 0;
			return_hold_value = false;
		}else{
			hold_count++;
			
			if(hold_count >= sec)
			{
				return_hold_value = true;
			}else{
				try {
					Thread.currentThread();
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return return_hold_value;
	}
	
	public Boolean onKeyDownOnce(Boolean IOInput,int sec)
	{
		if(!IOInput) {
			hold_once_count = 0;
			return_hold_once_value = false;
		}else{
			hold_once_count++;
			
			if(hold_once_count == sec)
			{
				return_hold_once_value = true;
			}else{
				try {
					Thread.currentThread();
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return_hold_once_value = false;
			}
		}
		
		return return_hold_once_value;
	}
	
}
