package kz.kuz.callback

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), MainFragment.Callbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, MainFragment())
                    .commitNow()
        }
    }

    fun doubleNumber(number: Int): Int {
        return number * 2
    }

    override fun tripleNumber(number: Int): Int {
        return number * 3
    }
}