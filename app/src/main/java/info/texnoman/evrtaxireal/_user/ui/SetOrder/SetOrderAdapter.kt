package info.texnoman.evrtaxireal._user.ui.SetOrder

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal.model.SetOrderModel
import info.texnoman.evrtaxireal.databinding.AdapterSetOrderBinding

data class SetOrderAdapter(var context: Context,var list:List<SetOrderModel>,var click:OnItemClickListener ):RecyclerView.Adapter<SetOrderAdapter.MyHolderView>() {
     var layoutInflater = LayoutInflater.from(context)
    var rowIndex =0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderView {
         var binding =AdapterSetOrderBinding.inflate(layoutInflater,parent,false)
        return MyHolderView(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyHolderView, position: Int) {
     var model =list[position]
        with(holder){
            binding.apply {
                title.text = model.title
            itemView.setOnClickListener {
                rowIndex =position
                notifyDataSetChanged()
                click.SetOrderClickListener(model)
            }
                 if (rowIndex ==position){
                     title.background =ContextCompat.getDrawable(context, R.drawable.setorder_background)
                 }else{
                     title.background =ContextCompat.getDrawable(context,R.drawable.filled_setorder_background)
                 }
            }
        }
    }

    override fun getItemCount(): Int =list.size
    class MyHolderView(var binding: AdapterSetOrderBinding):RecyclerView.ViewHolder(binding.root) {
    }
    interface OnItemClickListener{
        fun SetOrderClickListener(model: SetOrderModel)
    }
}