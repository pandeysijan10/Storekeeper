package pandeysijan10.com.np.storekeeper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Storekeeper extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static SQLiteDatabase db;
    private static Cursor c;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(mRecyclerView.getVisibility()== View.INVISIBLE) {
                        mRecyclerView.setVisibility(View.VISIBLE);
                    }
                    return true;
                case R.id.navigation_sold:
                    mRecyclerView.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storekeeper);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        mRecyclerView = findViewById(R.id.list);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        db = openOrCreateDatabase("ProductDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS product(productname VARCHAR,amount INTEGER);");
        c=db.rawQuery("SELECT * FROM product", null);
                if(c.getCount()==0) {
                   //Data in Nepali language. Some translations: चाउचाउ means dry noodles,साबुन means soap,बिस्कुट means biscuit,नुन means salt,चामल means rice grains,etc. 
                    db.execSQL("INSERT INTO product VALUES('" + "वाइवाइ चाउचाउ" + "','" + "30" + "');");
                    db.execSQL("INSERT INTO product VALUES('" + "टेस्टी बिस्कुट" + "','" + "50" + "');");
                    db.execSQL("INSERT INTO product VALUES('" + "बनबन बिस्कुट" + "','" + "30" + "');");
                    db.execSQL("INSERT INTO product VALUES('" + "कुरकुरे" + "','" + "15" + "');");
                    db.execSQL("INSERT INTO product VALUES('" + "कोक(सानो)" + "','" + "17" + "');");
                    db.execSQL("INSERT INTO product VALUES('" + "कोक(ठुलो)" + "','" + "5" + "');");
                    db.execSQL("INSERT INTO product VALUES('" + "तोरिको तेल" + "','" + "15" + "');");
                    db.execSQL("INSERT INTO product VALUES('" + "चामल" + "','" + "50" + "');");
                    db.execSQL("INSERT INTO product VALUES('" + "चिउरा" + "','" + "30" + "');");
                    db.execSQL("INSERT INTO product VALUES('" + "मट्टितेल" + "','" + "15" + "');");
                    db.execSQL("INSERT INTO product VALUES('" + "साबुन(लुगा धुने)" + "','" + "35" + "');");
                    db.execSQL("INSERT INTO product VALUES('" + "प्याज" + "','" + "18" + "');");
                    db.execSQL("INSERT INTO product VALUES('" + "आलु" + "','" + "12" + "');");
                    db.execSQL("INSERT INTO product VALUES('" + "नुन" + "','" + "55" + "');");
                    db.execSQL("INSERT INTO product VALUES('" + "चियापत्ती" + "','" + "10" + "');");
                  }
        List<String> mProductName = new ArrayList<>();
        List<Integer> mProductNumber = new ArrayList<>();
        while(c.moveToNext()){
            mProductName.add(c.getString(0));
            mProductNumber.add(c.getInt(1));
        }

        mAdapter = new MyAdapter(mProductName, mProductNumber);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

    }

    public static void updatedatabase(List<String> mProduct, List<Integer> mProductNo, int pos) {
        c=db.rawQuery("SELECT * FROM product WHERE productname='"+mProduct.get(pos)+"'", null);
        if(c.moveToFirst())
        {
            db.execSQL("UPDATE product SET amount='"+mProductNo.get(pos)+"' WHERE productname='"+mProduct.get(pos)+"'");
    }
}
}
