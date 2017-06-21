package com.example.junghanra.seongnam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Vector;


public class expressbus_2 extends AppCompatActivity {
    GridView gridView;
    Vector<String> Depplandtime;
    Vector<String> Level;
    Vector<String> Charge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expressbus_2);


        gridView = (GridView) findViewById(R.id.gridview2);

        ArrayList<Response> list =xmlParser();

        final String[] data = new String[Depplandtime.size()];
        for(int i = 0; i<Depplandtime.size();i++){
            data[i]=Depplandtime.get(i)+" ("+Level.get(i)+")";

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, data);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Toast t = Toast.makeText(expressbus_2.this,"요금:"+Charge.get(position)+"원",Toast.LENGTH_LONG);
                t.show();

            }
        }) ;
    }



    protected ArrayList<Response> xmlParser()  {
        Depplandtime = new Vector<String>();
        Level = new Vector<String>();
        Charge = new Vector<String>();
        ArrayList<Response> arrayList = new ArrayList<Response>();
        InputStream is = getResources().openRawResource(R.raw.detailed_express_bus);
        Intent intent = getIntent();
        String data1 = intent.getExtras().getString("content");


        // xmlPullParser
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new InputStreamReader(is, "UTF-8"));
            int eventType = parser.getEventType();
            Response items = null;

            while(eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String startTag = parser.getName();
                        if(startTag.equals("items")) {
                            items = new Response();

                        }
                        if (startTag.equals("routeid")) {
                            items.setrouteid(parser.nextText());
                            Log.d("TEST", items.getrouteid());

                        }
                        if (startTag.equals("depplandtime")) {
                            items.setdepplandtime(parser.nextText());

                        }
                        if (startTag.equals("level")) {
                            items.setlevel(parser.nextText());

                        }
                        if (startTag.equals("charge")) {
                            items.setcharge(parser.nextText());

                        }

                        break;
                    case XmlPullParser.END_TAG:
                        String endTag = parser.getName();
                        if(endTag.equals("items")) {
                            arrayList.add(items);
                            if(data1.equals(items.getrouteid())){
                                Depplandtime.add(items.getdepplandtime());
                                Level.add(items.getlevel());
                                Charge.add(items.getcharge());
                            }
                        }
                        break;
                }
                eventType = parser.next();
            }


        }catch(XmlPullParserException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

}
