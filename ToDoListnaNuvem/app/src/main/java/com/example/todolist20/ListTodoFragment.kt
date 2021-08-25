package com.example.todolist20

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.CheckBox
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asFlow
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist20.adapter.Adapter
import com.example.todolist20.databinding.FragmentFirstBinding
import com.example.todolist20.viewmodel.TodoViewModel
import com.google.android.material.appbar.MaterialToolbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListTodoFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    //private lateinit var recyclerView: RecyclerView
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: Adapter


    private val viewModel: TodoViewModel by activityViewModels{
        TodoViewModel.TodoViewModelFactory(
            (activity?.application as TodoAplication).database.itemFirebaseRepository()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = Adapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter
        val topAppBar = view.findViewById<MaterialToolbar>(R.id.topAppBar)
        topAppBar.setOnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.delete -> {
                    viewModel.deleteTodo()
                }
            }
            true
        }

        viewModel.allItems.observe(this.viewLifecycleOwner){
            it.let{
                adapter.submitList(it)
            }
        }
        binding.floatButton.setOnClickListener {
            findNavController().navigate(R.id.action_listTodoFragment_to_SecondFragment)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}