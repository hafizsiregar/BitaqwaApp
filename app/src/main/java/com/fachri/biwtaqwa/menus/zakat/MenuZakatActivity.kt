package com.fachri.biwtaqwa.menus.zakat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.fachri.biwtaqwa.databinding.ActivityMemuZakatBinding
import java.text.NumberFormat
import java.util.*

class MenuZakatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemuZakatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemuZakatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbarMenuZakat)
        initNavMenu()
    }

    private fun initNavMenu() {
        binding.btnZakat.setOnClickListener {
            val formatAmount = NumberFormat.getNumberInstance(Locale("id", "ID"))
                .format(binding.etAmountZakat.getNumericValue())
            binding.tvHartaAmount.text = "Rp $formatAmount"

            if(binding.etAmountZakat.getNumericValue()?.toInt()!! >= 85000000) {
                val zakat = binding.etAmountZakat.getNumericValue()!!.toInt() * (2.5 / 100)
                val formatZakat = NumberFormat.getNumberInstance(Locale("id", "ID"))
                    .format(zakat)
                binding.tvAmountZakat.text = "Rp $formatZakat"
            } else {
                Toast.makeText(
                    this, "Total Harta masih belum mencapai nisab (85gr Emas)",
                    Toast.LENGTH_LONG
                ).show()
                binding.tvAmountZakat.text = "Rp 0"
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}