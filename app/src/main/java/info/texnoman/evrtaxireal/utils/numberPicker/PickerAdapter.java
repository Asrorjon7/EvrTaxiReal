package info.texnoman.evrtaxireal.utils.numberPicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import info.texnoman.evrtaxireal.R;

public class PickerAdapter extends RecyclerView.Adapter<PickerAdapter.TextVH> {
    private Context context;
    private List<String> dataList;
    private RecyclerView recyclerView;

    public PickerAdapter(Context context, List<String> dataList, RecyclerView recyclerView) {
        this.context = context;
        this.dataList = dataList;
        this.recyclerView = recyclerView;
    }
    @Override
    public TextVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.picker_item_layout, parent, false);
        return new PickerAdapter.TextVH(view);
    }

    @Override
    public void onBindViewHolder(TextVH holder, @SuppressLint("RecyclerView") int position) {
        TextVH textVH = holder;
        textVH.pickerTxt.setText(dataList.get(position));
        textVH.pickerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerView != null) {
                    recyclerView.smoothScrollToPosition(position);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public void swapData(List<String> newData) {
        dataList = newData;
        notifyDataSetChanged();
    }

    class TextVH extends RecyclerView.ViewHolder {
        TextView pickerTxt;

        public TextVH(View itemView) {
            super(itemView);
            pickerTxt = (TextView) itemView.findViewById(R.id.picker_item);
        }
    }
}