package js.pekah.basictodolist.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import js.pekah.basictodolist.dao.TodoDao
import js.pekah.basictodolist.database.TodoDatabase
import js.pekah.basictodolist.dto.Todo
import kotlinx.coroutines.flow.Flow
import java.lang.IllegalStateException

private const val DATABASE_NAME = "todo-database.db"
class TodoRepository private constructor(context: Context){
    private val database: TodoDatabase = Room.databaseBuilder(
        context.applicationContext,
        TodoDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val todoDao = database.todoDao()
    fun getCheckedList():LiveData<MutableList<Todo>> = todoDao.getCheckedList()


    fun list(): LiveData<MutableList<Todo>> = todoDao.list()


    fun getTodo(id: Long): Todo = todoDao.selectOne(id)

    fun insert(dto: Todo) = todoDao.insert(dto)

    suspend fun update(dto: Todo) = todoDao.update(dto)


    fun delete(dto: Todo) = todoDao.delete(dto)
    /*fun readDoneData(): LiveData<MutableList<Todo>> = todoDao.readDoneData()*/

    companion object {
        private var INSTANCE: TodoRepository?=null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = TodoRepository(context)
            }
        }

        fun get(): TodoRepository {
            return INSTANCE ?:
            throw IllegalStateException("TodoRepository must be initialized")
        }
    }
}