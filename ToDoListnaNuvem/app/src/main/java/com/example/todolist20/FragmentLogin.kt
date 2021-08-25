package com.example.todolist20

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class FragmentLogin: Fragment() {
    private lateinit var ref: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ref= FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login, container, false)
        val email=view.findViewById<TextInputEditText>(R.id.logintitle)
        val password=view.findViewById<TextInputEditText>(R.id.password)
        val registerBtn=view.findViewById<Button>(R.id.loginbtn)
        val register = view.findViewById<TextView>(R.id.register)

        registerBtn.setOnClickListener {
            if(!(email.text.toString().isBlank() || password.text.toString().isBlank())){
                signIn(email,password)
            }else{
                Toast.makeText(context,"Autenticação falhou", Toast.LENGTH_SHORT).show()
            }
        }
        register.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentLogin_to_fragmentCreateUser)
        }
        return view
    }
    fun signIn(email:TextView,  password:TextView){
        ref.signInWithEmailAndPassword(
            email.text.toString().trim(),
            password.text.toString().trim()
        ).addOnCompleteListener { task ->
            if(task.isSuccessful){
                findNavController().navigate(R.id.action_fragmentLogin_to_listTodoFragment)
            }else{
                Toast.makeText(context, "Autenticação falhou",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}