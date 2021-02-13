package com.istekno.simpleunittesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.istekno.simpleunittesting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private const val FIELD_IS_NOT_EMPTY = "Field ini tidak boleh kosong"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = MainViewModel(CuboidModel())


    }

    override fun onClick(view: View) {
        val length = binding.edtLength.text.toString().trim()
        val width = binding.edtWidth.text.toString().trim()
        val height = binding.edtHeight.text.toString().trim()

        when {
            TextUtils.isEmpty(length) -> {
                binding.edtLength.error = FIELD_IS_NOT_EMPTY
            }
            TextUtils.isEmpty(width) -> {
                binding.edtWidth.error = FIELD_IS_NOT_EMPTY
            }
            TextUtils.isEmpty(height) -> {
                binding.edtHeight.error = FIELD_IS_NOT_EMPTY
            }

            else -> {
                val valueWidth = width.toDouble()
                val valueLength = length.toDouble()
                val valueHeight = height.toDouble()

                when(view.id) {
                    R.id.btn_save -> {
                        viewModel.save(valueWidth, valueLength, valueHeight)
                        visible()
                    }
                    R.id.btn_calculate_circumference -> {
                        binding.tvResult.text = viewModel.getCircumference().toString()
                        gone()
                    }
                    R.id.btn_calculate_surface_area -> {
                        binding.tvResult.text = viewModel.getSurfaceArea().toString()
                        gone()
                    }
                    R.id.btn_calculate_volume -> {
                        binding.tvResult.text = viewModel.getVolume().toString()
                        gone()
                    }
                }
            }
        }
    }

    private fun visible() {
        binding.btnCalculateCircumference.visibility = View.VISIBLE
        binding.btnCalculateSurfaceArea.visibility = View.VISIBLE
        binding.btnCalculateVolume.visibility = View.VISIBLE
        binding.btnSave.visibility = View.GONE
    }

    private fun gone() {
        binding.btnCalculateCircumference.visibility = View.GONE
        binding.btnCalculateSurfaceArea.visibility = View.GONE
        binding.btnCalculateVolume.visibility = View.GONE
        binding.btnSave.visibility = View.VISIBLE
    }
}