package dev.yc.logintoparse.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.yc.logintoparse.data.repository.LoginRepository
import dev.yc.logintoparse.databinding.FragmentLoginBinding
import dev.yc.logintoparse.utils.livedata.EventObserver
import kotlinx.coroutines.Dispatchers

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels(
        factoryProducer = {
            val dispatcher = Dispatchers.IO
            val loginRepository = LoginRepository(dispatcher)
            LoginViewModelFactory(loginRepository)
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
            // TODO: login success
            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
        })

        viewModel.empty.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, "empty", Toast.LENGTH_SHORT).show()
        })

        viewModel.invalid.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, "invalid", Toast.LENGTH_SHORT).show()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
