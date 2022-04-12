package info.texnoman.evrtaxireal._driver.ui.DriverOrder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import info.texnoman.evrtaxireal._driver.model.DriverOrderModel
import info.texnoman.evrtaxireal.databinding.AdapterDriverOrderBinding

class DriverOrderAdapter(var context: Context, var list: ArrayList<DriverOrderModel>,var click:ItemClickListener) :
    RecyclerView.Adapter<DriverOrderAdapter.MyHolderView>() {
    var layoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderView {
        var view = AdapterDriverOrderBinding.inflate(layoutInflater, parent, false)
        return MyHolderView(view)
    }
    override fun onBindViewHolder(holder: MyHolderView, position: Int) {
      var model =list[position]
        with(holder) {
            binding.apply {
                tvOrderNumber.text =model.nomer
            }
            itemView.setOnClickListener {
                click.OnClickListener(model)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    class MyHolderView(val binding: AdapterDriverOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {}
    interface  ItemClickListener{
        fun OnClickListener(model: DriverOrderModel)
    }
    }