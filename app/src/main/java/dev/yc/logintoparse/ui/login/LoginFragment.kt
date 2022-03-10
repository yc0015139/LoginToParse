package dev.yc.logintoparse.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dev.yc.logintoparse.MainActivity
import dev.yc.logintoparse.databinding.FragmentLoginBinding
import dev.yc.logintoparse.utils.livedata.EventObserver
import dev.yc.logintoparse.utils.navigation.navigateSafe

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels(
        factoryProducer = {
            val appContainer = (activity as MainActivity).appContainer
            LoginViewModelFactory(appContainer.userRepository)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupEvents()
        setupObservers()
    }

    private fun setupEvents() {
        binding.run {
            btnLogin.setOnClickListener {
                viewModel.doLogin(
                    account = tfAccount.editText?.text.toString(),
                    password = tfPassword.editText?.text.toString()
                )
            }
        }
    }

    private fun setupObservers() {
        viewModel.canClick.observe(viewLifecycleOwner, EventObserver {
            binding.btnLogin.isEnabled = it
        })

        viewModel.login.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
            navToTrafficNews()
        })

        viewModel.empty.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, "empty", Toast.LENGTH_SHORT).show()
        })

        viewModel.invalid.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, "invalid", Toast.LENGTH_SHORT).show()
        })
    }

    private fun navToTrafficNews() {
        val directions = LoginFragmentDirections.actionToTrafficNews()
        findNavController().navigateSafe(directions)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
