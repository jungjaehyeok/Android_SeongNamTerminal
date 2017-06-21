package com.example.junghanra.seongnam;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

import static android.R.attr.x;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


public class terminal_1 extends AppCompatActivity{
    ListView listView;
    static Vector<String> RouteidVector;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminal_1);
        listView = (ListView)findViewById(R.id.listview1);

        final ArrayList<Response> list = xmlParser();

        final String[] data = new String[list.size()];
        for(int i=0;i<list.size();i++) {
            data[i] = list.get(i).getarrplacenm();

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                data);
        listView.setAdapter(adapter);
        EditText editTextFilter = (EditText)findViewById(R.id.editTextFilter) ;
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString() ; if (filterText.length() > 0)
                { listView.setFilterText(filterText) ; } else { listView.clearTextFilter() ; }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

        }) ;
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String content = RouteidVector.get(position);
                Intent intent = new Intent(getApplicationContext(), terminal_2.class);
                intent.putExtra("content",content);
                startActivity(intent);

            }
        }) ;



    }

    protected ArrayList<Response> xmlParser()  {
        RouteidVector = new Vector<String>();
        ArrayList<Response> arrayList = new ArrayList<Response>();
        InputStream is = getResources().openRawResource(R.raw.intercity_bus);

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
                        if(startTag.equals("arrplacenm")) {
                            items.setarrplacenm(parser.nextText());
                        }
                        if(startTag.equals("routeid")){
                            items.setrouteid(parser.nextText());
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        String endTag = parser.getName();
                        if(endTag.equals("items")) {
                            RouteidVector.add(items.getrouteid());
                            arrayList.add(items);



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




    public void onClick_back(View v){
        finish();
    }

}