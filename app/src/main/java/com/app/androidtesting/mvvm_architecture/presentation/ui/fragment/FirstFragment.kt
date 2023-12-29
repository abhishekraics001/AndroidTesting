package com.app.androidtesting.mvvm_architecture.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.SeekBar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.app.androidtesting.R
import com.app.androidtesting.databinding.FragmentFirstBinding
import com.app.androidtesting.user_mvvm.comman.Utility
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
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

        binding.buttonFirst.setOnClickListener {
            navigateToSecondPgae()
        }
    }

    fun navigateToSecondPgae(){
        var termConditionStatus = false;
        var privacyPolicyStatus = false
        var genderSelctionValue = "Male"
        var NotificationSwitchStatus = false
        var AlarmSwitchStatus = false
        var PercentageProgressStatus = 0

        binding.termConditionStatus.setOnCheckedChangeListener { _, isChecked ->
            // Handle the checkbox state change
            if (isChecked) {
                // Checkbox is checked
            } else {
                // Checkbox is unchecked
            }
            termConditionStatus = isChecked
        }


         binding.privacyPolicyStatus.setOnCheckedChangeListener { _, isChecked ->
             // Handle the checkbox state change
             if (isChecked) {
                 // Checkbox is checked
             } else {
                 // Checkbox is unchecked
             }
             privacyPolicyStatus = isChecked
         }


        binding.genderRadioGroupStatus.setOnCheckedChangeListener { group, checkedId ->
            // Find the selected RadioButton by ID
            val selectedRadioButton = binding.root.findViewById<RadioButton>(checkedId)

            // Handle the selection
            if (selectedRadioButton != null) {
                val selectedText = selectedRadioButton.text
                // Do something with the selected text
                genderSelctionValue = selectedText.toString()
            }
        }

        binding.NotificationSwitchStatus.setOnCheckedChangeListener { _, isChecked ->
            // Handle the switch state change
            if (isChecked) {
                // Switch is ON
            } else {
                // Switch is OFF
            }
            NotificationSwitchStatus = isChecked
        }

        binding.AlarmSwitchStatus.setOnCheckedChangeListener { _, isChecked ->
            // Handle the switch state change
            if (isChecked) {
                // Switch is ON
            } else {
                // Switch is OFF
            }
            AlarmSwitchStatus = isChecked
        }

        binding.PercentageProgressStatus.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Handle seek bar progress change
                PercentageProgressStatus = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Called when user starts dragging the seek bar thumb
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Called when user stops dragging the seek bar thumb
            }
        })

        lifecycleScope.launch {
            context?.let { Utility.showToast(it, "TermConditionStatus: $termConditionStatus   \n PrivacyPolicyStatus: $privacyPolicyStatus  \n GenderSelectionValue: $genderSelctionValue  \n NotificationSwitchStatus: $NotificationSwitchStatus  \n AlarmSwitchStatus: $AlarmSwitchStatus  \n PercentageProgressStatus: $PercentageProgressStatus") }

            delay(5000)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}