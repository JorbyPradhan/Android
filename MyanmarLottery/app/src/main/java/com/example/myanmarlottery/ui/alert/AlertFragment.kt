package com.example.myanmarlottery.ui.alert

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.myanmarlottery.MUSIC_SERVICE_ACTION_START
import com.example.myanmarlottery.MyService
import com.example.myanmarlottery.R
import kotlinx.android.synthetic.main.fragment_alert.*

@Suppress("UNSAFE_CALL_ON_PARTIALLY_DEFINED_RESOURCE")
class AlertFragment : Fragment() {

    private lateinit var alertViewModel: AlertViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        alertViewModel =
                ViewModelProviders.of(this).get(AlertViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_alert, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
       /* alertViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        submitOk.setOnClickListener {
            validation()
        }
        send_order.setOnClickListener {
            val intent = Intent(context, MyService::class.java)
            intent.action = MUSIC_SERVICE_ACTION_START
            context?.startService(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun validation() {
        val numEnd = pinView_end.text.toString()
        var finalNumber : String ?= null
        val numFirst  = pinView_first.text.toString()
        if (numEnd.isEmpty() && numFirst.isEmpty()){
            send_order.visibility = View.GONE
            Toast.makeText(context, "Fill Number First", Toast.LENGTH_SHORT).show()
            return
        }
        if(numEnd.isEmpty()){
            send_order.visibility = View.VISIBLE
           finalNumber = "${numFirst}နှင့်စ"
        }else{
            send_order.visibility = View.VISIBLE
            val num = numEnd.reversed()
            if(numFirst.isEmpty()){
               finalNumber = "${numEnd}နှင့်ဆုံး"
            }else{
                if(numFirst.length == 3 && numEnd.length == 3) {
                    finalNumber = "$numFirst$numEnd"
                }
                if(numEnd.length != 3) {
                    finalNumber = "${numFirst}နှင့်စ၍__${numEnd}နှင့်ဆုံး"
                }
            }
        }
        dis_num.text = """Your number is$finalNumber """
    }
}