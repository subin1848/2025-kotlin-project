package com.example.studytimerproject

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studytimerproject.databinding.FragmentChartBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class ChartFragment : Fragment() {

    private var _binding: FragmentChartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentChartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPieChart()
    }

    private fun setupPieChart() {
        // 임의의 월별 공부량 데이터 (시간 단위)
        val studyData = listOf(
            PieEntry(30f, "1월"),
            PieEntry(45f, "2월"),
            PieEntry(60f, "3월"),
            PieEntry(35f, "4월"),
            PieEntry(50f, "5월"),
            PieEntry(55f, "6월")
        )

        val dataSet = PieDataSet(studyData, "월별 공부량")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 14f

        val pieData = PieData(dataSet)

        binding.pieChart.apply {
            data = pieData
            description.isEnabled = false
            isRotationEnabled = true
            centerText = "월별 공부량"
            setCenterTextSize(18f)
            setEntryLabelColor(Color.BLACK)
            animateY(1000)
            invalidate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}