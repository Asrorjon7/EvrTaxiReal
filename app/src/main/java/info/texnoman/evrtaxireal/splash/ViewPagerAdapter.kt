package info.texnoman.evrtaxireal.splash

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import info.texnoman.evrtaxireal.R
import info.texnoman.evrtaxireal.auth.model.ViewPagerModel
import info.texnoman.evrtaxireal.databinding.AdapterViewpagerBinding

class ViewPagerAdapter (var context:Context,var list:ArrayList<ViewPagerModel>):RecyclerView.Adapter<ViewPagerAdapter.MyHolderView>() {
   var layoutInflater =LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderView {
        var view  =AdapterViewpagerBinding.inflate(layoutInflater,parent,false)
    return MyHolderView(view)
    }
    override fun onBindViewHolder(holder: MyHolderView, position: Int) {
       var model =list[position]
        with(holder){
           binding.apply {
               ivView.setImageResource(model.image)
               tvTitle.text=model.title
               tvDesc.text =model.desc
           }
        }
    }

    override fun getItemCount() = list.size
    class MyHolderView(var binding:AdapterViewpagerBinding):ViewHolder(binding.root) {

    }


}
