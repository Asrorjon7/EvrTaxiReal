package info.texnoman.evrtaxireal._user.main
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import info.texnoman.evrtaxireal.model.NavigationModel
import info.texnoman.evrtaxireal.databinding.AdapterNavigationBinding

class NavigationAdapter(var context : Context, var list:ArrayList<NavigationModel>,var click:OnItemClickListener):RecyclerView.Adapter<NavigationAdapter.MyHolderView>() {
    var layoutInflater: LayoutInflater =LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationAdapter.MyHolderView {
      var binding = AdapterNavigationBinding.inflate(layoutInflater,parent,false)
        return MyHolderView(binding)
    }
    override fun onBindViewHolder(holder: NavigationAdapter.MyHolderView, position: Int) {
      var data =list[position]
       with(holder){
           binding.apply {
               ivIcon.setImageResource(data.image)
               tvTitle.text =data.title
               itemView.setOnClickListener {
                   click.NavigationClick(data)
               }
           }
       }
    }

    override fun getItemCount(): Int {
      return list.size
    }

    fun setData(model: List<NavigationModel>){
        this.list.addAll(model)
        notifyItemRangeChanged(0,model.size-1)
    }
    class MyHolderView(var binding:AdapterNavigationBinding):RecyclerView.ViewHolder(binding.root){
    }
    interface OnItemClickListener{
        fun NavigationClick(model:NavigationModel)
    }
}