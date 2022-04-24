package com.example.historyvn.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.historyvn.User
import com.example.historyvn.databinding.FragmentInformationsBinding
import com.example.historyvn.fragment.adapters.CategoryAdapter
import com.example.historyvn.viewmodels.CategoriesViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentInformations : Fragment() {

    private var _binding: FragmentInformationsBinding? = null
    private val binding: FragmentInformationsBinding
        get() = _binding ?: throw RuntimeException()

    private val viewModel by viewModel<CategoriesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInformationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fioText.text =
            "${User.info.firstName} ${User.info.lastName[0]}. ${User.info.midlleName[0]}."
        binding.ratingText.text = User.info.rating.toString()
        binding.categories.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launchWhenCreated {
            viewModel.categories.collectLatest { categories ->
                binding.categories.adapter = CategoryAdapter(
                    categories.map { category ->
                        category to {
                            findNavController().navigate(
                                FragmentInformationsDirections.actionInformationsToObjectsFragment2()
                                    .also { it.categoryId = category.id }
                            )
                        }
                    }
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}