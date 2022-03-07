package dev.yc.logintoparse.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.yc.logintoparse.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupEvents()
        setupObservers()
    }

    private fun setupEvents() {
        binding.btnChange.setOnClickListener {
            viewModel.doSomething()
        }
    }

    private fun setupObservers() {
        viewModel.someText.observe(viewLifecycleOwner) {
            binding.tvMessage.text = it
        }
    }
}
