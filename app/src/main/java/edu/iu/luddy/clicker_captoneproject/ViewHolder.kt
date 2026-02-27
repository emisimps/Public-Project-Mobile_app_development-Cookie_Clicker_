package edu.iu.luddy.clicker_captoneproject

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textView : TextView

        init {
            textView = view.findViewById(R.id.rest)
        }
    }