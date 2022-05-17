package info.texnoman.evrtaxireal.auth.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import info.texnoman.evrtaxireal.R;

public class CustomAdapter extends ArrayAdapter {
    private static class ViewHolder {
        ImageView mFlag;
        TextView mName;
    }

    private String[] spinnerTitles;
    private int[] spinnerImages;
    private Context mContext;



    public CustomAdapter( Context context, String[] titles, int[] images) {
        super(context, R.layout.custom_spinner_row);
        this.spinnerTitles= titles;
        this.spinnerImages= images;
        this.mContext=context;

    }

    @Override
    public int getCount(){
        return spinnerTitles.length;
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent){

        ViewHolder mViewHolder = new ViewHolder();
        if (convertView==null){
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= mInflater.inflate(R.layout.custom_spinner_row, parent, false);
            mViewHolder.mFlag= (ImageView) convertView.findViewById(R.id.ivFlag);
            mViewHolder.mName= (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(mViewHolder);
        }
        else{
            mViewHolder=(ViewHolder)convertView.getTag();
        }
        mViewHolder.mFlag.setImageResource(spinnerImages[position]);
        mViewHolder.mName.setText(spinnerTitles[position]);
       // mViewHolder.mPopulation.setText(spinnerPopulation[position]);


        return convertView;

    }

   @Override
    public View getDropDownView(int position,  View convertView, ViewGroup parent){

        return getView(position, convertView, parent);

    }






}
