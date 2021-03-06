package info.texnoman.evrtaxireal._user.ui.OrderHistory
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import info.texnoman.evrtaxireal.model.OrderHistoryModel
import info.texnoman.evrtaxireal.databinding.AdapterOrderBinding

class OrderHistoryAdapter(var context: Context, var list: ArrayList<OrderHistoryModel>,var click:OnItemClickListener) :
    RecyclerView.Adapter<OrderHistoryAdapter.MyHolderView>() {
    var layoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHistoryAdapter.MyHolderView {
        var view = AdapterOrderBinding.inflate(layoutInflater, parent, false)
        return MyHolderView(view)
    }
    override fun onBindViewHolder(holder: OrderHistoryAdapter.MyHolderView, position: Int) {
     with(holder){
         binding.tvDate.text ="affe"
         itemView.setOnClickListener {
             click.clickListener(list[position])
         }
     }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyHolderView(var binding: AdapterOrderBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener{
        fun clickListener(model: OrderHistoryModel)
    }
}