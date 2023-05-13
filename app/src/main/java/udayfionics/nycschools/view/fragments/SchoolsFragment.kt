package udayfionics.nycschools.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import udayfionics.nycschools.databinding.FragmentSchoolsBinding
import udayfionics.nycschools.viewmodel.SchoolsViewModel

class SchoolsFragment : Fragment() {

    private lateinit var binding: FragmentSchoolsBinding
    private lateinit var viewModel: SchoolsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSchoolsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SchoolsViewModel::class.java]
    }
}