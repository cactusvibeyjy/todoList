package js.pekah.basictodolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import js.pekah.basictodolist.adapter.TodoAdapter
import js.pekah.basictodolist.databinding.ActivityMainBinding
import js.pekah.basictodolist.databinding.ActivityTwoBinding
import js.pekah.basictodolist.viewmodel.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TwoActivity : AppCompatActivity() {
    lateinit var binding: ActivityTwoBinding
    lateinit var todoViewModel: TodoViewModel
    lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        todoViewModel.isChecked.observe(this) {
            todoAdapter.update(it)
        }
        todoAdapter = TodoAdapter(this)
        binding.doneList.layoutManager = LinearLayoutManager(this)
        binding.doneList.adapter = todoAdapter
        binding.doneList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        todoAdapter.setItemCheckBoxClickListener(object : TodoAdapter.ItemCheckBoxClickListener {
            override fun onClick(view: View, position: Int, itemId: Long) {
                CoroutineScope(Dispatchers.IO).launch {
                    val todo = todoViewModel.getOne(itemId)
                    todo.isChecked = !todo.isChecked
                    todoViewModel.update(todo)
                }
            }
        })
//        todoViewModel.readDoneData.observe(viewLifecycleOwner, Observer {
//            todoAdapter.setData(it)
//        })
//
//        return binding!!.root
        // }


        binding.returnList.setOnClickListener {

            // 가입하기 버튼을 누르면 들어오는 로직
            // 회원가입(JoinActivity) 화면으로 이동하게 Intent 사용
            val intent = Intent(this, MainActivity::class.java)

            // startActivity를 해야 화면이동 **
            startActivity(intent)
            //리스트 목록으로 이동시 토스트 메세지
            Toast.makeText(this, "목록으로 이동", Toast.LENGTH_SHORT).show()
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.menu_item_delete -> {
                Toast.makeText(this, "삭제", Toast.LENGTH_SHORT).show()
                todoViewModel.todoList.value!!.forEach {
                    if (it.isChecked) {
                        todoViewModel.delete(it)
                    }
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}