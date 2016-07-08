package ru.startandroid.notesmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 24.05.2016.
 */
public class NotesAdapter extends BaseAdapter implements View.OnClickListener {
    final String LOG_TAG = "NotesAdapterLog";
    Activity activity;
    private ArrayList<Note> rData = new ArrayList<Note>();
    static LayoutInflater inflater = null;
    Context mContext;

    public NotesAdapter(Activity a, ArrayList<Note> rD,
                        Context context) {
        this.mContext = context;
        activity = a;
        rData = rD;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        Log.d(LOG_TAG, "Вызов функции getCount");
        return rData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {

    }


    public class ViewHolder {

        public TextView text;
        public TextView text2;
        public TextView text3;


    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {
            //****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.list_view_row, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.textView);
            holder.text2 = (TextView) vi.findViewById(R.id.textView1);
            holder.text3 = (TextView) vi.findViewById(R.id.textView3);


            /************ Set holder with LayoutInflater ************/
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }

        Note item = rData.get(position);

        Context context = parent.getContext();

        if (item.getNote_text().length() > 15) {
            holder.text.setText(item.getNote_text().substring(0, 10) + "...");
        } else {
            holder.text.setText(item.getNote_text());
        }

        holder.text2.setText(item.getNote_data());
        holder.text3.setText(item.getNote_length() +" "+ item.getNote_width());


        /******** Set Item Click Listner for LayoutInflater for each row ***********/
        vi.setOnClickListener(new OnItemClickListener(position, item));
        return vi;
    }

    /*********
     * Called when Item click in ListView
     ************/


    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;
        private Note n;

        OnItemClickListener(int position, Note note) {
            mPosition = position;
            n = note;

        }


        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(mContext, AddNotesActivity.class);
            int flag = 1;
            myIntent.putExtra("flag_place", String.valueOf(flag));
            myIntent.putExtra("note_text", n.getNote_text());
            myIntent.putExtra("note_data", n.getNote_data());
            myIntent.putExtra("note_width",n.getNote_width());
            myIntent.putExtra("note_length",n.getNote_length());


            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(myIntent);


        }
    }
}


