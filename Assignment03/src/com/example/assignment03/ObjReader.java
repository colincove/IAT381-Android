package com.example.assignment03;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class ObjReader {
	private String url;
	private String rawData;
	private int resId;
	private InputStream stream;
	private Resources res;
	public ObjReader(int resId, Resources res) {
		this.resId=resId;
	    this.res=res;
	}
	public int getResId(){
		return resId;
	}
	public void read(IRecieveMesh reciever, Looper looper){
		Thread requestThread = new GetFileThread(new MessageHandler(looper, this, reciever));
		//requestThread.start();
	
	}
	class MessageHandler extends Handler{
		private IRecieveMesh reciever;
		private ObjReader reader;
		public MessageHandler(Looper looper, ObjReader reader, IRecieveMesh reciever){
			super(looper);
			this.reader=reader;
			this.reciever = reciever;
		}
		@ Override
		public void handleMessage(Message inputMessage){
			String stringData = inputMessage.getData().getString("mesh");
			ObjData data=new ObjData();
			// convert String into InputStream
			InputStream is = new ByteArrayInputStream(stringData.getBytes());
			 BufferedReader br = null;
				// convert String into InputStream
			

				br = new BufferedReader(new InputStreamReader(is));

				    try {
				        String line = null;
				        StringBuilder sb = new StringBuilder();
				        while ((line = br.readLine()) != null) {
				            parseLine(line, data);
				        }
				        
				    } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
				        try {
							br.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
			reciever.recieveMesh(data);
		}
	}
	private void parseLine(String line, ObjData data){
		if(line!=null){
		if(line.length()!=0){
			char firstChar = line.charAt(0);
			String[] lineData = line.replace("  ", " ").split(" ");
			
			if(firstChar=='f'){
				//face
				data.addFace(
						Integer.parseInt(lineData[1]), 
						Integer.parseInt(lineData[2]), 
						Integer.parseInt(lineData[3]));
			}else if(firstChar=='v'){
				//Vertices
				data.addVertice(
						Float.parseFloat(lineData[1]), 
						Float.parseFloat(lineData[2]), 
						Float.parseFloat(lineData[3]));
			}else if(firstChar=='#'){
				//comment
			}
		}
		}
	}
	class GetFileThread extends Thread {
		
		private Handler handler;
		private ObjReader reader;
		public GetFileThread(Handler handler) {
			super("Read File Thread");
			this.handler=handler;
			run();
		}
		@Override
		public void run() {
		    try {
		        InputStream in_s = res.openRawResource(resId);

		        byte[] b = new byte[in_s.available()];
		        in_s.read(b);
		        rawData = new String(b);
		    } catch (Exception e) {
		        // e.printStackTrace();
		    	rawData = "";
		    }
			 BufferedReader br = null;
			// convert String into InputStream
			InputStream is = new ByteArrayInputStream(rawData.getBytes());

			br = new BufferedReader(new InputStreamReader(is));

			    try {
			        String line = null;
			        StringBuilder sb = new StringBuilder();
			        while ((line = br.readLine()) != null) {
			            //line = br.readLine();
			        	sb.append(line + "\n");
			            //parseLine(line, data);
			        }
			        String result = sb.toString();
			        Message msg = new Message();
					Bundle bundle = new Bundle();
					bundle.putString("mesh", result);
					msg.setData(bundle);
					handler.sendMessage(msg);
			    } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
			        try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
		}
		}

}
