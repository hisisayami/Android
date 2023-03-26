package com.example.hisila_project2_tip_calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hisila_project2_tip_calculator.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var billAmount = binding.billAmount
        binding.tipButtonOne.setOnClickListener {
            var doubleBillAmount = billAmount.text.toString().toDouble()
            var amountDetail= tipCalculator(doubleBillAmount, 10.0)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun tipCalculator(amount: Double, tip: Double) : TotalAmountDetail2? {
        if (amount != null && tip != null ) {
            val totalTipAmount =  amount * (tip / 100) ;
            val totalBillWithTip = totalTipAmount + amount;
            return TotalAmountDetail2(
                totalTipAmount = totalTipAmount,
                totalBill = totalBillWithTip
            )
        }
        return null
    }
}

data class TotalAmountDetail2(
    var totalTipAmount : Double,
    var totalBill : Double
)
