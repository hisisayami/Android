package com.example.project2_contractor_calculator

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.project2_contractor_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var laborAmount = binding.laborAmount
        var materialAmount = binding.materialAmount

        binding.calculateButton.setOnClickListener {
            var doubleLaborAmount = laborAmount.text.toString().toDouble()
            var doubleMaterialAmount = materialAmount.text.toString().toDouble()
            var amountDetail = totalCostCalculator(doubleLaborAmount, doubleMaterialAmount)
            binding.subTotalLabel.text = amountDetail!!.subTotal.toString()
            binding.taxLabel.text = amountDetail!!.tax.toString()
            binding.totalLabel.text = amountDetail.total.toString()
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

    private fun totalCostCalculator(laborAmount: Double, materialAmount: Double) : TotalAmountDetail? {
        if (laborAmount != null && materialAmount != null ) {
            val subTotalAmount= laborAmount + materialAmount;
            val totalTaxAmount =  subTotalAmount * (5.0 / 100) ;
            val totalBill = subTotalAmount + totalTaxAmount;
            return TotalAmountDetail(
                subTotal = subTotalAmount,
                tax = totalTaxAmount,
                total = totalBill
            )
        }
        return null
    }
}

data class TotalAmountDetail(
    var subTotal : Double,
    var tax : Double,
    var total: Double
)