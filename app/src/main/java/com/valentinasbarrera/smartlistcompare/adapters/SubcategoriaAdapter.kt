import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.valentinasbarrera.smartlistcompare.R
import com.valentinasbarrera.smartlistcompare.model.Subcategoria

class SubcategoriaAdapter(val context: Context,
                          val layout: Int
) : BaseAdapter() {

    private var dataList: List<Subcategoria> = emptyList()


    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): Any {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView = convertView
        val holder: ViewHolder

        if (itemView == null) {
            val layoutInflater = LayoutInflater.from(context)
            itemView = layoutInflater.inflate(layout, null)
            holder = ViewHolder(itemView)
            itemView.tag = holder
        } else {
            holder = itemView.tag as ViewHolder
        }

        val item = dataList[position]
        holder.bind(item)

        return itemView!!
    }

    internal fun setSubcategorias(Subcategoria: List<Subcategoria>) {
        this.dataList = Subcategoria
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) {
        private val context: Context = view.context
        private val ivsubcategoriagrid: ImageView = view.findViewById(R.id.ivsubcategoriagrid)
        private val tvsubcategoriagrid: TextView = view.findViewById(R.id.tvsubcategoriagrid)
        private lateinit var dataItem: Subcategoria

        fun bind(dataItem: Subcategoria) {
            this.dataItem = dataItem
            tvsubcategoriagrid.text = dataItem.nombre
            val resourceId = context.resources.getIdentifier(dataItem.icono, "drawable", context.packageName)
            ivsubcategoriagrid.setImageResource(resourceId)
        }

        fun getSubCategoria(): Subcategoria {
            return dataItem
        }
    }
}
