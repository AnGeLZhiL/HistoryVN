package com.example.historyvn.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.historyvn.User
import com.example.historyvn.models.AnswersModel
import com.example.historyvn.models.QuestionModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuestionViewModel(
    private val client: HttpClient
): ViewModel() {

    private var questions  = mutableListOf<Pair<QuestionModel, Boolean>>()
    private val _currentQuestion = MutableStateFlow(QuestionModel(-1, "",emptyList()))
    val currentQuestion = _currentQuestion.asStateFlow()

    suspend fun fetchQuestions(id: Int) {
        viewModelScope.launch {
            runCatching {
                client.get("questions/$id").body<List<QuestionModel>>()
            }.onSuccess {
                questions = it.map { it to false }.toMutableList()
                _currentQuestion.value = questions.first().first
            }

        }
    }

    fun answer(num: Int, test: Int): Boolean {
        val current = questions.indexOfFirst { it.first == _currentQuestion.value }
        questions[current] = questions[current].first to questions[current].first.answers[num].isRight
        if (current == questions.lastIndex) {
            viewModelScope.launch {
                client.post("questions/check_results") {
                    contentType(ContentType.Application.Json)
                    setBody(
                        AnswersModel(
                            user = User.info.id,
                            test = test,
                            questions.map { it.second }
                        )
                    )
                }
            }
            return true
        } else {
            _currentQuestion.value = questions[current + 1].first
            return false
        }
    }

}