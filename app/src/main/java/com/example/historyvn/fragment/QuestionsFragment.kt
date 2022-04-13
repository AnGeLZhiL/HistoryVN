package com.example.historyvn.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.historyvn.R
import com.example.historyvn.User
import com.example.historyvn.databinding.QuestionsFragmentBinding
import com.example.historyvn.viewmodels.QuestionViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionsFragment: Fragment() {
    private var _binding: QuestionsFragmentBinding? = null
    private val binding: QuestionsFragmentBinding
        get() = _binding ?: throw RuntimeException()

    val args by navArgs<QuestionsFragmentArgs>()
    val viewModel by viewModel<QuestionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = QuestionsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fioText.text = "${User.info.firstName} ${User.info.lastName[0]}. ${User.info.midlleName[0]}."
        binding.ratingText.text = User.info.rating.toString()
        lifecycleScope.launchWhenCreated {
            viewModel.fetchQuestions(args.testId)
            viewModel.currentQuestion.collectLatest {
                binding.textView2.text = it.title
                if (it.answers.isNotEmpty()) {
                    binding.posible1.text = it.answers[0].text
                    binding.posible2.text = it.answers[1].text
                    binding.posible3.text = it.answers[2].text
                }
            }
        }

        binding.posible1.setOnClickListener {
            if (viewModel.answer(0, args.testId)) findNavController().navigateUp()
        }
        binding.posible2.setOnClickListener {
            if (viewModel.answer(1, args.testId)) findNavController().navigateUp()
        }
        binding.posible3.setOnClickListener {
            if (viewModel.answer(2, args.testId)) findNavController().navigateUp()
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}