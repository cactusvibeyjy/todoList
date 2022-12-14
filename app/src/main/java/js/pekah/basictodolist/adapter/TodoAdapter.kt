package js.pekah.basictodolist.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.graphics.Rect
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import js.pekah.basictodolist.R
import js.pekah.basictodolist.dto.Todo

class TodoAdapter(val context: Context): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var list = mutableListOf<Todo>()

    inner class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var title = itemView.findViewById<TextView>(R.id.tvTodoItem)
        var timestamp = itemView.findViewById<TextView>(R.id.tvTimeStamp)
        var checkbox = itemView.findViewById<CheckBox>(R.id.cbCheck)

        fun onBind(data: Todo) {
            title.text = data.title
            timestamp.text = data.timestamp
            checkbox.isChecked = data.isChecked

            if (data.isChecked) {
                title.paintFlags = title.paintFlags or STRIKE_THRU_TEXT_FLAG
            } else {
                title.paintFlags = title.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
            }

            checkbox.setOnClickListener{
                itemCheckBoxClickListener.onClick(it, layoutPosition, list[layoutPosition].id)
            }

            itemView.setOnClickListener {
                itemClickListner.onClick(it, layoutPosition, list[layoutPosition].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo_list, parent, false)
        //val view2 = LayoutInflater.from(parent.context).inflate(R.layout.activity_two,parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun update(newList: MutableList<Todo>) {
        this.list = newList
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onClick(view: View,  position: Int, itemId: Long)
    }

    interface ItemCheckBoxClickListener {
        fun onClick(view: View, position: Int, itemId: Long)
    }

    private lateinit var itemClickListner: ItemClickListener
    private lateinit var itemCheckBoxClickListener: ItemCheckBoxClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }

    fun setItemCheckBoxClickListener(itemCheckBoxClickListener: ItemCheckBoxClickListener) {
        this.itemCheckBoxClickListener = itemCheckBoxClickListener
    }
}

//    class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {
//        rvtoDoList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
//
//       /* override fun getItemOffsets(
//            outRect: Rect,
//            view: View,
//            parent: RecyclerView,
//            state: RecyclerView.State
//        ) {
//            super.getItemOffsets(outRect, view, parent, state)
//            val index = parent.getChildAdapterPosition(view) + 1
//
//            if (index % 3 == 0) //left, top, right, bottom
//                outRect.set(10, 10, 10, 60)
//            else
//                outRect.set(10, 10, 10, 0)
//
//            view.setBackgroundColor(Color.parseColor("E6DEDE"))
//            ViewCompat.setElevation(view, 20.0f)*/
//
//        }

