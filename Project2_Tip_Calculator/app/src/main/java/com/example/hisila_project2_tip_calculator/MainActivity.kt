package com.example.hisila_project2_tip_calculator

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.hisila_project2_tip_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var billAmount = binding.billAmount
        binding.tipButtonOne.setOnClickListener {
            var doubleBillAmount = billAmount.text.toString().toDouble()
            var amountDetail= tipCalculator(doubleBillAmount, 15.0)
            binding.totalAmount.text = "Tip $${amountDetail!!.totalTipAmount}, Total Bill: $${amountDetail!!.totalBill}"
        }

        binding.tipButtonTwo.setOnClickListener {
            var doubleBillAmount = billAmount.text.toString().toDouble()
            var amountDetail= tipCalculator(doubleBillAmount, 18.0)
            binding.totalAmount.text = "Tip $${amountDetail!!.totalTipAmount}, Total Bill: $${amountDetail!!.totalBill}"
        }

        binding.tipButtonThree.setOnClickListener {
            var doubleBillAmount = billAmount.text.toString().toDouble()
            var amountDetail= tipCalculator(doubleBillAmount, 20.0)
            binding.totalAmount.text = "Tip $${amountDetail!!.totalTipAmount}, Total Bill: $${amountDetail!!.totalBill}"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
    private fun tipCalculator(amount: Double, tip: Double) : TotalAmountDetail? {
        if (amount != null && tip != null ) {
            val totalTipAmount =  amount * (tip / 100) ;
            val totalBillWithTip = totalTipAmount + amount;
            return TotalAmountDetail(
                totalTipAmount = totalTipAmount,
                totalBill = totalBillWithTip
            )
        }
        return null
    }
}

data class TotalAmountDetail(
    var totalTipAmount : Double,
    var totalBill : Double
)