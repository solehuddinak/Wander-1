
package dev.dizzy1021.wander.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.dizzy1021.core.utils.SPLASH_SCREEN_DELAY
import dev.dizzy1021.core.utils.SharedPreferenceUtil
import dev.dizzy1021.wander.R
import dev.dizzy1021.wander.databinding.FragmentSplashBinding
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding as FragmentSplashBinding

    @Inject
    lateinit var pref: SharedPreferenceUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val actionBar = requireActivity().findViewById<Toolbar>(R.id.main_toolbar)
        actionBar.isGone = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadSplashScreen(pref.getStatusOnboard())
    }

    private fun loadSplashScreen(status: Boolean) {

        Handler(Looper.getMainLooper()).postDelayed({
            context?.let {
                if (status)
                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                else
                    findNavController().navigate(R.id.action_splashFragment_to_onboardFragment)
            }

        }, SPLASH_SCREEN_DELAY)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}