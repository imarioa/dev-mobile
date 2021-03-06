package com.example.todolist20

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class FragmentCreateUser: Fragment() {
    val ref= FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.createuser, container, false)
        val email=view.findViewById<TextInputEditText>(R.id.email)
        val password=view.findViewById<TextInputEditText>(R.id.senha)
        val registerBtn=view.findViewById<Button>(R.id.registerbtn)

        registerBtn.setOnClickListener {
            if(!(email.text.toString().isBlank() || password.text.toString().isBlank())) {
                ref.createUserWithEmailAndPassword(
                    email.text.toString().trim(),
                    password.text.toString().trim()
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(R.id.action_fragmentCreateUser_to_fragmentLogin)
                        Toast.makeText(
                            context, "Usuário criado!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context, "Criação falhou.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            }else {
                Toast.makeText(
                    context, "Criação falhou.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return view
    }
}