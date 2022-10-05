package js.pekah.basictodolist.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import js.pekah.basictodolist.dto.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dto: Todo)

    @Query("select * from todoTable")
    fun list(): LiveData<MutableList<Todo>>

    @Query("select * from todoTable where id = (:id)")
    fun selectOne(id: Long): Todo

    @Update
    suspend fun update(dto: Todo)

    @Delete
    fun delete(dto: Todo)

    @Query("SELECT * FROM todoTable WHERE `isChecked` = 1 ORDER BY id DESC")
    fun getCheckedList(): LiveData<MutableList<Todo>>


    /*
     fun readDoneData() : LiveData<MutableList<Todo>>
     @Query("SELECT * FROM todoTable")
     fun readAllData() : LiveData<MutableList<Todo>>*/

// 완료한 메모만 출력
    /*@Query("SELECT * FROM todoTable WHERE `check` = 1 ORDER BY year DESC, month DESC, day DESC, id DESC")
    fun readDoneData() :  MutableList<Todo>*/
}