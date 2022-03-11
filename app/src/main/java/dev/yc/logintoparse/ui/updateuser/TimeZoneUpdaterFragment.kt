package dev.yc.logintoparse.ui.updateuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.yc.logintoparse.MainActivity
import dev.yc.logintoparse.R
import dev.yc.logintoparse.databinding.FragmentTimeZoneUpdaterBinding
import dev.yc.logintoparse.utils.livedata.EventObserver

class TimeZoneUpdaterFragment : Fragment() {
    private var _binding: FragmentTimeZoneUpdaterBinding? = null
    private val binding: FragmentTimeZoneUpdaterBinding get() = _binding!!

    private val viewModel: TimeZoneUpdaterViewModel by viewModels(
        factoryProducer = {
            val appContainer = (activity as MainActivity).appContainer
            TimeZoneUpdaterViewModelFactory(appContainer.userRepository)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimeZoneUpdaterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        setupMenu()
    }

    private fun setupMenu() {
        val items = viewModel.timeZones
        val adapter = context?.let { ArrayAdapter(it, R.layout.list_item_time_zone, items) }
        binding.menuTimeZone.editText?.let { editText ->
            (editText as? AutoCompleteTextView)?.setAdapter(adapter)

            editText.addTextChangedListener {
                val timeZone = editText.text.toString()
                viewModel.updateTimeZone(timeZone)
            }
        }
    }

    private fun setupObservers() {
        viewModel.email.observe(viewLifecycleOwner) {
            binding.tvEmail.text = "Email: $it"
        }

        viewModel.success.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
        })

        viewModel.fail.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}