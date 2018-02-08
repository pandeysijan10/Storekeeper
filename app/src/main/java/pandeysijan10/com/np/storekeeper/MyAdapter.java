package pandeysijan10.com.np.storekeeper;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;




public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> mproduct;
    private List<Integer> mproductnumber;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt;
        public TextView txt1;
        public ImageView imgplus;
        public ImageView imgminus;
        public ViewHolder(View v) {
            super(v);
            txt = v.findViewById(R.id.textView);
            txt1 = v.findViewById(R.id.textView2);
            imgplus = v.findViewById(R.id.imageView2);
            imgminus = v.findViewById(R.id.imageView);
        }
    }

    public MyAdapter(List<String> mProductName,List<Integer> mProductNumber) {
        mproduct = mProductName;
        mproductnumber = mProductNumber;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.imgplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer iplus = mproductnumber.get(position);
                iplus=iplus+1;
                mproductnumber.set(position,iplus);
                holder.txt1.setText(mproductnumber.get(position).toString()+" बाँकी");
                Storekeeper.updatedatabase(mproduct, mproductnumber,position);
            }
        });
        holder.imgminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer iminus = mproductnumber.get(position);
                if(iminus>0){
                    iminus=iminus-1;
                    mproductnumber.set(position,iminus);
                    holder.txt1.setText(mproductnumber.get(position).toString()+" बाँकी");
                    Storekeeper.updatedatabase(mproduct, mproductnumber,position);
                }
            }
        });
        holder.txt.setText(mproduct.get(position));
        holder.txt1.setText(mproductnumber.get(position).toString()+" बाँकी");
    }

    @Override
    public int getItemCount() {
        return mproduct.size();
    }
}
