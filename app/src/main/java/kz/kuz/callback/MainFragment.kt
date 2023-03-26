package kz.kuz.callback

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

// цель - обратиться к методу в активности-хосте
// к методу можно обратиться как напрямую, так и посредством интерфейса Callbacks
// interface является коллекцией абстрактных методов
// интерфейс Callbacks создаётся во фрагменте, но метод из этого интерфейса переопределяется
// в активности-хосте
// интерфейс подключается в активности посредством implements.MainFragment.Callbacks
// а также во фрагменте переопределением класса onAttach (при присоединении фрагмента к активности)
// при этом в переопределении класса onDetach данный интерфейс нужно обнулить
class MainFragment : Fragment() {
    private var mCallbacks: Callbacks? = null

    // интерфейс для активности-хоста
    interface Callbacks {
        fun tripleNumber(number: Int): Int
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCallbacks = context as Callbacks
    }

    // методы фрагмента должны быть открытыми
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.toolbar_title)
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val textView = view.findViewById<TextView>(R.id.textView)
        val editText = view.findViewById<EditText>(R.id.editTextNumber)
        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            var number = editText.text.toString().toInt()
            //                number = ((MainActivity)getActivity()).doubleNumber(number);
            number = mCallbacks!!.tripleNumber(number)
            // без Callbacks данный фрагмент привязан к активности MainActivity
            // в случае с Callback фрагмент можно подключить к любой активности
            textView.text = number.toString()
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        mCallbacks = null
    }
}