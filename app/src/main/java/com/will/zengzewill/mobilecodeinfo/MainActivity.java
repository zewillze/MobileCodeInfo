package com.will.zengzewill.mobilecodeinfo;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.ListActivity;

import com.will.zengzewill.mobilecodeinfo.api.FetchInfoApi;
import com.will.zengzewill.mobilecodeinfo.database.MobileDataSource;
import com.will.zengzewill.mobilecodeinfo.model.InfoModel;
import com.will.zengzewill.mobilecodeinfo.protocol.MobileCodeInfoDelegate;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MobileCodeInfoDelegate{


    private TextView numberText;
    private TextView provinceText;
    private TextView cityText;
    private TextView typeText;

    private ListView listView;


    private FetchInfoApi api;
    private MobileDataSource dataSource;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        Build UI
        buildUI();
        dataSource = new MobileDataSource(this);
        dataSource.open();

        List<InfoModel> values = dataSource.getAllInfos();

        ArrayAdapter<InfoModel> adapter = new ArrayAdapter<InfoModel>(this, android.R.layout.simple_list_item_1, values);


    }

    private void buildUI(){
        numberText = (TextView)findViewById(R.id.number_text);
        provinceText = (TextView)findViewById(R.id.province_text);
        cityText = (TextView)findViewById(R.id.city_text);
        typeText = (TextView)findViewById(R.id.type_text);
        listView = (ListView)findViewById(R.id.list);


//       Set watcher
        numberText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() == 11) {
                    /* Request Info */
                    fetchCodeInfo(s.toString());
                }
                else if(s.length() == 0){
                    clearnUI();
                }
            }
        });
    }


    public void fetchCodeInfo(String code){
        api = new FetchInfoApi();
        api.delegate = this;
        api.execute(code.toString());
    }

    private void clearnUI(){
        provinceText.setText("");
        cityText.setText("");
        typeText.setText("");
    }
    @Override
    public void didFinishFetchInfo(InfoModel model) {
        bindData(model);
    }


    @Override
    public void didFailInfo(String err) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(err)
                .show();
    }

    public void bindData(InfoModel model){
        provinceText.setText(model.province);
        cityText.setText(model.city);
        typeText.setText(model.supplier + "-" + model.suit);
    }
}
