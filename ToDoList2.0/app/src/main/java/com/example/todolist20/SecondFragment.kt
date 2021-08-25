package com.example.todolist20

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist20.database.Todo
import com.example.todolist20.databinding.FragmentSecondBinding
import com.example.todolist20.viewmodel.TodoViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: TodoViewModel by activityViewModels{
        TodoViewModel.TodoViewModelFactory(
            (activity?.application as TodoAplication).database.todoDao()
        )
    }
    lateinit var todo: Todo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            addNewItem()
        }
    }

    private fun isEntryValid() : Boolean{
        return viewModel.isEntryValid(
            binding.titletodo.text.toString()
        )
    }
    private fun addNewItem() {
        if (isEntryValid()) {
            viewModel.addNewItem(binding.titletodo.text.trim().toString())
            findNavController().navigate(R.id.action_SecondFragment_to_listTodoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}