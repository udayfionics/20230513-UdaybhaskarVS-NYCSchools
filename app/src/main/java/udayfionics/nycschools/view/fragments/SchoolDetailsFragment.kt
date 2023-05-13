package udayfionics.nycschools.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import udayfionics.nycschools.databinding.FragmentSchoolDetailsBinding
import udayfionics.nycschools.viewmodel.SchoolDetailsViewModel

class SchoolDetailsFragment : Fragment() {

    private lateinit var binding: FragmentSchoolDetailsBinding
    private lateinit var viewModel: SchoolDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentSchoolDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SchoolDetailsViewModel::class.java]
    }
}