package dev.yc.logintoparse.ui.trafficnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dev.yc.logintoparse.MainActivity
import dev.yc.logintoparse.databinding.FragmentTrafficNewsBinding

class TrafficNewsFragment : Fragment() {
    private var _binding: FragmentTrafficNewsBinding? = null
    private val binding: FragmentTrafficNewsBinding get() = _binding!!

    private val viewModel: TrafficNewsViewModel by viewModels(
        factoryProducer = {
            val appContainer = (activity as MainActivity).appContainer
            TrafficNewsViewModelFactory(appContainer.trafficNewsRepository)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrafficNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupNews()
        setupOnClick()
    }

    private fun setupNews() {
        val adapter = TrafficNewsAdapter()
        binding.rvTrafficNews.adapter = adapter

        viewModel.trafficNews.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setupOnClick() {
        binding.tvTimeZone.setOnClickListener {
            val action = TrafficNewsFragmentDirections.actionToTimeZoneUpdater()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}