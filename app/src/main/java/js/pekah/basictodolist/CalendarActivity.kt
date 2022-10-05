package js.pekah.basictodolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import js.pekah.basictodolist.databinding.ActivityCalendarBinding

class CalendarActivity : AppCompatActivity() {
    lateinit var binding : ActivityCalendarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        binding.calendarView.setOnClickListener {

            // 가입하기 버튼을 누르면 들어오는 로직
            // 회원가입(JoinActivity) 화면으로 이동하게 Intent 사용
            val intent = Intent(this, CalendarActivity::class.java)

            // startActivity를 해야 화면이동 **
            startActivity(intent)
            //리스트 목록으로 이동시 토스트 메세지
            Toast.makeText(this, "달력으로 이동", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}