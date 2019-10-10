package com.github.enixander.listslayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numericalListView.setData(
            arrayOf(
                getString(R.string.lorem_ipsum_40),
                getString(R.string.lorem_ipsum_20),
                getString(R.string.lorem_ipsum_40),
                getString(R.string.lorem_ipsum_20)
            )
        )
    }
}
