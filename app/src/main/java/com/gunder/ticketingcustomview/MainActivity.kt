package com.gunder.ticketingcustomview

import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gunder.ticketingcustomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val seatsView = binding.seatsView
        val seatButton = binding.btnFinish
        seatButton.setOnClickListener {
            seatsView.seat?.let {
                Toast.makeText(this, "Kursi Anda nomor ${it.name}", Toast.LENGTH_SHORT).show()
            } ?: run {
                Toast.makeText(this, "Silahkan pilih kursi terlebih dahulu!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}