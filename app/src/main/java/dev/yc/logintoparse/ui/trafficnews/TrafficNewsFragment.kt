package dev.yc.logintoparse.ui.trafficnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.yc.logintoparse.databinding.FragmentTrafficNewsBinding

class TrafficNewsFragment : Fragment() {
    private var _binding: FragmentTrafficNewsBinding? = null
    private val binding: FragmentTrafficNewsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrafficNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}