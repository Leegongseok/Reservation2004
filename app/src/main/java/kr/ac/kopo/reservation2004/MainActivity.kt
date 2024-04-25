package kr.ac.kopo.reservation2004

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.Chronometer
import android.widget.DatePicker
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.TextView
import android.widget.TimePicker
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var chrono:Chronometer

    lateinit var rg:RadioGroup
    lateinit var calendar:DatePicker
    lateinit var timePick:TimePicker
    lateinit var textResult: TextView
    var selectedYear :Int=0
    var selectedMonth :Int=0
    var selectedDay :Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chrono=findViewById<Chronometer>(R.id.chrono)

        rg=findViewById<RadioGroup>(R.id.rg)
        calendar=findViewById<DatePicker>(R.id.calendar)
        timePick=findViewById<TimePicker>(R.id.timePick)
        textResult=findViewById<TextView>(R.id.textResult)

        rg.visibility=View.INVISIBLE
        timePick.visibility= View.INVISIBLE
        calendar.visibility=View.INVISIBLE

        rg.setOnCheckedChangeListener(rgListener)
        chrono.setOnClickListener({
            chrono.base=SystemClock.elapsedRealtime()
            chrono.start()
            chrono.setTextColor(Color.MAGENTA)
            rg.visibility=View.VISIBLE
        })

        textResult.setOnLongClickListener{
            chrono.stop()
            chrono.setTextColor(Color.CYAN)
            selectedYear=calendar.year
            selectedMonth=calendar.month
            selectedDay=calendar.dayOfMonth

            textResult.setText((""+selectedYear+"년" + selectedMonth+"월"+selectedDay+"일"))
            textResult.append(""+timePick.currentHour+"시")
            textResult.append(""+timePick.currentMinute+"분")
            textResult.append("예약완료됨.")

            rg.visibility=View.INVISIBLE
            calendar.visibility=View.INVISIBLE
            timePick.visibility=View.INVISIBLE
            return@setOnLongClickListener true

        }

//        textResult.setOnClickListener{
//            chrono.stop()
//            selectedYear=calendar.year
//            selectedMonth=calendar.month
//            selectedDay=calendar.dayOfMonth
//            chrono.setTextColor(Color.CYAN)
//            textResult.setText("" + selectedYear + "년" + selectedMonth+ "월" + selectedDay +"일")
//            textResult.append("" + timePick.currentHour + "시")
//            textResult.append("" + timePick.currentMinute + "분")
//        }

//        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
//            selectedYear=year
//            selectedMonth=month
//            selectedDay=dayOfMonth
//        }

    }
    var rgListener=OnCheckedChangeListener{group,checkedId->
        calendar.visibility=View.INVISIBLE
        timePick.visibility=View.INVISIBLE
        when(rg.checkedRadioButtonId){
            R.id.rbDate ->calendar.visibility=View.VISIBLE
            R.id.rbTime->timePick.visibility=View.VISIBLE
        }
    }
}